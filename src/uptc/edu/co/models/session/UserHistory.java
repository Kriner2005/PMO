/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.session;

/**
 *
 * @author alber
 */
public class UserHistory {

    private final int userId;
    private Session session;

    // Constructor
    public UserHistory(int userId) {
        this.userId = userId;
        this.session = null;
    }

    // obtiene el id del usuario
    public int getUserId() {
        return userId;
    }
    
    public void setSession(Session session) {
        this.session = session;
    }
}
