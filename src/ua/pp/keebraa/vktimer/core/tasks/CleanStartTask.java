package ua.pp.keebraa.vktimer.core.tasks;

import ua.pp.keebraa.vktimer.core.units.ApplicationUnit;

public class CleanStartTask implements Task {

    private static String taskId = "CleanStartUnit";
    private ApplicationUnit unit;

    public CleanStartTask(ApplicationUnit unit) {
        this.unit = unit;
    }

    @Override
    public void run() {
        unit.cleanStart();
    }

    @Override
    public String getTaskId() {
        String result = null;
        synchronized (unit) {
            result = taskId + "(" + unit.getUnitId() + ")";
        }
        return result;
    }
}
