/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.controllers;

/**
 *
 * @author alber
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.xml.transform.Source;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.user.Role;
import uptc.edu.co.models.user.User;
import uptc.edu.co.view.subVistas.AdminUser;

public class AdminController {
    private AdminUser vista;
    // El admin que está usando la aplicación
    private User usuarioActual; 
    private PersistenceManager persistenceManager;
    
   
    // Constructor
    public AdminController(AdminUser vista, User usuarioActual) {
        this.vista = vista;
        this.usuarioActual = usuarioActual;
        this.persistenceManager = new PersistenceManager();
    }
    
    /**
     * Carga todos los usuarios y los muestra en la vista
     * Solo puede ser ejecutado por un administrador
     */
   public void cargarUsuarios() {
        try {
            // Verificar permisos
            if (usuarioActual.getRol() != Role.ADMIN) {
                vista.mostrarError("No tienes permisos para ver los usuarios");
                return;
            }
            
            // Obtener usuarios del modelo
            List<User> usuarios = usuarioActual.viewAllUsers();
            
            // Mostrar en la vista
            vista.mostrarUsuarios(usuarios);
            
        } catch (SecurityException e) {
            vista.mostrarError("Error de seguridad: " + e.getMessage());
        } catch (Exception e) {
            vista.mostrarError("Error al cargar usuarios: " + e.getMessage());
            e.printStackTrace(); // Para debugging
        }
    }
    
//   Cambia el rol de un usuario entre USER y ADMIN
    public void cambiarRolUsuario(User usuario) {
        try {
            // Validaciones
            if (usuarioActual.getRol() != Role.ADMIN) {
                vista.mostrarError("No tienes permisos para cambiar roles");
                return;
            }
            
            if (usuario.getId() == usuarioActual.getId()) {
                vista.mostrarError("No puedes cambiar tu propio rol");
                return;
            }
            
            // 1. Cargar TODOS los usuarios del archivo
            List<User> todosLosUsuarios = persistenceManager.loadUsers();
            
            // 2. Buscar el usuario correcto en la lista y cambiar su rol
            boolean encontrado = false;
            for (User u : todosLosUsuarios) {
                if (u.getId() == usuario.getId()) {
                    // Cambiar el rol
                    if (u.getRol() == Role.ADMIN) {
                        u.setRol(Role.USER);
                        usuario.setRol(Role.USER); // Actualizar también el objeto de la vista
                    } else {
                        u.setRol(Role.ADMIN);
                        usuario.setRol(Role.ADMIN);
                    }
                    encontrado = true;
                    break;
                }
            }
            
            if (!encontrado) {
                vista.mostrarError("Usuario no encontrado en el sistema");
                return;
            }
            
            // 3. Guardar la lista completa con el cambio
            persistenceManager.saveUsers(todosLosUsuarios);
            
            vista.mostrarMensaje("Rol actualizado correctamente");
            
        } catch (IOException e) {
            vista.mostrarError("Error al guardar cambios: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            vista.mostrarError("Error al cambiar rol: " + e.getMessage());
            e.printStackTrace();
        }
    }
//    Elimina un usuario del sistema
    public void eliminarUsuario(User usuario) {
        try {
            // Validaciones
            if (usuarioActual.getRol() != Role.ADMIN) {
                vista.mostrarError("No tienes permisos para eliminar usuarios");
                return;
            }
            
            if (usuario.getId() == usuarioActual.getId()) {
                vista.mostrarError("No puedes eliminarte a ti mismo");
                return;
            }
            
            // Eliminar
            usuarioActual.deleteUser(usuario.getId());
            
            vista.mostrarMensaje("Usuario eliminado correctamente");
            
        } catch (IOException e) {
            vista.mostrarError("Error al eliminar usuario: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            vista.mostrarError("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
//    Busca un usuario por ID
    public User buscarUsuario(int id) {
        try {
            return usuarioActual.searchUser(id);
        } catch (Exception e) {
            vista.mostrarError("Error al buscar usuario: " + e.getMessage());
            return null;
        }
    }
    
//    Obtiene todos los usuarios del sistema
    private List<User> obtenerTodosLosUsuarios() {
        return persistenceManager.loadUsers();
    }
    
    /**
     * Refresca la lista de usuarios en la vista
     */
    public void refrescarLista() {
        cargarUsuarios();
    }

    public AdminUser getVista() {
        return vista;
    }
    
    
}