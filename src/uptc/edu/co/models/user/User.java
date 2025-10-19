/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.user;

import java.io.IOException;
import java.util.List;
import uptc.edu.co.models.persistence.PersistenceManager;

/**
 *
 * @author alber
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private Role rol;
    private boolean banned;

    public User(int id, String name, String email, String password, Role rol) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.banned = false;
    }

    public List<User> viewAllUsers() {
        if (this.rol != Role.ADMIN) {
            throw new SecurityException("Solo administradores pueden ver los usuarios");
        }
        return new PersistenceManager().loadUsers();
    }

    public void banUser(int idUser) {
        User auxUser = searchUser(idUser);
        auxUser.setBanned(true);
    }

    public void promoteUser(int id) {
        User promoUser = searchUser(id);
        promoUser.setRol(Role.ADMIN);
    }

    public User searchUser(int userId) {
        PersistenceManager manager = new PersistenceManager();
        List<User> users = manager.loadUsers();
        User auxUser = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                auxUser = users.get(i);
                i = users.size();
            }
        }
        return auxUser;
    }

    public void deleteUser(int userId) throws IOException {
        PersistenceManager manager = new PersistenceManager();
        manager.deleteUser(userId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public boolean getBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

}
