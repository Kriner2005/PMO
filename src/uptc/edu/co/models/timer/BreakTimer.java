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
public class BreakTimer implements Timer {

    private int duration;                // Duración total en segundos
    private int remainingTime;           // Tiempo restante
    private boolean running;
    private boolean paused;
    private TimerThread thread;          // Usa el TimerThread global
    private List<TimerListener> listeners;

    public BreakTimer(int duration) {
        this.duration = duration;
        this.remainingTime = duration;
        this.running = false;
        this.paused = false;
        this.listeners = new ArrayList<>();
    }

    @Override
    public void start() {
        if (running) {
            return;
        }

        running = true;
        paused = false;

        // Crear un nuevo hilo TimerThread con esta duración
        thread = new TimerThread(duration, this);
        thread.setListeners(listeners);
        thread.start();
    }

    @Override
    public void pause() {
        if (thread != null && running) {
            thread.pauseTimer();
            paused = true;
        }
    }

    @Override
    public void resume() {
        if (thread != null && paused) {
            thread.resumeTimer();
            paused = false;
        }
    }

    @Override
    public void reset() {
        stop();
        remainingTime = duration;
        notifyListeners();
    }

    @Override
    public void stop() {
        if (thread != null) {
            thread.stopTimer();
        }
        running = false;
        paused = false;
    }

    @Override
    public void addListener(TimerListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeListener(TimerListener listener) {
        listeners.remove(listener);
    }

    @Override
    public int getRemainingTime() {
        return remainingTime;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public boolean isPaused() {
        return paused;
    }

    // Notifica a los listeners manualmente (por ejemplo, al hacer reset)
    private void notifyListeners() {
        for (TimerListener listener : listeners) {
            listener.onTick(remainingTime);
        }
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
