
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.view.subVistas.AddTaskDialog;
import uptc.edu.co.view.subVistas.TaskPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author alber
 */
public class TestUser implements ActionListener {

    private TaskPanel panel;
    private Session session;

    public TestUser() {
        this.panel = new TaskPanel(this);
        this.session = new Session();
        List<String> lista = session.getTaskList();
        lista.add("ingles");
        lista.add("español");
        lista.add("matematicas");

        for (String task : lista) {
            panel.addTask(task, false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD_TASK") && panel != null) {
            AddTaskDialog dialog = new AddTaskDialog(panel.getParentFrame());
            dialog.setVisible(true);

            String task = dialog.getTask();
            if (task != null && !task.trim().isEmpty()) {
                session.getTaskList().add(task);
                panel.addTask(task, false);
                System.out.println(session.getTaskList().toString());
            }
        }
        
        if (e.getActionCommand() == "MARK_DONE") {
            panel.markTaskDone((JButton)e.getSource());
        }
    }

    public static void main(String[] args) throws IOException {
        new TestUser();
    }
}
