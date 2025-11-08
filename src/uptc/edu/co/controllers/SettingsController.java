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
    private Settings settingsTimer;
    private Session session;

    public SettingsController() {

        this.view = new SettingsView(this);
        view.setVisible(true);
        settingsTimer = new Settings();
        session = new Session();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "CHANGE_LANGUAGE" -> {
            }
            case "SET_POMODORO", "SET_SHORT_BREAK", "SET_LONG_BREAK" -> changeTime();
            case "RESET_BUTTON" -> reset();
            case "HOME_BUTTON_SETTINGS" -> {
            }
            case "STATISTICS_BUTTON_SETTINGS" -> {
            }
            case "CONFIGURATION_BUTTON_SETTINGS" -> {
            }
            default -> throw new AssertionError();
        }
        // changeLanguage(settings.getLanguageID());
        //ir a ventana principal
        //ir a estadísticas
        
    }

    public void reset() {

        view.setPomodoroField("25");
        view.setShortBreakField("5");
        view.setLongBreakField("15");

        settingsTimer.customSettings(25, 5, 15);
        session.setSettings(settingsTimer);

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

        settingsTimer.customSettings(pomodoro, shortBreak, longBreak);
        session.setSettings(settingsTimer);

        System.out.println("Tiempos actualizados correctamente");
    }

    public static void main(String[] args) {
        SettingsController c = new SettingsController();
    }

}
