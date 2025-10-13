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
public class BreakTimer implements Timer {

    private int duration;
    private int remainingTime;
    private boolean running;
    private boolean paused;
    private TimerThread timerThread;
    private List<TimerListener> listeners;

    public BreakTimer(int duration) {
        this.duration = duration;
        this.remainingTime = duration;
        this.running = false;
        this.timerThread = new TimerThread(duration, this);
    }

    @Override
    public void start() {
        if (running) {
            return;
        }

        running = true;
        paused = false;
        remainingTime = duration;

        // Crear nuevo thread cada vez que se inicia
        timerThread = (TimerThread) new Thread(this::run);
        timerThread.start();
    }

    @Override
    public void pause() {
        if (running && !paused) {
            paused = true;
        }
    }

    @Override
    public void reset() {
        stop();
        remainingTime = duration;
        notifyListeners();
    }

    public void resume() {
        if (running && paused) {
            paused = false;
            synchronized (this) {
                notify();
            }
        }
    }

    
    @Override
    public void stop() {
        running = false;
        paused = false;
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
        }
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
    public boolean isPaused() {
        return paused;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    private void run() {
        try {
            while (running && remainingTime > 0) {
                if (!paused) {
                    Thread.sleep(1000); // esperar 1 segundo
                    remainingTime--;
                    notifyListeners();
                } else {
                    synchronized (this) {
                        wait(); // esperar hasta que se reanude
                    }
                }
            }

            if (remainingTime == 0) {
                running = false;
                notifyFinish();
            }
        } catch (InterruptedException e) {
            // Timer interrumpido (detenido manualmente)
            running = false;
        }
    }

    private void notifyFinish() {
        for (TimerListener listener : listeners) {
            listener.onFinish();
        }
    }

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

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}
