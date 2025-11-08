/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.timer;

import java.util.ArrayList;
import uptc.edu.co.models.session.TimerListener;

/**
 *
 * @author alber
 */

//la logica es que se programa con un tiempo en el constructor 
//cuenta en segundo plano
//te avisa cuanto queda en la vista con el escucha
//se puede pausar, reaundar y apagar 
public class TimerThread extends Thread {

//    se encargara de contar a la par que el programa esta en ejecucion
//    cuenta al hilo y asi puede continuar en segundo plano
//    atributos 
//    conexion al timer al que pertenece el hilo 
    private Timer timer;

//estado del hilo 
    private volatile boolean running;

    private volatile boolean paused;

//Tiempo total en segundos 
    private int totalSeconds;

//cuanto queda para mostrar en la vista 
    private int remainingSeconds;

//lista de escuchas 
    private ArrayList<TimerListener> listeners;
//    constructor 

//    cuanto debe contar y de quien es el hilo 
    public TimerThread(int totalSeconds, Timer timer) {
        this.totalSeconds = totalSeconds;
        this.remainingSeconds = totalSeconds; // Empieza con el tiempo completo
        this.timer = timer;
        this.running = false;
        this.paused = false;
    }

//    FUNCIONAMIENTO HILO 
//    RESTAR TIEMPO, AVISAR A LA VISTA HASTA QUE LLEGUE A 0
    @Override
    public void run() {
        // Empezamos
        running = true;

        try {
            // CICLO PRINCIPAL: mientras este corriendo y queden segundos
            while (running && remainingSeconds > 0) {

                // Si está pausado, esperar hasta que se reanude
                synchronized (this) {
                    while (paused && running) {
                        // Esperar hasta que le den a resumeTimer()
                        wait();
                    }
                }

                // Esperar 1 segundo
                Thread.sleep(1000); // 1000 milisegundos = 1 segundo

                // Restar 1 segundo
                remainingSeconds--;

                // Notificar a los observadores (TICK)
                notifyTick();
            }

            // Si llegamos a 0, notificar
            if (remainingSeconds <= 0 && running) {
                notifyFinish();
            }

        } catch (InterruptedException ex) {
            // Si alguien interrumpe el hilo, salir
        } finally {
            // Ya terminamos
            running = false;
        }
    }

//    Control del hilo 
//    PAUSAR el conteo
//    El hilo se quedará dormido en el wait() del run()
    public synchronized void pauseTimer() {
        paused = true;
    }

//    REANUDAR el conteo
//    Despierta al hilo que estaba esperando en wait()
    public synchronized void resumeTimer() {
        paused = false;
        notifyAll(); // Despertar al hilo
    }

//    DETENER completamente el hilo
//    Interrumpe el sleep() del run()
    public void stopTimer() {
        running = false;
        // Despertar al hilo para que salga del run()
        interrupt();
    }

//REINICIAR el contador
    public void resetTimer() {
        remainingSeconds = totalSeconds;
    }

//NOTIFICAR A LA VISTA 
//Notificar a todos los observadores: "pasó un segundo"
    private void notifyTick() {
        if (listeners != null) {
            for (TimerListener listener : listeners) {
                listener.onTick(remainingSeconds);
            }
        }
    }

//    TERMINO 
    private void notifyFinish() {
        if (listeners != null) {
            for (TimerListener listener : listeners) {
                listener.onFinish();
            }
        }
    }

    public void setListeners(ArrayList<TimerListener> listeners) {
        this.listeners = listeners;
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPaused() {
        return paused;
    }

    public int getTotalSeconds() {
        return totalSeconds;
    }
}
