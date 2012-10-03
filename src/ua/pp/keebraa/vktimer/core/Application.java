package ua.pp.keebraa.vktimer.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ua.pp.keebraa.vktimer.api.interfaces.IVkApi;
import ua.pp.keebraa.vktimer.core.tasks.CleanStartTask;
import ua.pp.keebraa.vktimer.core.tasks.Task;
import ua.pp.keebraa.vktimer.core.units.ApplicationUnit;
public class Application{

    private static ExecutorService executor = Executors.newFixedThreadPool(4);

    private Map<String, ApplicationUnit> units;

    private List<Task> taskList;
    private IVkApi api;

    public Application() {
        units = Collections.synchronizedMap(new HashMap<String, ApplicationUnit>());
        taskList = Collections.synchronizedList(new LinkedList<Task>());
    }

    public void setApi(IVkApi api) {
        this.api = api;
    }

    public void addUnit(ApplicationUnit unit) {
        units.put(unit.getUnitId(), unit);
    }

    public ApplicationUnit getUnit(String unitId) {
        return units.get(unitId);
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public void registerTask(Task task) {
        
        synchronized (taskList) {
            taskList.add(task);
            taskList.notify();
        }
    }
    
    public void removeTask(Task task) {
        synchronized (taskList) {
            taskList.remove(task);
            taskList.notify();
        }
    }

    public IVkApi getApi() {
        return api;
    }

    public void start() {
        for (ApplicationUnit unit : units.values()) {
            Task task = new CleanStartTask(unit);
            this.registerTask(task);
        }
        runMainThread();
    }

    private void runMainThread() {
        while (true) {
            while (getTasks().isEmpty()) {
                try {
                    synchronized (getTasks()) {
                        getTasks().wait();
                    }
                } catch (InterruptedException e) {
                }
            }
            List<Task> currentTasks = Collections.synchronizedList(new LinkedList<Task>(getTasks()));
            for (Task task : currentTasks) {
                System.out.println("RUN TASK: " + task.getTaskId());
                executor.execute(task);
                getTasks().remove(task);
            }
        }
    }
}
