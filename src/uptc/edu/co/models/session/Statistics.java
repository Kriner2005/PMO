/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.session;

import java.time.LocalDateTime;
import java.util.List;

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

    public void calateFromSessions(List<Session> sessions) {
        reset();
        for (Session session : sessions) {
            if (session.getHistory() != null) {
                caculateFromRecords(session.getHistory());
            }
        }
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

    public void caculateFromRecords(List<PomodoroRecord> records) {
        reset();
        int completedWork = 0;
        int totalWork = 0;
        double totalDuration = 0;

        for (PomodoroRecord record : records) {
            if (record.getType() == PomodoroType.WORK) {
                totalWork++;
                totalWorkTime += record.getPlannedDuration();
                if (record.isCompleted()) {
                    completedWork++;
                    totalWorkPomodoros++;
                }
                totalDuration += record.getPlannedDuration();
            } else if (record.getType() == PomodoroType.SHORT_BREAK) {
                totalShortBreaks++;
                totalBreakTime += record.getPlannedDuration();
                totalDuration += record.getPlannedDuration();
            } else if (record.getType() == PomodoroType.LONG_BREAK) {
                totalLongBreaks++;
                totalBreakTime += record.getPlannedDuration();
                totalDuration += record.getPlannedDuration();
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

    public double getProdutivityScore() {
        // Score basado en pomodoros completados y tiempo efectivo
        double baseScore = (totalWorkPomodoros * 10); // 10 puntos por pomodoro
        double efficiencyBonus = getEfficiencyRate() * 0.5; // bonus por eficiencia
        return Math.min(100, baseScore + efficiencyBonus);
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
