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

//    Arranca el timer
    void start();

//    Pausa el timer (
    void pause();

//    Reanuda el timer desde donde se pauso antes
    void resume();

//    Detiene completamente el timer
    void stop();

//    Reinicia el timer a su tiempo inicial
    void reset();

//    notificacion del escucha 
    void addListener(TimerListener listener);

    /**
     * Remueve un observador
     * @param listener
     */
    void removeListener(TimerListener listener);

//    cuantos segudos quedan en el timer y muestra
    int getRemainingTime();

//    ---- aqui comprueban el estado si esta activo o no el timer ------
//    Pregunta si el timer está corriendo
//            
//    @return true si está corriendo, false si no
    boolean isRunning();

//    Pregunta si el timer está pausado
//            
//    @return true si está pausado, false si no
    boolean isPaused();
}
