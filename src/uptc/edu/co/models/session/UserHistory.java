/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alber
 */
public class UserHistory {

    private int userId;
    private LocalDate date;
    private List<Session> completedSessions;
    private Statistics dailyStats;

    // Constructor
    public UserHistory(int userId, LocalDate date) {
        this.userId = userId;
        this.date = date;
        this.completedSessions = new ArrayList<>();
        this.dailyStats = new Statistics();
    }

    // Añadir una sesión completada
    public void addCompletedSession(Session session) {
        if (session != null) {
            completedSessions.add(session);
            // recalcula las estadísticas automáticamente
            calculateDailyStats();
        }
    }

    // Calcular estadísticas diarias basadas en las sesiones
    public Statistics calculateDailyStats() {
        int totalWorkTime = 0;
        int totalBreakTime = 0;
        int totalPomodoros = 0;

        for (Session session : completedSessions) {
            if (session.getHistory() != null) {
                for (uptc.edu.co.models.session.PomodoroRecord record : session.getHistory()) {
                    if (record.isCompleted()) {
                        if (uptc.edu.co.models.session.PomodoroType.WORK == record.getType()) {
                            totalWorkTime += record.getPlannedDuration();
                            totalPomodoros++;
                        } else {
                            totalBreakTime += record.getPlannedDuration();
                        }
                    }
                }
            }
        }

        dailyStats.setTotalWorkTime(totalWorkTime);
        dailyStats.setTotalBreakTime(totalBreakTime);
        dailyStats.setTotalWorkPomodoros(totalPomodoros);

        return dailyStats;
    }

    // obtiene las sesiones completadas
    public List<Session> getCompletedSessions() {
        return completedSessions;
    }

    // obtiene las estadisticas diarias
    public int getTotalWorkTime() {
        return dailyStats.getTotalWorkTime();
    }

    // obtiene el tiempo total de descanso
    public int getTotalBreakTime() {
        return dailyStats.getTotalBreakTime();
    }

    // obtiene el total de pomodoros
    public int getTotalPomodoros() {
        return dailyStats.getTotalWorkPomodoros();
    }

    public Statistics getStatsForPeriod(java.time.LocalDate startDate, java.time.LocalDate endDate) {
        Statistics periodStats = new Statistics();

        // Filtrar sesiones por fechas
        java.util.List<Session> filteredSessions = new java.util.ArrayList<>();
        for (Session session : completedSessions) {
            if (session.getStartTime() != null) {
                java.time.LocalDate sessionDate = session.getStartTime().toLocalDate();
                if (!sessionDate.isBefore(startDate) && !sessionDate.isAfter(endDate)) {
                    filteredSessions.add(session);
                }
            }
        }

        periodStats.calateFromSessions(filteredSessions);
        return periodStats;
    }

    // obtiene el id del usuario
    public int getUserId() {
        return userId;
    }

    // obtiene la fecha del usuario
    public LocalDate getDate() {
        return date;
    }

}
