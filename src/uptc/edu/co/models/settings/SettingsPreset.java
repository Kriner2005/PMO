/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package uptc.edu.co.models.settings;

/**
 *
 * @author alber
 */
public enum SettingsPreset {
    CLASSIC("Clásico", 25, 5, 15),
    SHORT("Corto", 15, 3, 10),
    LONG("Largo", 50, 10, 20);

    public String name;
    public int workDuration;
    public int shortBreak;
    public int longBreak;
    public int longBreakInterval = 4;

    SettingsPreset(String name, int workDuration, int shortBreak, int longBreak) {
        this.name = name;
        this.workDuration = workDuration;
        this.shortBreak = shortBreak;
        this.longBreak = longBreak;
    }

    public void aplyToSettings(Settings settings) {
        settings.setWork(workDuration);
        settings.setShortBreak(shortBreak);
        settings.setLongBreak(longBreak);
        settings.setLongBreakInterval(longBreakInterval);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(int workDuration) {
        this.workDuration = workDuration;
    }

    public int getShortBreak() {
        return shortBreak;
    }

    public void setShortBreak(int shortBreak) {
        this.shortBreak = shortBreak;
    }

    public int getLongBreak() {
        return longBreak;
    }

    public void setLongBreak(int longBreak) {
        this.longBreak = longBreak;
    }

    public int getLongBreakInterval() {
        return longBreakInterval;
    }

    public void setLongBreakInterval(int longBreakInterval) {
        this.longBreakInterval = longBreakInterval;
    }

}
