/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.user.User;
import uptc.edu.co.view.View;

/**
 *
 * @author alber
 */
public class MainController implements ActionListener {

    private User curretnUserLogged;
    private final Session curreSession;
    private final View view;  // referencia a la vista principal

    // subControllers
    private final AuthController authController;
    private final HelpController helpController;
    private ReportController reportController;
    private final SessionController sessionController;
    private final SettingsController settingsController;
    private final TaskController taskController;
    private final TimerController timerController;

    public MainController() {
        view = new View(this);
        authController = new AuthController();
        helpController = new HelpController();
        reportController = new ReportController();
        sessionController = new SessionController(view, curretnUserLogged);
        settingsController = new SettingsController();
        curreSession = new Session();
        taskController = new TaskController(curreSession);
        timerController = new TimerController(view);
        timerController.arranque();

    }

    public void run() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        configurarEventosPrincipales();
    }

    private void configurarEventosPrincipales() {
        // Estos botones están definidos en View
        view.getUserBtn().addActionListener(this);
        view.getReportBtn().addActionListener(this);
        view.getSettingBtn().addActionListener(this);
//        view.getBtnTasks().add;
        view.getHelpBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == view.getBtnHelp()) {
            mostrarAyuda();
        }

        if (src == view.getReportBtn()) {
            abrirPanelReportes();
        }

        if (src == view.getSettingBtn()) {
            abrirConfiguracion();
        }

        if (src == view.getUserBtn()) {
            abrirPanelUsuario();
        }

        if (src == view.getBtnTasks()) {
            System.out.println("XD");
            abrirTareas();
        }
    }

    private void abrirPanelUsuario() {
        authController.getLogin().setVisible(true);
    }

    private void abrirPanelReportes() {
        reportController = new ReportController();
        reportController.getView().setVisible(true);
        // new ReportController(currentSession).mostrar();
    }

    private void abrirConfiguracion() {
        settingsController.getView().setVisible(true);
    }

    private void abrirTareas() {
        taskController.getTaskPanel().setVisible(true);
    }

    private void mostrarAyuda() {
        HelpController controller = new HelpController();
        controller.abrirHelp();

    }

}
