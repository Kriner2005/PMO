/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.session;

/**
 *
 * @author alber
 */
public interface TimerListener {

//    esta todo el rato verificando el cambio del tiempo
//    Ejm: si el timer tiene 10 segundos, esto dira
//            onTick(10) primer segudo
//            onTick(9) segundo
//            onTick(8) tercer segundo
//            onTick(7) y asi sucesivamente
//            hasta que diga que termino con el 
//            onFinish() SE ACABO EL TIMER 
//    basicamente da un tick del reloj 
//    remaining dice cuanto le queda en tiempo 
    void onTick(int remainingSeconds);

//    cuando llega a 0 se llama este metodo para que notificaciones pueda hacer lo suyo y muestre el mensaje 
//  NOTA:  que no se me olvide hacerlo
//    -   que suene
//    - que muestre un mensaje
//    - y pase al siguiente estado del pomodoro 
    void onFinish();

}
