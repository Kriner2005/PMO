/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.session;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.user.Role;
import uptc.edu.co.models.user.User;

/**
 *
 * @author alber
 */
public class Statistics {

    private int totalWorkPomodoros;
    private int totalShortBreaks; // en minutos
    private int totalLongBreaks; // en minutos
    private int totalWorkTime;
    private int totalBreakTime;
    private double averagePomodoroLenght; // porcentaje promedio
    private double completionRate; // porcentaje de pomodoros completados
    private LocalDateTime calulationDate;

    // Constructor
    public Statistics() {
        this.totalWorkPomodoros = 0;
        this.totalShortBreaks = 0;
        this.totalLongBreaks = 0;
        this.totalWorkTime = 0;
        this.totalBreakTime = 0;
        this.averagePomodoroLenght = 0.0;
        this.completionRate = 0.0;
        this.calulationDate = LocalDateTime.now();
    }

    private void reset() {
        this.totalWorkPomodoros = 0;
        this.totalShortBreaks = 0;
        this.totalLongBreaks = 0;
        this.totalWorkTime = 0;
        this.totalBreakTime = 0;
        this.averagePomodoroLenght = 0.0;
        this.completionRate = 0.0;
    }

    public List<Map.Entry<User, Double>> getUserRankingByHours() {
        PersistenceManager manager = new PersistenceManager();
        Map<User, Double> userHours = new HashMap<>();
        List<Session> sessions = manager.loadAllSessions();
        for (Session s : sessions) {
            if (s.getLoggedUser() != null && s.getStartTime() != null && s.getEndTime() != null) {
                double hours = getTotalMinutes(s.getStartTime(), s.getEndTime()) / 60.0;
                userHours.put(s.getLoggedUser(), userHours.getOrDefault(s.getLoggedUser(), 0.0) + hours);
            }
        }

        List<Map.Entry<User, Double>> sorted = new ArrayList<>(userHours.entrySet());
        sorted.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        return sorted;
    }

    public Duration calculateDuration(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            return Duration.ZERO;
        }
        return Duration.between(startTime, endTime);
    }

    public long getTotalMinutes(LocalDateTime startTime, LocalDateTime endTime) {
        return calculateDuration(startTime, endTime).toMinutes();
    }

    //datos de prueba
    public List<Map.Entry<User, Double>> prueba() {
        // 🔹 Datos de ejemplo (mock)
        Map<User, Double> userHours = new HashMap<>();

        userHours.put(new User(123, "Alice", "", "", Role.USER), 12.5);
        userHours.put(new User(456, "Bob", "", "", Role.USER), 7.8);
        userHours.put(new User(789, "Carlos", "", "", Role.USER), 15.2);
        userHours.put(new User(321, "Diana", "", "", Role.USER), 9.0);
        userHours.put(new User(654, "Erick", "", "", Role.USER), 20.3);

        // 🔹 Ordenar por horas (descendente)
        List<Map.Entry<User, Double>> sorted = new ArrayList<>(userHours.entrySet());
        sorted.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        return sorted;
    }

    public void caculateFromRecords(List<PomodoroRecord> records) {
        reset();
        int completedWork = 0;
        int totalWork = 0;
        double totalDuration = 0;

        for (PomodoroRecord record : records) {
            if (null != record.getType()) switch (record.getType()) {
                case WORK:
                    totalWork++;
                    totalWorkTime += record.getPlannedDuration();
                    if (record.isCompleted()) {
                        completedWork++;
                        totalWorkPomodoros++;
                    }   totalDuration += record.getPlannedDuration();
                    break;
                case SHORT_BREAK:
                    totalShortBreaks++;
                    totalBreakTime += record.getPlannedDuration();
                    totalDuration += record.getPlannedDuration();
                    break;
                case LONG_BREAK:
                    totalLongBreaks++;
                    totalBreakTime += record.getPlannedDuration();
                    totalDuration += record.getPlannedDuration();
                    break;
                default:
                    break;
            }
        }

        // Calcular promedios
        if (totalWork > 0) {
            this.completionRate = (completedWork / (double) totalWork) * 100;
        }

        if (records.size() > 0) {
            this.averagePomodoroLenght = totalDuration / records.size();
        }

        this.calulationDate = LocalDateTime.now();
    }

    public double getEfficiencyRate() {
        if (totalWorkTime <= 0) {
            return 0;
        }

        // Eficiencia basada en qué tan cerca están los tiempos reales de los ideales
        int idealWorkTime = totalWorkPomodoros * 25; // 25 min por pomodoro ideal
        return Math.min(100, (idealWorkTime / (double) totalWorkTime) * 100);
    }

    public int getTotalWorkPomodoros() {
        return totalWorkPomodoros;
    }

    public void setTotalWorkPomodoros(int totalWorkPomodoros) {
        this.totalWorkPomodoros = totalWorkPomodoros;
    }

    public int getTotalShortBreaks() {
        return totalShortBreaks;
    }

    public void setTotalShortBreaks(int totalShortBreaks) {
        this.totalShortBreaks = totalShortBreaks;
    }

    public int getTotalLongBreaks() {
        return totalLongBreaks;
    }

    public void setTotalLongBreaks(int totalLongBreaks) {
        this.totalLongBreaks = totalLongBreaks;
    }

    public int getTotalWorkTime() {
        return totalWorkTime;
    }

    public void setTotalWorkTime(int totalWorkTime) {
        this.totalWorkTime = totalWorkTime;
    }

    public int getTotalBreakTime() {
        return totalBreakTime;
    }

    public void setTotalBreakTime(int totalBreakTime) {
        this.totalBreakTime = totalBreakTime;
    }

    public double getAveragePomodoroLenght() {
        return averagePomodoroLenght;
    }

    public void setAveragePomodoroLenght(double averagePomodoroLenght) {
        this.averagePomodoroLenght = averagePomodoroLenght;
    }

    public double getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(double completionRate) {
        this.completionRate = completionRate;
    }

    public LocalDateTime getCalulationDate() {
        return calulationDate;
    }

    public void setCalulationDate(LocalDateTime calulationDate) {
        this.calulationDate = calulationDate;
    }
}
