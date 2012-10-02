package ua.pp.keebraa.vktimer.api;

import java.io.IOException;

import ua.pp.keebraa.vktimer.api.exception.GetPageException;
import ua.pp.keebraa.vktimer.api.interfaces.IUserApi;
import ua.pp.keebraa.vktimer.api.interfaces.IVkApi;
import ua.pp.keebraa.vktimer.api.user.UserApi;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

public class ExceptionUnsafeVkApi implements IVkApi {
    private String login;
    private String password;
    private String applicationId;
    private WebClient webClient;

    private AccessToken accessTokenController;

    public void init(String login, String password, String applicationId) {
        this.login = login;
        this.password = password;
        this.applicationId = applicationId;
        this.webClient = new WebClient();
        this.accessTokenController = new AccessToken(this);
    }

    public String getAccessToken() {
        return accessTokenController.getAccessToken(login, password, applicationId);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Page getPage(String url) {
        try {
            Page page = webClient.getPage(url);
            return page;
        } catch (IOException e) {
            throw new GetPageException(url, e);
        }
    }

    @Override
    public void dispose() {
        login = null;
        password = null;
        accessTokenController = null;
        if(webClient != null)
            webClient.closeAllWindows();
        webClient = null;
    }

    @Override
    public IUserApi getUserApi() {
        return new UserApi(this);
    }
}
