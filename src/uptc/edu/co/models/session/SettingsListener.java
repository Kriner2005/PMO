/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.session;

/**
 *
 * @author alber
 */
public interface SettingsListener {
      
    /**
     * Se ejecuta cuando cambian las configuraciones del Pomodoro
     * @param newSettings Las nuevas configuraciones aplicadas
     */
    void onSettingsChanged(Settings newSettings);
}
