package uptc.edu.co.controllers;

import javax.swing.JOptionPane;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.session.Settings;
import uptc.edu.co.models.user.User;
import uptc.edu.co.view.View;

/**
 * Controlador de gestión de sesiones. Coordina la creación, finalización y
 * carga de historial entre los modelos y la vista principal.
 */
public class SessionController {

    private Session activeSession;
    private final PersistenceManager persistenceManager;
    private final View view;
    private Session userHistory;
    private final User currentUser;

    /**
     * Constructor principal del controlador de sesión.
     *
     * @param view la vista principal
     * @param user el usuario autenticado
     */
    public SessionController(View view, User user) {
        this.view = view;
        this.currentUser = user;
        this.persistenceManager = new PersistenceManager();
        this.userHistory = persistenceManager.loadSession(user.getId());
    }

    /**
     * Crea una nueva sesión: pide nombre, inicializa y actualiza la vista.
     */
    public Session createSession() {
        String sessionName = JOptionPane.showInputDialog(
                view,
                "Ingrese un nombre para la sesión:",
                "Nueva Sesión",
                JOptionPane.QUESTION_MESSAGE
        );

        if (sessionName == null || sessionName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Debe ingresar un nombre válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        activeSession = new Session(currentUser, sessionName);

        // Cargar configuración (por ahora la default de Settings)
        Settings settings = activeSession.getSettings();

        // Actualizar título en la vista
        view.setTitle("Pomodoro - Sesión: " + sessionName);

        JOptionPane.showMessageDialog(view,
                "Sesión creada exitosamente.\nDuración trabajo: "
                + settings.getWorkDuration() + " min.",
                "Sesión iniciada",
                JOptionPane.INFORMATION_MESSAGE);
        return activeSession;
    }

    /**
     * Devuelve la sesión actualmente activa.
     *
     * @return
     */
    public Session getActiveSession() {
        return activeSession;
    }

    /**
     * Devuelve el historial actual del usuario.
     *
     * @return
     */
}
