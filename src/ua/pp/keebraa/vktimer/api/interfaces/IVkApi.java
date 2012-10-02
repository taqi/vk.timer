package ua.pp.keebraa.vktimer.api.interfaces;

import com.gargoylesoftware.htmlunit.Page;

public interface IVkApi {
    public void init(String login, String password, String applicationId);

    public String getAccessToken();

    public Page getPage(String url);
    
    public void dispose();
    
    public IUserApi getUserApi();
}
