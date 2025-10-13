package uptc.edu.co.controllers;

import uptc.edu.co.models.session.PomodoroRecord;
import uptc.edu.co.models.session.PomodoroType;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.session.TimerListener;
import uptc.edu.co.models.settings.Settings;
import uptc.edu.co.models.timer.PomodoroTimer;

public class PomodoroController implements TimerListener {

    private Session currentSession;
    private PomodoroTimer currentTimer;
    private Settings settings;
    private int completedWorkPomodoros;
    private PomodoroRecord currentRecord;

    // Constructor
    public PomodoroController(Session session, Settings settings) {
        this.currentSession = session;
        this.settings = settings;
        this.completedWorkPomodoros = 0;
    }

    // ------------------ CONTROL DE POMODOROS ------------------
    public void startWorkPomodoro(String taskName) {
        int duration = settings.getWorkDuration(); // minutos → deberías definirlo en Settings
        currentTimer = new PomodoroTimer(duration * 60); // en segundos
        currentTimer.addListener(this);

        createPomodoroRecord(PomodoroType.WORK, taskName);

        currentTimer.start();
        onTimerStarted(PomodoroType.WORK);
    }

    public void startBreak() {
        PomodoroType breakType = shouldStartLongBreak() ? PomodoroType.LONG_BREAK : PomodoroType.SHORT_BREAK;

        int duration = (breakType == PomodoroType.LONG_BREAK) ? settings.getLongBreakDuration()
                : settings.getShortBreakDuration();

        currentTimer = new PomodoroTimer(duration * 60);
        currentTimer.addListener(this);

        createPomodoroRecord(breakType, "Break");
        currentTimer.start();
        onTimerStarted(breakType);
    }

    public void pauseTimer() {
        if (currentTimer != null) {
            currentTimer.pause();
            onTimerPaused();
        }
    }

    public void resumeTimer() {
        if (currentTimer != null) {
            currentTimer.resume();
            onTimerResumed();
        }
    }

    public void stopTimer() {
        if (currentTimer != null) {
            currentTimer.stop();
            finalizePomodoroRecord(false);
            onTimerStopped();
        }
    }

    public PomodoroTimer getCurrentTimer() {
        return currentTimer;
    }

    public int getCompletedPomodoros() {
        return completedWorkPomodoros;
    }

    // ------------------ LISTENER EVENTS ------------------
    @Override
    public void onTick(int remainingTime) {
        onTimerTick(remainingTime);
    }

    @Override
    public void onFinish() {
        PomodoroType type = currentRecord.getType();
        finalizePomodoroRecord(true);

        if (type == PomodoroType.WORK) {
            completedWorkPomodoros++;
        }

        onTimerFinished(type);
    }

    public void onTimerStarted(PomodoroType type) {
        System.out.println("Timer started: " + type);
    }

    public void onTimerTick(int remainingSeconds) {
        System.out.println("Remaining: " + remainingSeconds + "s");
    }

    public void onTimerFinished(PomodoroType type) {
        System.out.println("Timer finished: " + type);
    }

    public void onTimerPaused() {
        System.out.println("Timer paused");
    }

    public void onTimerResumed() {
        System.out.println("Timer resumed");
    }

    public void onTimerStopped() {
        System.out.println("Timer stopped");
    }

    // ------------------ REGISTROS ------------------
    private void createPomodoroRecord(PomodoroType type, String taskName) {
        currentRecord = new PomodoroRecord(type, taskName);
        currentRecord.startRecord();
    }

    private void finalizePomodoroRecord(boolean completed) {
        if (currentRecord != null) {
            currentRecord.endRecord(completed);
            currentSession.addPomodoroRecord(currentRecord);
            currentRecord = null;
        }
    }

    private boolean shouldStartLongBreak() {
        return completedWorkPomodoros > 0
                && completedWorkPomodoros % settings.getLongBreakInterval() == 0;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
