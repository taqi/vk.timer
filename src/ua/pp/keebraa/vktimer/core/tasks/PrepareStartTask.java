package ua.pp.keebraa.vktimer.core.tasks;

import ua.pp.keebraa.vktimer.core.units.ApplicationUnit;

public class PrepareStartTask implements Task {

    private String taskId = "PrepareStartTask ";
    private ApplicationUnit unit;

    public PrepareStartTask(ApplicationUnit unit) {
        this.unit = unit;
    }

    @Override
    public void run() {
        unit.prepareStart();
    }

    @Override
    public String getTaskId() {
        return taskId + "(" + unit.getUnitId() + ")";
    }
}
