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
    private String taskName; // "Estudiar Java", "Leer libro"
    private boolean completed; // ¿Se completó o se canceló?
    private PomodoroType type; // WORK, SHORT_BREAK, LONG_BREAK

    public PomodoroRecord(PomodoroType type, String taskName) {
        this.type = type;
        this.taskName = taskName;
        this.startTime = LocalDateTime.now();
        this.completed = false;
    }

    public void startRecord() {
        this.startTime = LocalDateTime.now();
    }

    public void endRecord(boolean completed) {
        this.endTime = LocalDateTime.now();
        this.completed = completed;
        if (startTime != null && endTime != null) {
            this.plannedDuration = (int) java.time.Duration.between(startTime, endTime).toMinutes();
        }
    }

    public double getEfficiencyPercentage() {
        if (plannedDuration <= 0) {
            return 0;
        }
        // Suponiendo que la duración planificada es el tiempo ideal
        int idealDuration = switch (type) {
            case WORK ->
                25;
            case SHORT_BREAK ->
                5;
            case LONG_BREAK ->
                15;
        };
        return Math.min(100, (idealDuration / (double) plannedDuration) * 100);
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
