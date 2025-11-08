/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.timer;

//logica de todo el timer independiente del pomodoro 
import java.util.ArrayList;
import uptc.edu.co.models.session.TimerListener;

//LA IDEA DE ESTA CLASE ES QUE SE CREA 
//un tiempo como el de 25 minutos o 5 y le pide al hilo que avise
//que inicia y se puede pausar, reanduar y detenerlo. gracias al hilo
//y sobreecribiendo los metoods de las interfaces
//solo recibe la duración y cuenta hacia atrás
public class PomodoroTimer implements Timer {

//    ATRIBUTOS 
//    1500 = 25 minutos
//    300 = 5 minutos 
//    900 = 15 minutos 
    private int duration;

//    estado del timer
//    - true = run
//    - false = paused 
    private boolean running;

//    esta pausado? para reaundarlo
//    - true = paused
//    - false = run
    private boolean paused;

//    el hilo 
    private TimerThread thread;

//    a quienes notificara 
    private ArrayList<TimerListener> listeners;

//    constructor toma el pomodoro con su respectivo tiempo independiente del modo
    public PomodoroTimer(int duration) {
        this.duration = duration;
        this.listeners = new ArrayList<>();
        // No está corriendo al inicio
        this.running = false;
        // No está pausado al inicio
        this.paused = false;
    }

//    METODOS DE LA INTERFAZ QUE SE REESCRIBEN 
    @Override
    public synchronized void start() {
        // Si ya está corriendo, no hacer nada
        if (running) {
            return; // Salir del método
        }

        // Crear el "motor" que contará los segundos
        thread = new TimerThread(duration, this);

        // Darle la lista de observadores al motor
        thread.setListeners(listeners);

        // Arrancar el motor (empieza a contar)
        thread.start();

        // Marcar que está corriendo
        running = true;
        paused = false;
    }

    @Override
    public synchronized void pause() {
        // Si no está corriendo se sale
        if (!running || thread == null) {
            return;
        }

        // Decirle al hilo que se pause
        thread.pauseTimer();

        // Marcar que está pausado osea cambiar estado
        paused = true;
    }

//     - Verificar que esté pausado
//     - Decirle al hilo que continue
//     - Marcar que ya no está pausado
    @Override
    public synchronized void resume() {
        // Si no está corriendo, no hay motor, o no está pausado, salir
        if (!running || thread == null || !paused) {
            return;
        }

        // Decirle al hilo que continue
        thread.resumeTimer();

        // Marcar que ya no está pausado, reaundar
        paused = false;
    }

//    Si hay un hilo, apagarlo completamente ya que se acabo para el usuario
//    Marcar que ya no está corriendo ni pausado
    @Override
    public synchronized void stop() {
        // Si hay un hilo
        if (thread != null) {
            // Apagarlo
            thread.stopTimer();
            // Olvidarse de el en caso de que se cambie el modo
            thread = null;
        }

        // Marcar que no está corriendo
        running = false;
        paused = false;
    }

    @Override
    public void reset() {
//        reinicia la logica del timer 
        stop();
//         en el controlador actualizamos la vista 
    }

    @Override
    public void addListener(TimerListener listener) {
        // Si el listener existe y no esta ya en la lista
        if (listener != null && !listeners.contains(listener)) {
            // Agregarlo a la lista
            listeners.add(listener);
        }
    }

//    quitar el escucha
    @Override
    public void removeListener(TimerListener listener) {
        listeners.remove(listener);
    }

//    OBTENER cuántos segundos quedan
    @Override
    public int getRemainingTime() {
        // Si hay un hilo, preguntarle cuánto queda
        if (thread != null) {
            return thread.getRemainingSeconds();
        }
        // Si no hay hilo, devolver la duracion completa
        return duration;
    }

//    revisar si esta corriendo 
    @Override
    public boolean isRunning() {
        return running && thread != null && thread.isRunning();
    }
//    revisar si esta pausado 

    @Override
    public boolean isPaused() {
        return paused;
    }

//    Obtiene la duración total configurada
    public int getDuration() {
        return duration;
    }

//    Cambia la duración (solo si no está corriendo)
    public void setDuration(int duration) {
        if (!running) {
            this.duration = duration;
        }
    }
}
