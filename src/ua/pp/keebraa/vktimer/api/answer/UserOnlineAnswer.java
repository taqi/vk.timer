package ua.pp.keebraa.vktimer.api.answer;

public class UserOnlineAnswer {
    private User user;
    private boolean online;
    private boolean mobileOnline;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public boolean isOnline() {
        return online;
    }
    public void setOnline(boolean online) {
        this.online = online;
    }
    public boolean isMobileOnline() {
        return mobileOnline;
    }
    public void setMobileOnline(boolean mobileOnline) {
        this.mobileOnline = mobileOnline;
    }
    
}
