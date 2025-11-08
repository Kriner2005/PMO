
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.session.PomodoroRecord;
import uptc.edu.co.models.session.PomodoroType;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.session.Settings;
import uptc.edu.co.models.session.SettingsPreset;
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
public class TestRanking {

    public static void main(String[] args) throws IOException {
        User user1 = new User(1, "keiner", "", "", Role.USER);

        PersistenceManager manager = new PersistenceManager();
        manager.addUser(user1);
        //creo la session
        Session auxSession = new Session(user1, "ingles");
        //paso la configuración previa
        Settings config = new Settings();
        config.loadPreset(SettingsPreset.CLASSIC);
        auxSession.initializeSession();
        auxSession.setSessionId(0);
        auxSession.finalizeSession();
        auxSession.addPomodoroRecord(new PomodoroRecord(PomodoroType.WORK, auxSession.getStartTime(), auxSession.getEndTime(), false, SettingsPreset.CLASSIC.workDuration));
        auxSession.addPomodoroRecord(new PomodoroRecord(PomodoroType.WORK, auxSession.getStartTime(), auxSession.getEndTime(), false, SettingsPreset.CLASSIC.workDuration));
        auxSession.addPomodoroRecord(new PomodoroRecord(PomodoroType.WORK, auxSession.getStartTime(), auxSession.getEndTime(), false, SettingsPreset.CLASSIC.workDuration));
        manager.saveSession(user1.getId(), auxSession);
        
        User user2 = new User(2, "sara", "", "", Role.USER);
        
            manager.addUser(user2);
        //creo la session
        Session auxSession2 = new Session(user2, "ingles");
        //paso la configuración previa
        config.loadPreset(SettingsPreset.CLASSIC);
        auxSession2.initializeSession();
        auxSession2.setSessionId(0);
        auxSession2.finalizeSession();
        auxSession2.addPomodoroRecord(new PomodoroRecord(PomodoroType.WORK, auxSession2.getStartTime(), auxSession2.getEndTime(), false, SettingsPreset.CLASSIC.workDuration));
        auxSession2.addPomodoroRecord(new PomodoroRecord(PomodoroType.WORK, auxSession2.getStartTime(), auxSession2.getEndTime(), false, SettingsPreset.CLASSIC.workDuration));
        auxSession.addPomodoroRecord(new PomodoroRecord(PomodoroType.WORK, auxSession2.getStartTime(), auxSession2.getEndTime(), false, SettingsPreset.CLASSIC.workDuration));
        manager.saveSession(user2.getId(), auxSession);
        
        User user3 = new User(3, "rosa", "", "", Role.USER);
        
        manager.addUser(user3);
        //creo la session
        Session auxSession3 = new Session(user2, "ingles");
        //paso la configuración previa
        config.loadPreset(SettingsPreset.CLASSIC);
        auxSession3.initializeSession();
        auxSession3.setSessionId(0);
        auxSession3.finalizeSession();
        auxSession3.addPomodoroRecord(new PomodoroRecord(PomodoroType.WORK, auxSession3.getStartTime(), auxSession3.getEndTime(), false, SettingsPreset.CLASSIC.workDuration));
        auxSession3.addPomodoroRecord(new PomodoroRecord(PomodoroType.WORK, auxSession3.getStartTime(), auxSession3.getEndTime(), false, SettingsPreset.CLASSIC.workDuration));
        auxSession3.addPomodoroRecord(new PomodoroRecord(PomodoroType.WORK, auxSession3.getStartTime(), auxSession3.getEndTime(), false, SettingsPreset.CLASSIC.workDuration));
        manager.saveSession(user3.getId(), auxSession);

    }

}
