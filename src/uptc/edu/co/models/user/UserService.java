/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.user;

import java.util.List;
import java.util.regex.Pattern;
import uptc.edu.co.models.persistence.PersistenceManager;

/**
 *
 * @author alber
 */
public class UserService {

    private PersistenceManager manager;

    public UserService(PersistenceManager manager) {
        this.manager = manager;
    }

    public User searchUser(String userEmail, String userPassword) {
        List<User> users = manager.loadUsers();
        User auxUser = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(userEmail) && users.get(i).getPassword().equals(userPassword)) {
                auxUser = users.get(i);
                i = users.size();
            }
        }
        return auxUser;
    }

    public User searchUser(String userEmail) {
        List<User> users = manager.loadUsers();
        User auxUser = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(userEmail)) {
                auxUser = users.get(i);
                i = users.size();
            }
        }
        return auxUser;
    }

    public boolean userExist(String userEmail) {
        List<User> users = manager.loadUsers();
        boolean exist = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(userEmail)) {
                exist = true;
                i = users.size();
            }
        }
        return exist;
    }

    public boolean verificatePassword(String userEmail, String userPassword) {
        User userData = searchUser(userEmail);
        boolean correctPassword = false;
        if (userData.getPassword().equals(userPassword)) {
            correctPassword = true;
        }
        return correctPassword;
    }

    public static int generateId() {
        PersistenceManager manager = new PersistenceManager();
        List<User> users = manager.loadUsers();
        int id = users.size() + 1;
        return id;
    }

    private Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    private Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{6,}$"
    );

    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public String passwordFeedback(String password) {
        if (password == null || password.length() < 6) {
            return "Debe tener al menos 6 caracteres.";
        }
        if (!password.matches(".*[A-Z].*")) {
            return "Debe contener al menos una letra mayúscula.";
        }
        if (!password.matches(".*[a-z].*")) {
            return "Debe contener al menos una letra minúscula.";
        }
        if (!password.matches(".*\\d.*")) {
            return "Debe contener al menos un número.";
        }
        if (!password.matches(".*[@$!%*?&._-].*")) {
            return "Debe contener al menos un carácter especial (@$!%*?&._-).";
        }
        return "Contraseña válida.";
    }

    public static boolean verificateRol(User user) {
        return user.getRol() == Role.ADMIN;
    }
}
