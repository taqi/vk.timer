package ua.pp.keebraa.vktimer.api.accesstoken;

import java.util.LinkedList;
import java.util.List;

import ua.pp.keebraa.vktimer.api.exception.AccessTokenWizardException;
import ua.pp.keebraa.vktimer.api.interfaces.IVkApi;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AccessTokenWizard {
    private String token;

    private HtmlPage currentPage;

    private String login;

    private Long expired;

    private String password;

    private List<AccessTokenWizardPage> pages;

    private IVkApi api;

    public AccessTokenWizard(IVkApi api) {
        pages = new LinkedList<AccessTokenWizardPage>();
        pages.add(new AccessTokenAskLoginPasswordPage());
        pages.add(new AllowAccessPage());
        pages.add(new LoginSuccessPage());
        this.api = api;
    }

    public HtmlPage getPage() {
        return currentPage;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void changePage(String url) {
        currentPage = (HtmlPage) api.getPage(url);
    }

    public void changePage(HtmlPage page) {
        currentPage = page;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String process() {
        for (AccessTokenWizardPage page : pages) {
            if (!page.validate(currentPage))
                continue;
            boolean result = page.process(this);
            if (!result) {
                throw new AccessTokenWizardException(page.getPageDescription());
            }
        }
        return token;
    }

    public void setExpired(String expired) {
        this.expired = Long.valueOf(expired);
    }

    public long getExpired() {
        return expired;
    }
}
