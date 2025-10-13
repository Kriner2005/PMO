/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.timer;

import java.util.ArrayList;
import java.util.List;
import uptc.edu.co.models.session.TimerListener;

/**
 *
 * @author alber
 */
public class PomodoroTimer {
       private int duration;        // duración total en segundos
    private int remainingTime;   // tiempo restante en segundos
    private boolean running;
    private boolean paused;
    private TimerThread thread;
    private List<TimerListener> listeners;

    // Constructor
    public PomodoroTimer(int duration) {
        this.duration = duration;
        this.remainingTime = duration;
        this.running = false;
        this.paused = false;
        this.listeners = new ArrayList<>();
    }

    // Inicia el temporizador
    public void start() {
        if (running) return;

        running = true;
        paused = false;
        thread = new TimerThread();
        thread.start();
    }

    // Pausa el temporizador
    public void pause() {
        if (running && !paused) {
            paused = true;
        }
    }

    // Reanuda el temporizador
    public void resume() {
        if (running && paused) {
            paused = false;
            synchronized (thread) {
                thread.notify(); // despierta el hilo si estaba en pausa
            }
        }
    }

    // Reinicia el temporizador
    public void reset() {
        stop();
        this.remainingTime = duration;
        notifyListeners();
    }

    // Detiene completamente el temporizador
    public void stop() {
        running = false;
        paused = false;
        if (thread != null) {
            thread.interrupt();
        }
    }

    // Añadir un listener
    public void addListener(TimerListener listener) {
        listeners.add(listener);
    }

    // Remover un listener
    public void removeListener(TimerListener listener) {
        listeners.remove(listener);
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPaused() {
        return paused;
    }

    // Notifica a todos los listeners
    private void notifyListeners() {
        for (TimerListener listener : listeners) {
            listener.onTick(remainingTime);
            if (remainingTime == 0) {
                listener.onFinish();
            }
        }
    }

    // ------------------ INNER CLASS ------------------
    private class TimerThread extends Thread {
        @Override
        public void run() {
            try {
                while (running && remainingTime > 0) {
                    if (!paused) {
                        Thread.sleep(1000); // un segundo
                        remainingTime--;
                        notifyListeners();
                    } else {
                        synchronized (this) {
                            wait(); // espera hasta que se reanude
                        }
                    }
                }
                if (remainingTime == 0) {
                    running = false;
                    notifyListeners(); // notifica que terminó
                }
            } catch (InterruptedException e) {
                // Timer detenido manualmente
            }
        }
    }

}
