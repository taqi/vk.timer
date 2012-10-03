package ua.pp.keebraa.vktimer.core.tasks;

import ua.pp.keebraa.vktimer.core.units.ApplicationUnit;

public class StartTask implements Task {

    private String taskId = "StartTask ";
    private ApplicationUnit unit;
    private long delay;

    public StartTask(ApplicationUnit unit) {
        this.unit = unit;
    }
    
    public StartTask(ApplicationUnit unit, long delay) {
        this.unit = unit;
        this.delay = delay;
    }

    @Override
    public void run() {
        try {
            Thread.yield();
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }
        unit.start();
    }

    @Override
    public String getTaskId() {
        return taskId + "(" + unit.getUnitId() + ")";
    }
}
