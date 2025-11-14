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
import uptc.edu.co.controllers.MainController;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.user.Role;
import uptc.edu.co.models.user.User;
import uptc.edu.co.view.View;
import uptc.edu.co.view.subVistas.AdminUser;

public class Run {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // 1. Crear datos de prueba
//                crearDatosDePrueba();

                // 2. Usuario admin para la prueba
                User adminDePrueba = new PersistenceManager().loadUser("c@gmail.com");
                System.out.println(adminDePrueba.getName());

                // 3. Crear vista y controlador
                AdminUser vista = new AdminUser();
                AdminController controlador = new AdminController(vista, adminDePrueba);
                vista.setControlador(controlador);

                // 4. Cargar usuarios
                controlador.cargarUsuarios();

                // 5. Mostrar
                vista.setVisible(true);

                System.out.println("✅ Sistema iniciado con datos de prueba");

            } catch (Exception e) {
                System.err.println("❌ Error al iniciar: " + e.getMessage());
                e.printStackTrace();
            }
        });

    }
}
