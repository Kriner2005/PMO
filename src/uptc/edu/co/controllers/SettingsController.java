package uptc.edu.co.controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PersistenceDelegate;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.session.Settings;
import uptc.edu.co.view.subVistas.SettingsView;


public class SettingsController implements ActionListener {

    private final SettingsView view;
    private Session session;
    private MainController main;

    public SettingsController(Session session, MainController main) {
        this.view = new SettingsView(this);
        this.session = session;
        this.main = main;
        cargarValoresActuales();
    }

    private void cargarValoresActuales() {
        Settings s = session.getSettings();
        view.setPomodoroField(String.valueOf(s.getWorkDuration()));
        view.setShortBreakField(String.valueOf(s.getShortBreakDuration()));
        view.setLongBreakField(String.valueOf(s.getLongBreakDuration()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "SET_POMODORO", "SET_SHORT_BREAK", "SET_LONG_BREAK" ->
                aplicarCambios();
            case "RESET_BUTTON" ->
                reset();
        }
    }

    private void aplicarCambios() {

        int work = Integer.parseInt(view.getPomodoroField());
        int sb = Integer.parseInt(view.getShortBreakField());
        int lb = Integer.parseInt(view.getLongBreakField());

        Settings newSettings = new Settings(work, sb, lb);
        session.setSettings(newSettings); // <---- Notifica automáticamente
        new PersistenceManager().saveSession(main.getCurrentUserLogged().getId(), session);
    }

    public void reset() {
        Settings defaultSettings = new Settings(25, 5, 15);
        session.setSettings(defaultSettings);

        cargarValoresActuales();
    }

    public void setSession(Session newSession) {
        this.session = newSession;
        cargarValoresActuales();
    }

    public void show() {
        view.setVisible(true);
    }
}
