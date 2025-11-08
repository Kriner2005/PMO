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
    private Session curreSession;
    private TimerController timerController;
    private View mainView;  // referencia a la vista principal

    public void run() {
        arranque();
    }

    private void arranque() {
        // Crear el TimerController y su vista
        timerController = new TimerController();
        timerController.arranque();

        // Obtener la vista principal desde el TimerController
        mainView = timerController.getVista();

        // Agregar listeners a los botones globales (menu superior)
        configurarEventosPrincipales();

    }

    private void configurarEventosPrincipales() {
        // Estos botones están definidos en View
        mainView.getUserBtn().addActionListener(this);
        mainView.getReportBtn().addActionListener(this);
        mainView.getSettingBtn().addActionListener(this);
        mainView.getHelpBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        switch (e.getActionCommand()) {
            case "SHOW_RANKING" -> {

            }
            case "SHOW_STATISTICS" -> {

            }
        }
    }

    private void abrirPanelUsuario() {
        System.out.println("→ Abrir panel de usuario");
        // Aquí luego puedes crear e invocar el UserController
        // new UserController(currentUserLogged).mostrar();
    }

    private void abrirPanelReportes() {
        System.out.println("→ Abrir panel de reportes");
        // new ReportController(currentSession).mostrar();
    }

    private void abrirConfiguracion() {
        System.out.println("→ Abrir configuración");
        // new SettingsController().mostrar();
    }

    private void mostrarAyuda() {
        System.out.println("→ Mostrar ayuda o FAQ");
        // Podría ser un modal con información
    }

}
