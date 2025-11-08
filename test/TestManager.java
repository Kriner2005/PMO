
import java.io.IOException;
import java.time.LocalDateTime;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.session.PomodoroRecord;
import uptc.edu.co.models.session.PomodoroType;
import uptc.edu.co.models.session.Session;
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
public class TestManager {

    public static void main(String[] args) throws IOException {
        PersistenceManager manager = new PersistenceManager();
        User u = manager.loadUser("a");
        Session s = manager.loadSession(u.getId());
        s.addPomodoroRecord(new PomodoroRecord(PomodoroType.LONG_BREAK, LocalDateTime.MIN, LocalDateTime.MIN, true, 0));
        manager.saveSession(u.getId(), s);
        
    }
}
