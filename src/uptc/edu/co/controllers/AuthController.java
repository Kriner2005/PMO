/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.controllers;

/**
 *
 * @author alber
 */
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.settings.Settings;
import uptc.edu.co.models.user.Role;
import uptc.edu.co.models.user.User;

public class AuthController {

    private PersistenceManager persistenceManager;
    private User currentUser;  // Usuario actualmente logueado
    private boolean isGuestMode;  // Modo invitado
    private static int nextUserId = 1;  // Contador para IDs

    // Constructor
    public AuthController(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
        this.currentUser = null;
        this.isGuestMode = false;
        initializeNextUserId();
    }

    // ==================== REGISTRO DE USUARIOS ====================
    /**
     * Registra un nuevo usuario normal (USER)
     *
     * @return true si el registro fue exitoso, false si ya existe
     */
    public boolean registerUser(String name, String email, String password) {
        return registerUser(name, email, password, Role.USER);
    }

    /**
     * Registra un nuevo usuario con rol específico Solo un ADMIN puede crear
     * otros ADMIN
     */
    public boolean registerUser(String name, String email, String password, Role role) {
        // Validaciones
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (email == null || !isValidEmail(email)) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }

        // Verificar si el email ya existe
        if (persistenceManager.loadUser(email) != null) {
            return false;  // Usuario ya existe
        }

        // Verificar permisos para crear ADMIN
        if (role == Role.ADMIN && !isAdmin()) {
            throw new SecurityException("Solo un administrador puede crear otros administradores");
        }

        // Crear nuevo usuario
        User newUser = new User();
        newUser.setId(nextUserId++);
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(hashPassword(password));  // TODO: Implementar hash real
        newUser.setRol(role);
        newUser.setUserSettings(new Settings());  // Configuración por defecto

        // Guardar en persistencia
        boolean saved = persistenceManager.saveUser(newUser);

        if (saved) {
            System.out.println("✅ Usuario registrado: " + name + " (" + role + ")");
        }

        return saved;
    }

    // ==================== LOGIN/LOGOUT ====================
    /**
     * Inicia sesión con email y contraseña
     *
     * @return true si el login fue exitoso
     */
    public boolean login(String email, String password) {
        if (email == null || password == null) {
            return false;
        }

        // Buscar usuario
        User user = persistenceManager.loadUser(email);

        if (user == null) {
            System.out.println("❌ Usuario no encontrado: " + email);
            return false;
        }

        // Verificar contraseña
        if (!verifyPassword(password, user.getPassword())) {
            System.out.println("❌ Contraseña incorrecta");
            return false;
        }

        // Login exitoso
        this.currentUser = user;
        this.isGuestMode = false;

        System.out.println("✅ Login exitoso: " + user.getName() + " (" + user.getRol() + ")");
        return true;
    }

    /**
     * Cierra la sesión actual
     */
    public void logout() {
        if (currentUser != null) {
            System.out.println("👋 Logout: " + currentUser.getName());
        }
        this.currentUser = null;
        this.isGuestMode = false;
    }

    /**
     * Inicia modo invitado (sin persistencia)
     */
    public void loginAsGuest() {
        User guest = new User();
        guest.setId(-1);  // ID especial para invitado
        guest.setName("Invitado");
        guest.setEmail("guest@temp.com");
        guest.setRol(Role.USER);
        guest.setUserSettings(new Settings());

        this.currentUser = guest;
        this.isGuestMode = true;

        System.out.println("👤 Modo invitado activado");
    }

    // ==================== GESTIÓN DE USUARIOS (ADMIN) ====================
    /**
     * Obtiene todos los usuarios (solo ADMIN)
     */
    public java.util.List<User> viewAllUsers() {
        if (!isAdmin()) {
            throw new SecurityException("Solo administradores pueden ver todos los usuarios");
        }
        return persistenceManager.loadAllUsers();
    }

    /**
     * Elimina un usuario (solo ADMIN)
     */
    public boolean deleteUser(int userId) {
        if (!isAdmin()) {
            throw new SecurityException("Solo administradores pueden eliminar usuarios");
        }

        // No permitir eliminar al propio admin
        if (currentUser != null && currentUser.getId() == userId) {
            throw new IllegalStateException("No puedes eliminarte a ti mismo");
        }

        return persistenceManager.deleteUser(userId);
    }

    /**
     * Cambia el rol de un usuario (solo ADMIN)
     */
    public boolean changeUserRole(int userId, Role newRole) {
        if (!isAdmin()) {
            throw new SecurityException("Solo administradores pueden cambiar roles");
        }

        // Buscar usuario
        java.util.List<User> users = persistenceManager.loadAllUsers();
        for (User user : users) {
            if (user.getId() == userId) {
                user.setRol(newRole);
                return persistenceManager.saveUser(user);
            }
        }

        return false;
    }

    /**
     * Bane/Activa un usuario (TODO: implementar campo 'banned' en User)
     */
    public void banUser(int userId) {
        if (!isAdmin()) {
            throw new SecurityException("Solo administradores pueden banear usuarios");
        }
        // TODO: Agregar campo 'banned' a la clase User
        System.out.println("⚠️ Funcionalidad de baneo no implementada aún");
    }

    // ==================== VERIFICACIÓN DE PERMISOS ====================
    /**
     * Verifica si hay un usuario logueado
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Verifica si el usuario actual es ADMIN
     */
    public boolean isAdmin() {
        return currentUser != null && currentUser.getRol() == Role.ADMIN;
    }

    /**
     * Verifica si está en modo invitado
     */
    public boolean isGuestMode() {
        return isGuestMode;
    }

    /**
     * Obtiene el usuario actual
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Verifica si el usuario puede realizar acciones administrativas
     */
    public boolean canPerformAdminActions() {
        return isAdmin();
    }

    // ==================== UTILIDADES ====================
    /**
     * Valida formato de email básico
     */
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    /**
     * Hash de contraseña (IMPLEMENTACIÓN SIMPLE - USAR BCrypt EN PRODUCCIÓN)
     */
    private String hashPassword(String password) {
        // TODO: Implementar hash real con BCrypt o similar
        return "HASH_" + password;  // ⚠️ TEMPORAL - NO USAR EN PRODUCCIÓN
    }

    /**
     * Verifica contraseña contra hash
     */
    private boolean verifyPassword(String password, String hashedPassword) {
        // TODO: Implementar verificación real
        return hashedPassword.equals(hashPassword(password));
    }

    /**
     * Inicializa el contador de IDs leyendo usuarios existentes
     */
    private void initializeNextUserId() {
        java.util.List<User> users = persistenceManager.loadAllUsers();
        if (users != null && !users.isEmpty()) {
            int maxId = 0;
            for (User user : users) {
                if (user.getId() > maxId) {
                    maxId = user.getId();
                }
            }
            nextUserId = maxId + 1;
        }
    }

    /**
     * Cambia la contraseña del usuario actual
     */
    public boolean changePassword(String oldPassword, String newPassword) {
        if (currentUser == null || isGuestMode) {
            return false;
        }

        if (!verifyPassword(oldPassword, currentUser.getPassword())) {
            return false;
        }

        if (newPassword.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }

        currentUser.setPassword(hashPassword(newPassword));
        return persistenceManager.saveUser(currentUser);
    }

    /**
     * Actualiza el perfil del usuario actual
     */
    public boolean updateProfile(String name, String email) {
        if (currentUser == null || isGuestMode) {
            return false;
        }

        if (name != null && !name.trim().isEmpty()) {
            currentUser.setName(name);
        }

        if (email != null && isValidEmail(email)) {
            currentUser.setEmail(email);
        }

        return persistenceManager.saveUser(currentUser);
    }
}
