/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.controllers;

/**
 *
 * @author alber
 */
import java.util.List;
import uptc.edu.co.models.user.User;
import uptc.edu.co.models.user.Role;

public class UserController {

    private User currentUser;

    public boolean login(String email, String password) {
        // Aquí cargarías el usuario desde PersistenceManager
        // y verificarías la contraseña

        return false;
    }

    public boolean isAdmin() {
        return currentUser != null
                && currentUser.getRol() == Role.ADMIN;
    }

    public boolean canBanUsers() {
        return isAdmin();
    }

    public boolean canViewAllUsers() {
        return isAdmin();
    }

    public boolean canDeleteAnyUser() {
        return isAdmin();
    }

    // Métodos protegidos que verifican permisos
    public List<User> viewAllUsers() {
        if (!canViewAllUsers()) {
            throw new SecurityException("No tienes permisos de administrador");
        }
        // lógica para obtener usuarios
        return null;
    }

    public void banUser(int userId) {
        if (!canBanUsers()) {
            throw new SecurityException("No tienes permisos de administrador");
        }
        // lógica para banear usuario
    }
}
