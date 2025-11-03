/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.session;

/**
 *
 * @author alber
 */
public enum PomodoroType {
    WORK("Trabajo", 25),
    SHORT_BREAK("Descanso corto", 5),
    LONG_BREAK("Descanso largo", 15);

    private final String displayName;
    private final int defaultDuration; // referencia base (opcional)

    PomodoroType(String name, int duration) {
        this.displayName = name;
        this.defaultDuration = duration;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getDefaultDuration() {
        return defaultDuration;
    }
}
