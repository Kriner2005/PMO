/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.view.subVistas.AddTaskDialog;
import uptc.edu.co.view.subVistas.TaskPanel;

/**
 *
 * @author alber
 */
public class TaskController implements ActionListener {

    private final TaskPanel taskPanel;
    private Session session;

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

        List<String> taskList = session.getTaskList();

        if (taskList == null) {
            session.setTaskList(new java.util.ArrayList<>());
            return;
        }

        for (String task : taskList) {
            taskPanel.addTask(task, false);
        }
    }

    private void addTAsk() {
        AddTaskDialog dialog = new AddTaskDialog(taskPanel.getParentFrame());
        dialog.setVisible(true);
        String task = dialog.getTask();

        if (task != null && !task.trim().isEmpty()) {
            taskPanel.addTask(task, false);

            // GUARDAR EN LA SESSION
            session.getTaskList().add(task);

            // GUARDAR EN JSON
            new PersistenceManager().saveSession(session.getSessionId(), session);
        }
    }

    public void reload() {
        taskPanel.clearTasks();
        loadTask();
    }

    private void markTask(ActionEvent e) {
        taskPanel.markTaskDone((JButton) e.getSource());
    }

    public TaskPanel getTaskPanel() {
        return taskPanel;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
