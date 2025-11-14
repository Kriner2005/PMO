/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.session.Settings;
import uptc.edu.co.models.user.User;
import uptc.edu.co.view.View;
import uptc.edu.co.view.subVistas.AdminUser;
import uptc.edu.co.view.subVistas.EmergentWindow;

/**
 *
 * @author alber
 */
public class MainController implements ActionListener {

    private User currentUserLogged;
    private Session currentSession;
    private final View view;

    // Subcontroladores
    private final PersistenceManager persistenceManager;
    private final AuthController authController;
    private final HelpController helpController;
    private ReportController reportController;
    private final SettingsController settingsController;
    private final TaskController taskController;
    private final TimerController timerController;
    private AdminController adminController = null;

    public MainController() {
        persistenceManager = new PersistenceManager();
        // 1. Crear sesión inicial (anonima)
        currentSession = new Session();

        // 2. Crear vista principal
        view = new View(this);

        // 3. Crear subcontroladores
        authController = new AuthController(this);
        helpController = new HelpController();
        reportController = new ReportController();
        settingsController = new SettingsController(currentSession,this);
        taskController = new TaskController(currentSession);
        timerController = new TimerController(view, currentSession);
        if (currentUserLogged == null) {
            view.getAdminBtn().setVisible(false);
        }else{
            view.getAdminBtn().setVisible(true);
        }
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
        view.getAdminBtn().addActionListener(this);
        view.getLeftTarea().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionPerformed(new ActionEvent(view.getLeftTarea(), ActionEvent.ACTION_PERFORMED, "panelTarea"));
            }
        });
//        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == view.getBtnHelp()) {
            mostrarAyuda();
        } else if (src == view.getReportBtn()) {
            abrirPanelReportes();
        } else if (src == view.getSettingBtn()) {
            abrirConfiguracion();
        } else if (src == view.getBtnLogin()) {
            abrirPanelUsuario();
        } else if (src == view.getLeftTarea()) {
            abrirTareas();
        } else if (src == view.getAdminBtn()) {
            adminUser();
        }
    }

    // -------------------------
    // Métodos de navegación
    // -------------------------
    
    private void adminUser() {
        AdminUser vista = new AdminUser();
        AdminController controller = new AdminController(vista, currentUserLogged);
        vista.setControlador(controller);
        controller.cargarUsuarios();
        controller.getVista().setVisible(true);
    }
    private void abrirPanelUsuario() {
        authController.abrirLogin();
        view.setVisible(false);
        authController.getViewMain(view);
    }

    private void abrirPanelReportes() {
        reportController.getView().setVisible(true);
    }

    private void abrirConfiguracion() {
        settingsController.show();
    }

    private void abrirTareas() {
        taskController.getTaskPanel().getParentFrame().setVisible(true);
        System.out.println("uptc.edu.co.controllers.MainController.abrirTareas()");
    }

    private void mostrarAyuda() {
        helpController.abrirHelp();
    }

    // ---------------------------------------
    // Actualizar session cuando el usuario loguea
    // ---------------------------------------
    public void setCurretnUserLogged(User curretnUserLogged) {
        this.currentUserLogged = curretnUserLogged;
        System.out.println("eee" + curretnUserLogged);
        if (curretnUserLogged != null) {
            this.currentSession = persistenceManager.loadSession(curretnUserLogged.getId());
            settingsController.setSession(currentSession);
            taskController.setSession(currentSession);
            timerController.setSession(currentSession);
            taskController.setSession(currentSession);
            taskController.reload();
            view.getBtnLogin().removeActionListener(this);

            System.out.println("usuario de la clase session controller:" + curretnUserLogged);
        } else {
            System.out.println("session nulo ");
        }
        System.out.println("MainController - curreSession: " + currentSession + " hash:" + System.identityHashCode(currentSession));
        System.out.println("MainController - loggedUser: " + curretnUserLogged + " hash:" + (curretnUserLogged != null ? System.identityHashCode(curretnUserLogged) : "null"));

    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void createSession() {
        String sessionName = JOptionPane.showInputDialog(
                view,
                "Ingrese un nombre para la sesión:",
                "Nueva Sesión",
                JOptionPane.QUESTION_MESSAGE
        );

        if (sessionName == null || sessionName.trim().isEmpty()) {
            new EmergentWindow("ERROR", "Debe ingresar un nombre válido.", 1);
        }

        currentSession = new Session(this.currentUserLogged, sessionName);

        // Cargar configuración (por ahora la default de Settings)
        Settings settings = currentSession.getSettings();

        // Actualizar título en la vista
        view.setTitle("Pomodoro - Sesión: " + sessionName);
        new EmergentWindow("Sesión iniciada", "Sesión creada exitosamente.\nDuración trabajo: "
                + settings.getWorkDuration() + " min.", 2);
        persistenceManager.saveSession(currentUserLogged.getId(), currentSession);
    }

    public User getCurrentUserLogged() {
        return currentUserLogged;
    }
    
    
}
