/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uptc.edu.co.models.timer;

import uptc.edu.co.models.session.TimerListener;

/**
 *
 * @author alber
 */
public interface Timer {

    void start();

    void pause();

    void reset();

    void resume();

    void stop();

    void addListener(TimerListener listener);

    void removeListener(TimerListener listener);

    int getRemainingTime();

    boolean isRunning();

    boolean isPaused();

}
