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

    private View vista;
    private Timer timerActual;
    private int tiempoTotal;
    private Settings settings;
    // Los tres modos del pomodoro
    private PomodoroTimer pomodoroTimer;
    private PomodoroTimer shortBreakTimer;
    private PomodoroTimer longBreakTimer;

    // Enumeración para tipos de timer
    private enum TipoTimer {
        POMODORO, SHORT_BREAK, LONG_BREAK
    }

    private TipoTimer tipoActual;

//    Constructor: inicializa los timers
    public TimerController() {
        // Crear los tres tipos de timer
        pomodoroTimer = new PomodoroTimer(1500);      // 25 minutos
        shortBreakTimer = new PomodoroTimer(300);        // 5 minutos
        longBreakTimer = new PomodoroTimer(900);         // 15 minutos

        // Por defecto, empezamos con Pomodoro
        timerActual = pomodoroTimer;
        tipoActual = TipoTimer.POMODORO;
        tiempoTotal = 1500;
    }

//    Método principal que arranca la aplicación
    public void arranque() {

        vista = new View();

        // Configurar listeners de la vista
        configurarEventosVista();

        // Configurar listener del modelo
        configurarListenerTimer();

        // Aplicar estilos iniciales
        aplicarEstilosPomodoro();

        // Centrar y mostrar ventana
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

//    Configura los eventos de los botones de la vista
    private void configurarEventosVista() {
        // Botón Start/Pause
        vista.getBtnStart().addActionListener(e -> accionStartPause());

        // Botón Reset
        vista.getBtnReset().addActionListener(e -> accionReset());

//         Botones de cambio de modo
        vista.getBtnPomodoro().addActionListener(e -> cambiarAPomodoro());
        vista.getBtnShortBreak().addActionListener(e -> cambiarAShortBreak());
        vista.getBtnLongBreak().addActionListener(e -> cambiarALongBreak());
        vista.getBtnHelp().setActionCommand("SHOW_HELP");
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
        vista.updateProgressBar(0);
        cambiarIconoStart(false);
    }

//      Cambiar a modo Pomodoro (25 min)
    private void cambiarAPomodoro() {
        detenerTimerActual();
        timerActual = pomodoroTimer;
        tipoActual = TipoTimer.POMODORO;
        tiempoTotal = 1500;
        actualizarTiempoEnVista(1500);
        vista.updateProgressBar(0);
        aplicarEstilosPomodoro();
        cambiarIconoStart(false);
    }

//    Cambiar a modo Short Break (5 min)
    private void cambiarAShortBreak() {
        detenerTimerActual();
        timerActual = shortBreakTimer;
        tipoActual = TipoTimer.SHORT_BREAK;
        tiempoTotal = 300;
        actualizarTiempoEnVista(300);
        vista.updateProgressBar(0);
        aplicarEstilosShortBreak();
        cambiarIconoStart(false);
    }

//  Cambiar a modo Long Break (15 min)
    private void cambiarALongBreak() {
        detenerTimerActual();
        timerActual = longBreakTimer;
        tipoActual = TipoTimer.LONG_BREAK;
        tiempoTotal = 900;
        actualizarTiempoEnVista(900);
        vista.updateProgressBar(0);
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
            vista.updateTimeLabel(String.format("%02d:%02d", minutos, segs));
        });
    }
//    Actualiza la barra de progreso

    private void actualizarBarraProgreso(int segundosRestantes) {
        SwingUtilities.invokeLater(() -> {
            int progreso = (int) (((tiempoTotal - segundosRestantes) / (double) tiempoTotal) * 100);
            vista.updateProgressBar(progreso);
        });
    }

//  Cambia el ícono del botón Start (play/pause)
    private void cambiarIconoStart(boolean enEjecucion) {
        SwingUtilities.invokeLater(() -> {
            vista.updateStartButtonIcon(enEjecucion);
        });
    }

//    Maneja la finalización del timer
    private void manejarFinalizacion() {
        SwingUtilities.invokeLater(() -> {
            String mensaje = obtenerMensajeFinalizacion();
            javax.swing.JOptionPane.showMessageDialog(
                    vista,
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
                return "¡Pomodoro completado! 🍅\nToma un descanso.";
            case SHORT_BREAK:
                return "¡Descanso corto completado! ☕\n¡A trabajar!";
            case LONG_BREAK:
                return "¡Descanso largo completado! 🎉\n¡Listo para continuar!";
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
            vista.updatePanelColors(colorPaneles);

            // Actualizar panel central con borde redondeado
            CustomComponents.hacerPanelRedondeado(
                    vista.getCenterPanel(),
                    25,
                    fondoCenter,
                    bordeCenter,
                    3
            );

            // Actualizar color de barra de progreso
            vista.updateProgressBarColor(bordeCenter);

            // Repintar todo
            vista.repaintComponents();
        });
    }

//    get y set 
    public View getVista() {
        return vista;
    }

    public Timer getTimerActual() {
        return timerActual;
    }

}
