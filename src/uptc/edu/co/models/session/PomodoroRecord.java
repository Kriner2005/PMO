/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.session;

import java.time.LocalDateTime;

/**
 *
 * @author alber
 */
public class PomodoroRecord {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int plannedDuration; // duración real del pomodoro
    private boolean completed; // ¿Se completó o se canceló?
    private PomodoroType type; // WORK, SHORT_BREAK, LONG_BREAK

    public PomodoroRecord(PomodoroType type, LocalDateTime startTime, LocalDateTime endTime, boolean completed) {
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.completed = completed;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPlannedDuration() {
        return plannedDuration;
    }

    public void setPlannedDuration(int duration) {
        this.plannedDuration = duration;
    }
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public PomodoroType getType() {
        return type;
    }

    public void setType(PomodoroType type) {
        this.type = type;
    }

}
