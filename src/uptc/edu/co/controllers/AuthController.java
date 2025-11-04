/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.user.Role;
import uptc.edu.co.models.user.User;
import uptc.edu.co.view.subVistas.PomodoroLogin;
import uptc.edu.co.view.subVistas.RegisterForm;
import uptc.edu.co.models.user.UserService;

/**
 *
 * @author alber
 */
public class AuthController implements ActionListener {
    // private View vista;

    private PomodoroLogin login;
    private RegisterForm register;
    private List< User> usuarios = new ArrayList<>();

    public AuthController() {
        abrirLogin();
    }

    private void abrirLogin() {
        login = new PomodoroLogin(this);
    }

    private void abrirRegistro() {
        register = new RegisterForm(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "BUTTON_LOGIN" ->
                manejarLogin();

            case "BUTTON_REGISTER_LOGIN" -> {
                login.setVisible(false);
                abrirRegistro();
            }

            case "BUTTON_BACK_LOGIN" -> {
                login.dispose();
                login.showMessage("se abrio la vista ");
                //vista visible 
                // vista.setVisible(true);
            }

            case "BUTTON_BACK_REGISTER" -> {
                register.dispose();
                login.setVisible(true);
            }

            case "BUTTON_REGISTER" -> {
                try {
                    manejarRegistro();
                } catch (IOException ex) {
                    System.getLogger(AuthController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }

        }
        // === LOGIN ===

    }

    private void manejarLogin() {
        PersistenceManager manager = new PersistenceManager();
        UserService service = new UserService(manager);
        String email = login.getTextEmail();
        String password = login.getTextPassword();

        if (email.isEmpty() || password.isEmpty() || email.equalsIgnoreCase("EMAIL ID") || password.equalsIgnoreCase("PASSWORD")) {
            login.showMessage("Por favor ingrese todos los campos.");
        } else if (service.userExist(email) == true) {
            if (service.verificatePassword(email, password) == true) {
                User user = service.searchUser(email, password);
                login.showMessage("Bienvenido: " + email);
                login.dispose();
                login.showMessage("se abrio la vista ");
                //enviar a la vista el usuario
            } else {
                login.showMessage("Contraseña incorrecta.");
            }
        } else {
            login.showMessage("Usuario o contraseña incorrectos.");
            login.setTextEmail();
            login.setTextPassword();
        }

    }

    private void manejarRegistro() throws IOException {
        String name = register.getTextName();
        String email = register.getTextEmail();
        String password = register.getTextPassword();
        PersistenceManager manager = new PersistenceManager();
        UserService service = new UserService(manager);

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || name.equalsIgnoreCase("Ingrese su nombre...") || email.equalsIgnoreCase("Ingrese su correo...") || password.equalsIgnoreCase("Ingrese una contraseña...")) {
            register.showMessage("Por favor ingrese todos los campos.");
        } else if (service.userExist(email) == false) {
            User newUser = new User(service.generateId(), name, email, password, Role.USER);
            manager.addUser(newUser);
            register.showMessage("Usuario registrado correctamente: " + name);
            login.setVisible(true);
            register.dispose();
        } else {
            register.showMessage("Este usuario ya existe.");
        }
    }

    public static void main(String[] args) {
        AuthController u = new AuthController();

    }
}
