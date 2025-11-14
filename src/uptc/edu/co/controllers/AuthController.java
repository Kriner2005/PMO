/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.session.Settings;
import uptc.edu.co.models.user.Role;
import uptc.edu.co.models.user.User;
import uptc.edu.co.view.subVistas.PomodoroLogin;
import uptc.edu.co.view.subVistas.RegisterForm;
import uptc.edu.co.models.user.UserService;
import uptc.edu.co.view.View;

/**
 *
 * @author alber
 */
public class AuthController implements ActionListener {
    // private View vista;

    private PomodoroLogin login;
    private RegisterForm register;
    private User user;
    private View vista;
    private MainController mainController;

    public AuthController(MainController mainController) {
        this.mainController = mainController;
    }

    public void abrirLogin() {
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
                vista.setVisible(true);
//                login.showMessage("se abrio la vista ");
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

    }

    private void manejarLogin() {
        PersistenceManager manager = new PersistenceManager();
        UserService service = new UserService(manager);
        String email = login.getTextEmail();
        String password = login.getTextPassword();

        if (email.isEmpty() || password.isEmpty() || email.equalsIgnoreCase("EMAIL ID") || password.equalsIgnoreCase("PASSWORD")) {
            login.showMessage("ERROR", "Por favor ingrese todos los campos.", 1);
        } else if (service.userExist(email) == true) {
            if (service.verificatePassword(email, password) == true) {
                this.user = service.searchUser(email, password);
                login.dispose();
                mainController.setCurretnUserLogged(user);
                if (UserService.verificateRol(user) && user != null) {
                    this.vista.getAdminBtn().setVisible(true);
                    //se le envia la vista para el administrador y se coloca en this.vista
                    login.showMessage("","se abre la vista admin",2);
                    
                }
                this.vista.setVisible(true);
                login.showMessage("Bienvenido: ", service.searchUser(email, password).getName(), 2);

            } else {
                login.showMessage("ERROR", "Contraseña incorrecta.", 1);
            }
        } else {
            login.showMessage("ERROR", "Usuario o contraseña incorrectos.", 1);
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
            register.showMessage("ERROR", "Por favor ingrese todos los campos.", 1);
        } else if (!service.isValidEmail(email)) {
            register.showMessage("Correo inválido"," Por favor ingrese un correo con formato válido (ej: usuario@dominio.com).",1);
        } else if (!service.isValidPassword(password)) {
            String feedback = service.passwordFeedback(password);
            register.showMessage("Contraseña no válida: ", feedback,1);
        } else if (service.userExist(email) == false) {
            User newUser = new User(UserService.generateId(), name, email, password, Role.USER);
            manager.addUser(newUser);
            register.showMessage("Usuario registrado correctamente: " , name,2);
            manager.saveSession(newUser.getId(), new Session(newUser, ""));
            login.setVisible(true);
            register.dispose();
        } else {
            register.showMessage("ERROR","Este usuario ya existe.",1);
        }
    }

    public PomodoroLogin getLogin() {
        return login;
    }

    public User getCurrentUser() {
        return user;
    }

    public void setCurrentUser(User user) {
        this.user = user;
    }

    public void getViewMain(View view) {
        this.vista = view;
    }
}
