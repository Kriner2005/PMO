/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.controllers;

import javax.swing.SwingUtilities;
import uptc.edu.co.models.timer.PomodoroTimer;
import uptc.edu.co.models.timer.Timer;
import uptc.edu.co.models.session.TimerListener;

import java.awt.Color;
import uptc.edu.co.models.session.Settings;
import uptc.edu.co.utilities.CustomComponents;
import uptc.edu.co.view.View;

/**
 *
 * @author alber
 */
public class TimerController {

    // Conexión vista y modelo 
    private final View view;
    private Timer timerActual;
    private int tiempoTotal;

    // Los tres modos del pomodoro
    private PomodoroTimer pomodoroTimer;
    private PomodoroTimer shortBreakTimer;
    private PomodoroTimer longBreakTimer;

    // Enumeración para tipos de timer
    private enum TipoTimer {
        POMODORO, SHORT_BREAK, LONG_BREAK
    }

    private final Settings setting;
    private TipoTimer tipoActual;

//    Constructor: inicializa los timers
    public TimerController(View view) {
        this.view = view;
//      valores predeterminados 
        setting = new Settings();

//      crear un timer con esas configuraciones establecidas en configuraciones
        // Se lee de settings y se convierte
        // Crear los tres tipos de timer  
        // 25 minutos
        pomodoroTimer = new PomodoroTimer(setting.getWorkDurationInSeconds());
        // 5 minutos
        shortBreakTimer = new PomodoroTimer(setting.getShortBreakDurationInSeconds());
        // 15 minutos
        longBreakTimer = new PomodoroTimer(setting.getLongBreakDurationInSeconds());

        // Por defecto, empezamos con Pomodoro
        timerActual = pomodoroTimer;
        tipoActual = TipoTimer.POMODORO;
    }

//    aplicar las nuevas configuraciones 
    public void aplicarNuevasConfigs(int workMinuto, int shortMinuto, int longMinuto) {

        // Detener timer actual
        if (timerActual.isRunning()) {
            timerActual.stop();
        }

        // Actualizar SettingsM (en MINUTOS)
        setting.setWork(workMinuto);
        setting.setShortBreak(shortMinuto);
        setting.setLongBreak(longMinuto);

        // Recrear timers (conversión a SEGUNDOS aquí)
        pomodoroTimer = new PomodoroTimer(setting.getWorkDurationInSeconds());
        shortBreakTimer = new PomodoroTimer(setting.getShortBreakDurationInSeconds());
        longBreakTimer = new PomodoroTimer(setting.getLongBreakDurationInSeconds());

        // Reconectar listeners
        configurarListenerTimer();

        // Actualizar el timer actual según el modo
        switch (tipoActual) {
            case POMODORO:
                timerActual = pomodoroTimer;
                tiempoTotal = setting.getWorkDurationInSeconds();
                break;
            case SHORT_BREAK:
                timerActual = shortBreakTimer;
                tiempoTotal = setting.getShortBreakDurationInSeconds();
                break;
            case LONG_BREAK:
                timerActual = longBreakTimer;
                tiempoTotal = setting.getLongBreakDurationInSeconds();
                break;
        }

        // Actualizar vista
        actualizarTiempoEnVista(tiempoTotal);
        view.updateProgressBar(0);
        cambiarIconoStart(false);

        // Mensaje de confirmación
        String mensaje = setting.esConfiguracionPorDefecto()
                ? "Configuración estándar de Pomodoro aplicada"
                : "Configuración personalizada aplicada";

        javax.swing.JOptionPane.showMessageDialog(view,
                mensaje,
                "Configuración",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }

//    Método principal que arranca la aplicación
    public void arranque() {
        // Configurar listeners de la vista
        configurarEventosVista();

        // Configurar listener del modelo
        configurarListenerTimer();

        // Aplicar estilos iniciales
        aplicarEstilosPomodoro();
    }

//    Configura los eventos de los botones de la vista
    private void configurarEventosVista() {
        // Botón Start/Pause
        view.getBtnStart().addActionListener(e -> accionStartPause());

        // Botón Reset
        view.getBtnReset().addActionListener(e -> accionReset());

//         Botones de cambio de modo
        view.getBtnPomodoro().addActionListener(e -> cambiarAPomodoro());
        view.getBtnShortBreak().addActionListener(e -> cambiarAShortBreak());
        view.getBtnLongBreak().addActionListener(e -> cambiarALongBreak());

    }

//    Configura el listener para recibir actualizaciones del timer
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

        // Agregar el listener a todos los timers
        pomodoroTimer.addListener(listener);
        shortBreakTimer.addListener(listener);
        longBreakTimer.addListener(listener);
    }

//    Acción del botón Start/Pause
    private void accionStartPause() {
        if (timerActual.isRunning() && !timerActual.isPaused()) {
            // Si está corriendo, pausar
            timerActual.pause();
            cambiarIconoStart(false);
        } else if (timerActual.isPaused()) {
            // Si está en pausa, reanudar
            timerActual.resume();
            cambiarIconoStart(true);
        } else {
            // Si está detenido, iniciar
            timerActual.start();
            cambiarIconoStart(true);
        }
    }

//  Acción del botón Reset
    private void accionReset() {
        timerActual.stop();
        timerActual.reset();
        actualizarTiempoEnVista(tiempoTotal);
        view.updateProgressBar(0);
        cambiarIconoStart(false);
    }

//      Cambiar a modo Pomodoro (25 min)
    private void cambiarAPomodoro() {
        detenerTimerActual();
        timerActual = pomodoroTimer;
        tipoActual = TipoTimer.POMODORO;
        tiempoTotal = setting.getWorkDurationInSeconds();
        actualizarTiempoEnVista(tiempoTotal);
        view.updateProgressBar(0);
        aplicarEstilosPomodoro();
        cambiarIconoStart(false);
    }

//    Cambiar a modo Short Break (5 min)
    private void cambiarAShortBreak() {
        detenerTimerActual();
        timerActual = shortBreakTimer;
        tipoActual = TipoTimer.SHORT_BREAK;
        tiempoTotal = setting.getShortBreakDurationInSeconds();
        actualizarTiempoEnVista(tiempoTotal);
        view.updateProgressBar(0);
        aplicarEstilosShortBreak();
        cambiarIconoStart(false);
    }

//  Cambiar a modo Long Break (15 min)
    private void cambiarALongBreak() {
        detenerTimerActual();
        timerActual = longBreakTimer;
        tipoActual = TipoTimer.LONG_BREAK;
        tiempoTotal = 900;
        tiempoTotal = setting.getLongBreakDurationInSeconds();
        actualizarTiempoEnVista(tiempoTotal);
        view.updateProgressBar(0);
        aplicarEstilosLongBreak();
        cambiarIconoStart(false);
    }

//  Detiene el timer actual de forma segura
    private void detenerTimerActual() {
        if (timerActual != null && timerActual.isRunning()) {
            timerActual.stop();
        }
    }

//    Actualiza el tiempo mostrado en la vista
    private void actualizarTiempoEnVista(int segundos) {
        SwingUtilities.invokeLater(() -> {
            int minutos = segundos / 60;
            int segs = segundos % 60;
            view.updateTimeLabel(String.format("%02d:%02d", minutos, segs));
        });
    }
//    Actualiza la barra de progreso

    private void actualizarBarraProgreso(int segundosRestantes) {
        SwingUtilities.invokeLater(() -> {
            int progreso = (int) (((tiempoTotal - segundosRestantes) / (double) tiempoTotal) * 100);
            view.updateProgressBar(progreso);
        });
    }

//  Cambia el ícono del botón Start (play/pause)
    private void cambiarIconoStart(boolean enEjecucion) {
        SwingUtilities.invokeLater(() -> {
            view.updateStartButtonIcon(enEjecucion);
        });
    }

//    Maneja la finalización del timer
    private void manejarFinalizacion() {
        SwingUtilities.invokeLater(() -> {
            String mensaje = obtenerMensajeFinalizacion();
            javax.swing.JOptionPane.showMessageDialog(view,
                    mensaje,
                    "Timer Completado",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
            accionReset();
        });
    }

//  Obtiene el mensaje apropiado según el tipo de timer
    private String obtenerMensajeFinalizacion() {
        switch (tipoActual) {
            case POMODORO:
                return "¡Pomodoro completado!\nToma un descanso.";

            case SHORT_BREAK:
                return "¡Descanso corto completado!\n¡A trabajar!";
            case LONG_BREAK:
                return "¡Descanso largo completado!\n¡Listo para continuar!";
            default:
                return "¡Timer completado!";
        }
    }

//ESTILOS 
    private void aplicarEstilosPomodoro() {
        Color fondo = new Color(205, 92, 92);
        Color borde = new Color(139, 0, 0);
        Color paneles = new Color(194, 11, 11);

        aplicarColores(paneles, fondo, borde);
    }

    private void aplicarEstilosShortBreak() {
        Color fondo = new Color(52, 124, 129);
        Color borde = new Color(32, 94, 99);
        Color paneles = new Color(52, 124, 129);

        aplicarColores(paneles, fondo, borde);
    }

    private void aplicarEstilosLongBreak() {
        Color fondo = new Color(57, 112, 151);
        Color borde = new Color(37, 82, 121);
        Color paneles = new Color(57, 112, 151);

        aplicarColores(paneles, fondo, borde);
    }

    private void aplicarColores(Color colorPaneles, Color fondoCenter, Color bordeCenter) {
        SwingUtilities.invokeLater(() -> {
            // Actualizar paneles principales
            view.updatePanelColors(colorPaneles);

            // Actualizar panel central con borde redondeado
            CustomComponents.hacerPanelRedondeado(view.getCenterPanel(),
                    25,
                    fondoCenter,
                    bordeCenter,
                    3
            );

            // Actualizar color de barra de progreso
            view.updateProgressBarColor(bordeCenter);

            // Repintar todo
            view.repaintComponents();
        });
    }
//    get y set 

    public Settings getSettings() {
        return setting;
    }

    public View getVista() {
        return view;
    }

    public Timer getTimerActual() {
        return timerActual;
    }

}
