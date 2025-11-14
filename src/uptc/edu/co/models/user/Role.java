/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package uptc.edu.co.models.user;

/**
 *
 * @author alber
 */
public enum Role {
    USER("Usuario"),
    ADMIN("Administrador");
    
    private final String descripcion;

    Role(String descripcion) {
        this.descripcion = descripcion;
    }
    
     public String getDescripcion() {
        return descripcion;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
    
    public boolean isAdmin() {
        return this == ADMIN;
    }

    public boolean isUser() {
        return this == USER;
    }
}