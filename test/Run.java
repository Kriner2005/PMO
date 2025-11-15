/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alber
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import uptc.edu.co.controllers.AdminController;
import uptc.edu.co.controllers.GenerarDatosPrueba;
import uptc.edu.co.controllers.MainController;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.user.Role;
import uptc.edu.co.models.user.User;
import uptc.edu.co.view.View;
import uptc.edu.co.view.subVistas.AdminUser;

public class Run {

    public static void main(String[] args) throws IOException {
        new GenerarDatosPrueba().generarDatosPrueba();
    }
}
