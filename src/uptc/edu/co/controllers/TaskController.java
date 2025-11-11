/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.view.subVistas.AddTaskDialog;
import uptc.edu.co.view.subVistas.TaskPanel;

/**
 *
 * @author alber
 */
public class TaskController implements ActionListener {

    private final TaskPanel taskPanel;
    private final Session session;

    public TaskController(Session session) {
        this.taskPanel = new TaskPanel(this);
        this.session = session;
        loadTask();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ADD_TASK" -> {
                addTAsk();
            }

            case "MARK_DONE" -> {
                markTask(e);
            }
            default ->
                throw new AssertionError();
        }
    }

    private void loadTask() {
        
        List<String> taskList = session.getTaskList() != null ? session.getTaskList(): null;
        
        if (!taskList.isEmpty() || taskList == null ) {
            for (String task : taskList) {
                taskPanel.addTask(task, false);
            }
        }
    }

    private void addTAsk() {
        AddTaskDialog dialog = new AddTaskDialog(taskPanel.getParentFrame());
        dialog.setVisible(true);
        String task = dialog.getTask();
        if (task != null && !task.trim().isEmpty()) {
            taskPanel.addTask(task, false);
        }
    }
    
    private void markTask(ActionEvent e) {
         taskPanel.markTaskDone((JButton)e.getSource());
    }

    public TaskPanel getTaskPanel() {
        return taskPanel;
    }
    
    

}
