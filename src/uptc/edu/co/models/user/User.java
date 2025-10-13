/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.user;

import java.util.List;
import uptc.edu.co.models.settings.Settings;

/**
 *
 * @author alber
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private Settings userSettings;
    private Role rol;

    public List<User> viewAllUsers() {
        return null;
    }

    public void banUser(int idUser) {
    }

    public void deleteUser() {
    }

    public List<User> viewLoginUsers() {
        return null;
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

    public Settings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(Settings userSettings) {
        this.userSettings = userSettings;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

}
