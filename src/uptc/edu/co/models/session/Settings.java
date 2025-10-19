/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.session;

/**
 *
 * @author alber
 */
public class Settings {

    private int workDuration; // en minutos
    private int shortBreakDuration; // en minutos
    private int longBreakDuration; // en minutos
    private int longBreakInterval; // cada cuántos pomodoros va un break largo

    public Settings() {
        this.workDuration = 25;
        this.shortBreakDuration = 5;
        this.longBreakDuration = 15;
        this.longBreakInterval = 4;
    }

    // Constructor con valores por defecto
    public Settings(int workDuration, int shortBreak, int longBreak, int longBreakInterval) {
        this.workDuration = workDuration;
        this.shortBreakDuration = shortBreak;
        this.longBreakDuration = longBreak;
        this.longBreakInterval = longBreakInterval;
    }

    // ---------------- MÉTODOS PRINCIPALES ----------------
    // Cargar un preset
    public void loadPreset(SettingsPreset preset) {
        this.workDuration = preset.getWorkDuration();
        this.shortBreakDuration = preset.getShortBreak();
        this.longBreakDuration = preset.getLongBreak();
        this.longBreakInterval = preset.getLongBreakInterval();
    }

    public void selectPreset(int value) {
        switch (value) {
            case 1 ->
                loadPreset(SettingsPreset.CLASSIC);
            case 2 ->
                loadPreset(SettingsPreset.SHORT);
            case 3 ->
                loadPreset(SettingsPreset.LONG);
            default ->
                throw new AssertionError();
        }
    }

    public void customSettings(int workDuration, int shortBrakDuration, int longBreakDuration, int longBreakInterval) {
        saveSettings(new Settings(workDuration, shortBreakDuration, longBreakDuration, longBreakInterval));
    }

    public void saveSettings(Settings newSettings) {
        this.workDuration = newSettings.getWorkDuration();
        this.shortBreakDuration = newSettings.getShortBreakDuration();
        this.longBreakDuration = newSettings.getLongBreakDuration();
        this.longBreakInterval = newSettings.getLongBreakInterval();
    }

    public int getDurationForType(PomodoroType type) {
        return switch (type) {
            case WORK ->
                workDuration;
            case SHORT_BREAK ->
                shortBreakDuration;
            case LONG_BREAK ->
                longBreakDuration;
        };
    }

    // ---------------- GETTERS & SETTERS ----------------
    public int getWorkDuration() {
        return workDuration;
    }

    public int getShortBreakDuration() {
        return shortBreakDuration;
    }

    public int getLongBreakDuration() {
        return longBreakDuration;
    }

    public int getLongBreakInterval() {
        return longBreakInterval;
    }

    public void setWork(int workDuration) {
        this.workDuration = workDuration;
    }

    public void setShortBreak(int shortBreakDuration) {
        this.shortBreakDuration = shortBreakDuration;
    }

    public void setLongBreak(int longBreakDuration) {
        this.longBreakDuration = longBreakDuration;
    }

    public void setLongBreakInterval(int longBreakInterval) {
        this.longBreakInterval = longBreakInterval;
    }
}
