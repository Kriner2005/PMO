/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.settings;

import uptc.edu.co.controllers.PomodoroController;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author alber
 */
public class Settings {

    private int workDuration; // en minutos
    private int shortBreakDuration; // en minutos
    private int longBreakDuration; // en minutos
    private int longBreakInterval; // cada cuántos pomodoros va un break largo
    private String theme;
    private boolean soundEnabled;
    private String soundFile;

    // Constructor con valores por defecto
    public Settings() {
        this.workDuration = 25;
        this.shortBreakDuration = 5;
        this.longBreakDuration = 15;
        this.longBreakInterval = 4;
        this.theme = "Light";
    }

    // ---------------- MÉTODOS PRINCIPALES ----------------
    // Cargar un preset
    public void loadPreset(SettingsPreset preset) {
        this.workDuration = preset.getWorkDuration();
        this.shortBreakDuration = preset.getShortBreak();
        this.longBreakDuration = preset.getLongBreak();
        this.longBreakInterval = preset.getLongBreakInterval();
        this.theme = "Light"; // o preset.getTheme() si se añade al enum
    }

    // Guardar en un archivo (serialización simple)
    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("settings.ser"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error saving settings: " + e.getMessage());
        }
    }

    // Cargar desde un archivo (si existe)
    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("settings.ser"))) {
            Settings loaded = (Settings) ois.readObject();
            // Directly assign loaded values since SettingsPreset cannot be instantiated
            this.workDuration = loaded.workDuration;
            this.shortBreakDuration = loaded.shortBreakDuration;
            this.longBreakDuration = loaded.longBreakDuration;
            this.longBreakInterval = loaded.longBreakInterval;
            this.theme = loaded.theme;
        } catch (IOException | ClassNotFoundException e) {
            //throw new SettingsLoadException("No previous settings found. Using defaults.", e);
        }
    }

    // Aplicar configuración a un controlador
    public void applyTo(PomodoroController controller) {
        // Asumimos que PomodoroController tiene un setter de settings
        controller.setSettings(this);
    }

    // Clonar la configuración
    @Override
    public Settings clone() {
        try {
            return (Settings) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Settings(); // en caso de error, retorna nueva
        }
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

    public String getTheme() {
        return theme;
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public String getSoundFile() {
        return soundFile;
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

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;
    }

    public void setSoundFile(String soundFile) {
        this.soundFile = soundFile;
    }
}
