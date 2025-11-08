
import javax.swing.JOptionPane;
import uptc.edu.co.models.session.TimerListener;
import uptc.edu.co.models.timer.PomodoroTimer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author alber
 */
public class TestThread {

    public static void main(String[] args) throws InterruptedException {
        PomodoroTimer pomodoro = new PomodoroTimer(10); // 20 segundos

        pomodoro.addListener(new TimerListener() {
            @Override
            public void onTick(int remainingTime) {
                System.out.println("⏱ Tiempo restante: " + remainingTime + "s");
            }

            @Override
            public void onFinish() {
                System.out.println("✅ Pomodoro finalizado");
            }
        });

        pomodoro.start();
        do {
            int option = Integer.parseInt(JOptionPane.showInputDialog(null, "1) pausar \n 2) reanudar"));
            switch (option) {
                case 1 ->
                    pomodoro.pause();
                case 2 ->
                    pomodoro.resume();
                case 3 ->
                    pomodoro.stop();
                default ->
                    JOptionPane.showMessageDialog(null, "bye");
            }
        } while (pomodoro.getRemainingTime() >= 0);
    }

}
