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
import uptc.edu.co.models.settings.SystemSettings;
import uptc.edu.co.models.user.User;
import uptc.edu.co.models.user.Role;

public class UserController {

    private User loggedUser;

    public void login(User user) {
        if (user.getRol() == Role.ADMIN) {
            openAdminView(user);
        } else {
            openUserView(user);
        }
    }

    //Crear usuario
    public User createUser(String name, String email, String password) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setUserSettings(new SystemSettings());
        newUser.setRol(Role.USER);

        return newUser;
    }

    public User createAdminUser(String name, String email, String password) {
        if (!loggedUser.getRol().isAdmin()) {
            throw new SecurityException("Solo los administradores pueden crear otros administradores.");
        }
        User newAdmin = new User();
        newAdmin.setName(name);
        newAdmin.setEmail(email);
        newAdmin.setPassword(password);
        newAdmin.setUserSettings(new SystemSettings());
        newAdmin.setRol(Role.ADMIN);

        return newAdmin;
    }

    private void openAdminView(User user) {
        //AdminView adminView = new AdminView(user);vista de administrador
        
    }

    private void openUserView(User user) {
        //UserView userView = new UserView(user); vistaNOrmal user
        //definir si hay 2 vistas para cada tipo de user o solo ocultar las funcionalidades de cada usuario
    }
}
