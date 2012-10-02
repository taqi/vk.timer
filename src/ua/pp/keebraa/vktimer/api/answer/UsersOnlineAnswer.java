package ua.pp.keebraa.vktimer.api.answer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UsersOnlineAnswer implements Iterable<UserOnlineAnswer>{
    private final Map<User, UserOnlineAnswer> answers = new HashMap<User, UserOnlineAnswer>();
    
    public void addUserOnline(User user, UserOnlineAnswer answer){
        answers.put(user, answer);
    }

    @Override
    public Iterator<UserOnlineAnswer> iterator() {
        return answers.values().iterator();
    }
}
