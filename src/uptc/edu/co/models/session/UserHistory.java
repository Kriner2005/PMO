/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.session;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alber
 */
public class UserHistory {

    private final int userId;
    private int numSessions;
    private final List<Session> sessions;

    // Constructor
    public UserHistory(int userId) {
        this.userId = userId;
        this.sessions = new ArrayList<>();
        this.numSessions = 0;
    }

    // Añadir una sesión completada
    public void addCompletedSession(Session session) {
        if (session != null) {
            sessions.add(session);
        }
    }

    // obtiene las sesiones completadas
    public List<Session> getCompletedSessions() {
        return sessions;
    }

    // obtiene el id del usuario
    public int getUserId() {
        return userId;
    }

    public int getNumSessions() {
        return numSessions;
    }

    public void setNumSessions(int numSessions) {
        this.numSessions = numSessions;
    }
}
