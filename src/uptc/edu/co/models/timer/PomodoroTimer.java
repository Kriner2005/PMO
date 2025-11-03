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
public class PomodoroTimer implements Timer {

    private int duration;
    private boolean running;
    private boolean paused;
    private TimerThread thread;
    private List<TimerListener> listeners;

    public PomodoroTimer(int duration) {
        this.duration = duration;
        this.listeners = new ArrayList<>();
    }

    @Override
    public synchronized void start() {
        if (running) {
            return;
        }

        // Crear el TimerThread global
        thread = new TimerThread(duration, this);
        thread.setListeners(listeners);
        thread.start();

        running = true;
        paused = false;
    }

    @Override
    public synchronized void pause() {
        if (!running || thread == null) {
            return;
        }
        thread.pauseTimer();
        paused = true;
    }

    @Override
    public synchronized void resume() {
        if (!running || thread == null) {
            return;
        }
        if (!paused) {
            return;
        }
        thread.resumeTimer();
        paused = false;
    }

    @Override
    public synchronized void stop() {
        if (thread != null) {
            thread.stopTimer();
            thread = null;
        }
        running = false;
        paused = false;
    }

    @Override
    public void reset() {
        stop();
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
        if (thread != null) {
            return thread.getRemainingSeconds();
        }
        return duration;
    }

    @Override
    public boolean isRunning() {
        return running && thread != null && thread.isRunning();
    }

    @Override
    public boolean isPaused() {
        return paused;
    }
}
