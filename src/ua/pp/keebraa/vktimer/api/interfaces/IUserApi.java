package ua.pp.keebraa.vktimer.api.interfaces;

import ua.pp.keebraa.vktimer.api.answer.UserOnlineAnswer;
import ua.pp.keebraa.vktimer.api.answer.UsersOnlineAnswer;

public interface IUserApi {
    public UserOnlineAnswer isUserOnline(String userId);

    public UsersOnlineAnswer isUsersOnline(String... usersId);
}
