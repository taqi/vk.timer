package ua.pp.keebraa.vktimer.core.units;

import ua.pp.keebraa.vktimer.api.interfaces.IVkApi;
import ua.pp.keebraa.vktimer.core.Application;

public interface ApplicationUnit {

    public void setApplication(Application application);

    public void cleanStart();

    public void init();

    public void prepareStart();

    public void start();

    public void stop();

    public void dispose();

    public String getUnitId();
}
