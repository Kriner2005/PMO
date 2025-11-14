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

    private User currentUserLogged;
    private Session currentSession;
    private final View view;

    // Subcontroladores
    private final AuthController authController;
    private final HelpController helpController;
    private ReportController reportController;
    private final SettingsController settingsController;
    private final TaskController taskController;
    private final TimerController timerController;

    public MainController() {

        // 1. Crear sesión inicial (anonima)
        currentSession = new Session(null, "Sesión anonima");

        // 2. Crear vista principal
        view = new View(this);

        // 3. Crear subcontroladores
        authController = new AuthController();
        helpController = new HelpController();
        reportController = new ReportController();
        settingsController = new SettingsController(currentSession);
        taskController = new TaskController(currentSession);
        timerController = new TimerController(view, currentSession);

        // 4. Arrancar timer
        timerController.arranque();
    }

    public void run() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        configurarEventosPrincipales();
    }

    private void configurarEventosPrincipales() {
        view.getUserBtn().addActionListener(this);
        view.getReportBtn().addActionListener(this);
        view.getSettingBtn().addActionListener(this);
        view.getHelpBtn().addActionListener(this);
//        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == view.getBtnHelp()) mostrarAyuda();
        else if (src == view.getReportBtn()) abrirPanelReportes();
        else if (src == view.getSettingBtn()) abrirConfiguracion();
        else if (src == view.getBtnLogin()) abrirPanelUsuario();
        else if (src == view.getLeftTarea()) abrirTareas();
    }

    // -------------------------
    // Métodos de navegación
    // -------------------------

    private void abrirPanelUsuario() {
        authController.getLogin().setVisible(true);
    }

    private void abrirPanelReportes() {
        reportController.getView().setVisible(true);
    }

    private void abrirConfiguracion() {
        settingsController.show();
    }

    private void abrirTareas() {
        taskController.getTaskPanel().setVisible(true);
        System.out.println("uptc.edu.co.controllers.MainController.abrirTareas()");
    }

    private void mostrarAyuda() {
        helpController.abrirHelp();
    }

    // ---------------------------------------
    // Actualizar session cuando el usuario loguea
    // ---------------------------------------
    public void setCurrentUser(User user) {

        this.currentUserLogged = user;

        // Crear nueva sesión
        currentSession = new Session(user, "Sesión de " + user.getName());
        currentSession.initializeSession();

        // Enviar nueva session a controladores dependientes
        settingsController.setSession(currentSession);
        timerController.setSession(currentSession);
        taskController.setSession(currentSession);
    }

    public Session getCurrentSession() {
        return currentSession;
    }
}
