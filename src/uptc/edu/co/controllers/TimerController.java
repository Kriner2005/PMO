package uptc.edu.co.controllers;

import javax.swing.SwingUtilities;
import uptc.edu.co.models.timer.PomodoroTimer;
import uptc.edu.co.models.timer.Timer;
import uptc.edu.co.models.session.TimerListener;

import java.awt.Color;
import javax.swing.JOptionPane;

import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.session.Settings;
import uptc.edu.co.models.session.SettingsListener;
import uptc.edu.co.utilities.CustomComponents;
import uptc.edu.co.view.View;

public class TimerController implements SettingsListener {

    private final View view;
    private Timer timerActual;
    private int tiempoTotal;
    private Session session;

    private PomodoroTimer pomodoroTimer;
    private PomodoroTimer shortBreakTimer;
    private PomodoroTimer longBreakTimer;

    private enum TipoTimer {
        POMODORO, SHORT_BREAK, LONG_BREAK
    }

    private TipoTimer tipoActual;

    // ============================================
    // CONSTRUCTOR
    // ============================================
    public TimerController(View view, Session session) {
        this.view = view;
        this.session = session;

        // Escuchar cambios de configuraciones
        session.addSettingsListener(this);

        // Crear timers usando los settings de la sesión
        Settings settings = session.getSettings();

        pomodoroTimer = new PomodoroTimer(settings.getWorkDurationInSeconds());
        shortBreakTimer = new PomodoroTimer(settings.getShortBreakDurationInSeconds());
        longBreakTimer = new PomodoroTimer(settings.getLongBreakDurationInSeconds());

        timerActual = pomodoroTimer;
        tipoActual = TipoTimer.POMODORO;
    }

    // ============================================
    // SE NOTIFICA UN CAMBIO EN LOS AJUSTES
    // ============================================
    @Override
    public void onSettingsChanged(Settings newSettings) {

        if (timerActual != null && timerActual.isRunning()) {
            timerActual.stop();
        }

        JOptionPane.showMessageDialog(view, "🔔 Timer actualizado con nuevas configuraciones");

        // Recrear timers con los NUEVOS valores
        pomodoroTimer = new PomodoroTimer(newSettings.getWorkDurationInSeconds());
        shortBreakTimer = new PomodoroTimer(newSettings.getShortBreakDurationInSeconds());
        longBreakTimer = new PomodoroTimer(newSettings.getLongBreakDurationInSeconds());

        configurarListenerTimer();

        // Mantener el modo actual, pero con nuevos tiempos
        switch (tipoActual) {
            case POMODORO:
                timerActual = pomodoroTimer;
                tiempoTotal = newSettings.getWorkDurationInSeconds();
                break;
            case SHORT_BREAK:
                timerActual = shortBreakTimer;
                tiempoTotal = newSettings.getShortBreakDurationInSeconds();
                break;
            case LONG_BREAK:
                timerActual = longBreakTimer;
                tiempoTotal = newSettings.getLongBreakDurationInSeconds();
                break;
        }

        actualizarTiempoEnVista(tiempoTotal);
        view.updateProgressBar(0);
        cambiarIconoStart(false);
    }

    // ============================================
    // CAMBIO COMPLETO DE SESIÓN
    // ============================================
    public void setSession(Session newSession) {

        if (this.session != null) {
            this.session.removeSettingsListener(this);
        }

        this.session = newSession;
        newSession.addSettingsListener(this);

        // Aplicar sus configuraciones
        onSettingsChanged(newSession.getSettings());
    }

    // ============================================
    // ARRANQUE DEL CONTROLADOR
    // ============================================
    public void arranque() {
        configurarEventosVista();
        configurarListenerTimer();
        aplicarEstilosPomodoro();
    }

    // ============================================
    // EVENTOS DE LA VISTA
    // ============================================
    private void configurarEventosVista() {

        view.getBtnStart().addActionListener(e -> accionStartPause());
        view.getBtnReset().addActionListener(e -> accionReset());

        view.getBtnPomodoro().addActionListener(e -> cambiarAPomodoro());
        view.getBtnShortBreak().addActionListener(e -> cambiarAShortBreak());
        view.getBtnLongBreak().addActionListener(e -> cambiarALongBreak());
    }

    // ============================================
    // LISTENER DEL TIMER
    // ============================================
    private void configurarListenerTimer() {

        TimerListener listener = new TimerListener() {
            @Override
            public void onTick(int segundosRestantes) {
                actualizarTiempoEnVista(segundosRestantes);
                actualizarBarraProgreso(segundosRestantes);
            }

            @Override
            public void onFinish() {
                manejarFinalizacion();
            }
        };

        pomodoroTimer.addListener(listener);
        shortBreakTimer.addListener(listener);
        longBreakTimer.addListener(listener);
    }

    // ============================================
    // BOTÓN START / PAUSE
    // ============================================
    private void accionStartPause() {

        if (timerActual.isRunning() && !timerActual.isPaused()) {
            timerActual.pause();
            cambiarIconoStart(false);

        } else if (timerActual.isPaused()) {
            timerActual.resume();
            cambiarIconoStart(true);

        } else {
            timerActual.start();
            cambiarIconoStart(true);
        }
    }

    // ============================================
    // BOTÓN RESET
    // ============================================
    private void accionReset() {
        timerActual.stop();
        timerActual.reset();
        actualizarTiempoEnVista(tiempoTotal);
        view.updateProgressBar(0);
        cambiarIconoStart(false);
    }

    // ============================================
    // CAMBIAR MODOS
    // ============================================
    private void cambiarAPomodoro() {
        detenerTimerActual();

        tipoActual = TipoTimer.POMODORO;
        timerActual = pomodoroTimer;
        tiempoTotal = session.getSettings().getWorkDurationInSeconds();

        actualizarTiempoEnVista(tiempoTotal);
        view.updateProgressBar(0);
        aplicarEstilosPomodoro();
        cambiarIconoStart(false);
    }

    private void cambiarAShortBreak() {
        detenerTimerActual();

        tipoActual = TipoTimer.SHORT_BREAK;
        timerActual = shortBreakTimer;
        tiempoTotal = session.getSettings().getShortBreakDurationInSeconds();

        actualizarTiempoEnVista(tiempoTotal);
        view.updateProgressBar(0);
        aplicarEstilosShortBreak();
        cambiarIconoStart(false);
    }

    private void cambiarALongBreak() {
        detenerTimerActual();

        tipoActual = TipoTimer.LONG_BREAK;
        timerActual = longBreakTimer;
        tiempoTotal = session.getSettings().getLongBreakDurationInSeconds();

        actualizarTiempoEnVista(tiempoTotal);
        view.updateProgressBar(0);
        aplicarEstilosLongBreak();
        cambiarIconoStart(false);
    }

    private void detenerTimerActual() {
        if (timerActual != null && timerActual.isRunning()) {
            timerActual.stop();
        }
    }

    // ============================================
    // ACTUALIZACIONES DE VISTA
    // ============================================
    private void actualizarTiempoEnVista(int segundos) {
        SwingUtilities.invokeLater(() -> {
            int minutos = segundos / 60;
            int segs = segundos % 60;
            view.updateTimeLabel(String.format("%02d:%02d", minutos, segs));
        });
    }

    private void actualizarBarraProgreso(int segundosRestantes) {

        int progreso = (int) (((tiempoTotal - segundosRestantes) / (double) tiempoTotal) * 100);

        SwingUtilities.invokeLater(() -> view.updateProgressBar(progreso));
    }

    private void cambiarIconoStart(boolean running) {
        SwingUtilities.invokeLater(() -> view.updateStartButtonIcon(running));
    }

    // ============================================
    // FINALIZACIÓN DEL TIMER
    // ============================================
    private void manejarFinalizacion() {

        SwingUtilities.invokeLater(() -> {

            JOptionPane.showMessageDialog(view,
                    obtenerMensajeFinalizacion(),
                    "Timer completado",
                    JOptionPane.INFORMATION_MESSAGE
            );

            accionReset();
        });
    }

    private String obtenerMensajeFinalizacion() {
        switch (tipoActual) {
            case POMODORO:
                return "¡Pomodoro completado!";
            case SHORT_BREAK:
                return "¡Descanso corto completado!";
            case LONG_BREAK:
                return "¡Descanso largo completado!";
            default:
                return "¡Timer completado!";
        }
    }

    // ============================================
    // ESTILOS
    // ============================================
    private void aplicarEstilosPomodoro() {
        aplicarColores(new Color(194, 11, 11),
                new Color(205, 92, 92),
                new Color(139, 0, 0));
    }

    private void aplicarEstilosShortBreak() {
        aplicarColores(new Color(52, 124, 129),
                new Color(52, 124, 129),
                new Color(32, 94, 99));
    }

    private void aplicarEstilosLongBreak() {
        aplicarColores(new Color(57, 112, 151),
                new Color(57, 112, 151),
                new Color(37, 82, 121));
    }

    private void aplicarColores(Color paneles, Color fondo, Color borde) {
        SwingUtilities.invokeLater(() -> {

            view.updatePanelColors(paneles);

            CustomComponents.hacerPanelRedondeado(
                    view.getCenterPanel(),
                    25,
                    fondo,
                    borde,
                    3
            );

            view.updateProgressBarColor(borde);
            view.repaintComponents();
        });
    }

    public Timer getTimerActual() {
        return timerActual;
    }

    public View getVista() {
        return view;
    }
}
