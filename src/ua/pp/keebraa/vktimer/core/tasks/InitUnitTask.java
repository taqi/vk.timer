package ua.pp.keebraa.vktimer.core.tasks;

import ua.pp.keebraa.vktimer.core.units.ApplicationUnit;

public class InitUnitTask implements Task {

    private String taskId = "InitTask ";
    private ApplicationUnit unit;
    private long delay;

    public InitUnitTask(ApplicationUnit unit) {
        this.unit = unit;
    }
    
    public InitUnitTask(ApplicationUnit unit, long delay) {
        this.unit = unit;
        this.delay = delay;
    }

    @Override
    public void run() {
        try{
            Thread.yield();
            Thread.sleep(delay);
        }catch(InterruptedException e){
            
        }
        unit.init();

    }

    @Override
    public String getTaskId() {
        return taskId + "(" + unit.getUnitId() + ")";
    }
}
