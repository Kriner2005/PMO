/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uptc.edu.co.models.session;

/**
 *
 * @author alber
 */
// GUARDA EN MINUTOS (lo que el usuario entiende)
public class Settings {
    
// GUARDA EN MINUTOS (lo que el usuario entiende)
    private int workDuration;        // 25 minutos
    private int shortBreakDuration;  // 5 minutos
    private int longBreakDuration;   // 15 minutos
    
    public Settings() {
        this.workDuration = 25;
        this.shortBreakDuration = 5;
        this.longBreakDuration = 15;
    }
    
    public Settings(int workDuration, int shortBreak, int longBreak) {
        this.workDuration = workDuration;
        this.shortBreakDuration = shortBreak;
        this.longBreakDuration = longBreak;
    }
    
    // obtiene y convierte 
    public int getWorkDurationInSeconds() {
        return workDuration * 60;
    }
    
    public int getShortBreakDurationInSeconds() {
        return shortBreakDuration * 60;
    }
    
    public int getLongBreakDurationInSeconds() {
        return longBreakDuration * 60;
    }
    
    public int getWorkDuration() {
        return workDuration;
    }
    
    public int getShortBreakDuration() {
        return shortBreakDuration;
    }
    
    public int getLongBreakDuration() {
        return longBreakDuration;
    }
    
    // Setters con validación
    public void setWork(int minutos) {
        if (minutos > 0 && minutos <= 120) {
            this.workDuration = minutos;
        }
    }
    
    public void setShortBreak(int minutos) {
        if (minutos > 0 && minutos <= 60) {
            this.shortBreakDuration = minutos;
        }
    }
    
    public void setLongBreak(int minutos) {
        if (minutos > 0 && minutos <= 120) {
            this.longBreakDuration = minutos;
        }
    }
    
    // Verificar si es configuración por defecto
    public boolean esConfiguracionPorDefecto() {
        return workDuration == 25 
            && shortBreakDuration == 5
            && longBreakDuration == 15;
    }
}