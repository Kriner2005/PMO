package uptc.edu.co.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.session.Settings;
import uptc.edu.co.view.subVistas.SettingsView;

/**
 *
 * @author alber
 */
public class SettingsController implements ActionListener {

    private SettingsView view;
    private Session session;

    public SettingsController(Session session) {

        this.view = new SettingsView(this);
        this.session = session;

        Settings currentSettings = session.getSettings();
        view.setPomodoroField(String.valueOf(currentSettings.getWorkDuration()));
        view.setShortBreakField(String.valueOf(currentSettings.getShortBreakDuration()));
        view.setLongBreakField(String.valueOf(currentSettings.getLongBreakDuration()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "CHANGE_LANGUAGE" -> {
            }
            case "SET_POMODORO", "SET_SHORT_BREAK", "SET_LONG_BREAK" ->
                changeTime();
            case "RESET_BUTTON" ->
                reset();
            case "HOME_BUTTON_SETTINGS" -> {
            }
            case "STATISTICS_BUTTON_SETTINGS" -> {
            }
            case "CONFIGURATION_BUTTON_SETTINGS" -> {
            }
            default ->
                throw new AssertionError();
        }
        // changeLanguage(settings.getLanguageID());
        //ir a ventana principal
        //ir a estadísticas

    }

    public void reset() {

        view.setPomodoroField("25");
        view.setShortBreakField("5");
        view.setLongBreakField("15");

        Settings defaultSettings = new Settings(25, 5, 15);
        session.setSettings(defaultSettings); // Esto notificará automáticamente al TimerController

    }

    public void changeTime() {

        int pomodoro, shortBreak, longBreak;

        try {
            pomodoro = Integer.parseInt(view.getPomodoroField());
            if (pomodoro <= 0 || pomodoro > 180) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            pomodoro = 25;
            view.setPomodoroField("25");
        }

        try {
            shortBreak = Integer.parseInt(view.getShortBreakField());
            if (shortBreak <= 0 || shortBreak > 180) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            shortBreak = 5;
            view.setShortBreakField("5");
        }

        try {
            longBreak = Integer.parseInt(view.getLongBreakField());
            if (longBreak <= 0 || longBreak > 180) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            longBreak = 15;
            view.setLongBreakField("15");
        }

        Settings newSettings = new Settings(pomodoro, shortBreak, longBreak);
        session.setSettings(newSettings); // Esto notificará automáticamente al TimerController

        System.out.println("Tiempos actualizados correctamente");
    }
    
     public void setSession(Session newSession) {
        this.session = newSession;
        
        // Actualizar vista con las configuraciones de la nueva sesión
        Settings settings = newSession.getSettings();
        view.setPomodoroField(String.valueOf(settings.getWorkDuration()));
        view.setShortBreakField(String.valueOf(settings.getShortBreakDuration()));
        view.setLongBreakField(String.valueOf(settings.getLongBreakDuration()));
    }

    public SettingsView getView() {
        return view;
    }

}
