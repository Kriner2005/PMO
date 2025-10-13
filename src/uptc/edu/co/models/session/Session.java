/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.session;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import uptc.edu.co.models.settings.Settings;
import uptc.edu.co.models.user.User;

/**
 *
 * @author alber
 */
public class Session {

    private int sessionId;
    private String sessionName; // "Sesión Mañana", "Estudio Matemáticas"
    private User loggedUser; // Referencia al usuario actual
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<PomodoroRecord> currentSessionsRecords; // Pomodoros de esta sesión
    private Settings settings; // Config específica de esta sesión

    public Session() {
        this.currentSessionsRecords = new ArrayList<>();
        this.settings = new Settings(); // valores por defecto
    }

    public Session(User user, String sessionName) {
        this.loggedUser = user;
        this.sessionName = sessionName;
        this.startTime = LocalDateTime.now();
        this.currentSessionsRecords = new ArrayList<>();
        this.settings = new Settings(); // valores por defecto
    }

    public void initializeSession() {
        this.startTime = LocalDateTime.now();
    }

    public void finalizeSession() {
        this.endTime = LocalDateTime.now();
    }

    public void addPomodoroRecord(PomodoroRecord record) {
        if (this.currentSessionsRecords == null) {
            this.currentSessionsRecords = new ArrayList<>();
        }
        this.currentSessionsRecords.add(record);
    }

    public Statistics getSessionStats() {
        return new Statistics();
    }

    public boolean isActive() {
        return endTime == null;
    }

    public Duration getDuration() {
        if (endTime != null) {
            return Duration.between(startTime, endTime);
        } else {
            return Duration.between(startTime, LocalDateTime.now());
        }
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int id) {
        this.sessionId = id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String name) {
        this.sessionName = name;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public List<PomodoroRecord> getHistory() {
        return currentSessionsRecords;
    }

    public void setHistory(List<PomodoroRecord> currentSessionsRecords) {
        this.currentSessionsRecords = currentSessionsRecords;
    }

}
