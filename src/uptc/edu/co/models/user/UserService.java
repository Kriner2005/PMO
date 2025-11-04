/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.user;

import java.util.List;
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

}
