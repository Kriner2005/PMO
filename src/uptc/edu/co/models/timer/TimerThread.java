/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.timer;

import java.util.List;
import uptc.edu.co.models.session.TimerListener;

/**
 *
 * @author alber
 */
public class TimerThread extends Thread {

    private Timer timer;
    private volatile boolean running;
    private volatile boolean paused;
    private int totalSeconds;
    private int remainingSeconds;
    private List<TimerListener> listeners;

    public TimerThread(int totalSeconds, Timer timer) {
        this.totalSeconds = totalSeconds;
        this.remainingSeconds = totalSeconds;
        this.timer = timer;
        this.running = false;
        this.paused = false;
    }

    public void run() {
        running = true;

        try {
            while (running && remainingSeconds > 0) {
                if (!paused) {
                    Thread.sleep(1000); // esperar 1 segundo
                    remainingSeconds--;

                    // Notificar tick a través del timer
                    notifyTick();
                } else {
                    // Si está pausado, esperar hasta que se reanude
                    synchronized (this) {
                        while (paused && running) {
                            wait();
                        }
                    }
                }
            }

            // Si el timer llegó a cero, notificar finalización
            if (remainingSeconds <= 0 && running) {
                notifyFinish();
            }

        } catch (InterruptedException e) {
            // Thread interrumpido (timer detenido)
        } finally {
            running = false;
        }
    }

    public synchronized void pauseTimer() {
        paused = true;
    }

    public synchronized void resumeTimer() {
        paused = false;
        notify(); // despertar el thread si estaba esperando
    }

    public void stopTimer() {
        running = false;
        interrupt(); // interrumpir el thread
    }

    private void notifyTick() {
        // Si el timer implementa TimerListener, notificar directamente
        if (timer instanceof TimerListener) {
            ((TimerListener) timer).onTick(remainingSeconds);
        }

        // Si hay listeners externos, notificarlos también
        if (listeners != null) {
            for (TimerListener listener : listeners) {
                listener.onTick(remainingSeconds);
            }
        }
    }

    private void notifyFinish() {
        // Si el timer implementa TimerListener, notificar directamente
        if (timer instanceof TimerListener) {
            ((TimerListener) timer).onFinish();
        }

        // Si hay listeners externos, notificarlos también
        if (listeners != null) {
            for (TimerListener listener : listeners) {
                listener.onFinish();
            }
        }
    }

    // Setter para listeners externos
    public void setListeners(List<TimerListener> listeners) {
        this.listeners = listeners;
    }

    // Método para resetear el timer
    public void resetTimer() {
        remainingSeconds = totalSeconds;
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
