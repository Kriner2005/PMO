/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package uptc.edu.co.models.session;

/**
 *
 * @author alber
 */
public enum PomodoroType {
    WORK("Trabajo", 50),
    SHORT_BREAK("Descanso corto", 10),
    LONG_BREAK("Descanso largo", 15);

    private String displayName;
    private int defaultDuration;

    PomodoroType(String name, int duration) {
        this.displayName = name;
        this.defaultDuration = duration;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getDefaultDuration() {
        return defaultDuration;
    }

    public void setDefaultDuration(int defaultDuration) {
        this.defaultDuration = defaultDuration;
    }

}
