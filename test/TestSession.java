
import java.io.IOException;
import java.util.List;

import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.session.PomodoroRecord;
import uptc.edu.co.models.session.PomodoroType;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.session.Settings;
import uptc.edu.co.models.session.SettingsPreset;
import uptc.edu.co.models.session.UserHistory;
import uptc.edu.co.models.user.Role;
import uptc.edu.co.models.user.User;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author alber
 */
public class TestSession {

    public static void main(String[] args) throws IOException, InterruptedException {
        User user = new User(2, "sara", "@gmail", "asd", Role.USER);
        PersistenceManager manager = new PersistenceManager();
        manager.addUser(user);
        //creo la session
        Session auxSession = new Session(user, "ingles");
        //paso la configuración previa
        Settings config = new Settings();
        config.loadPreset(SettingsPreset.CLASSIC);
        auxSession.initializeSession();
        auxSession.setSessionId(0);
        auxSession.finalizeSession();
        auxSession.addPomodoroRecord(new PomodoroRecord(PomodoroType.WORK, auxSession.getStartTime(), auxSession.getEndTime(), false, SettingsPreset.CLASSIC.workDuration));
        manager.saveSession(user.getId(), auxSession);
    }
}
