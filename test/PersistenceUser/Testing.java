/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PersistenceUser;

import java.io.IOException;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.user.Role;
import uptc.edu.co.models.user.User;

/**
 *
 * @author alber
 */
public class Testing {

    public static void main(String[] args) throws IOException {
        PersistenceManager manager = new PersistenceManager();
              User user = new User(1, "Keiner", "@gmail", "Password", null, Role.USER);
        User user1 = new User(2, "sara", "@gmail", "Password", null, Role.USER);
        User user2 = new User(3, "alberto", "@gmail", "Password", null, Role.USER);
        User user3 = new User(4, "carvjal", "@gmail", "Password", null, Role.USER);
        manager.addUser(user);
        manager.addUser(user1);
        manager.addUser(user2);
        manager.addUser(user3);
        manager.deleteUser(4);
        
    }
}
