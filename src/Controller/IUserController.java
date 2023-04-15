package src.Controller;

import src.Entity.Request;
import src.Entity.User;

import java.io.IOException;
import java.util.List;

public interface IUserController {
    User loginUser(List<User> users, String userID, String password) throws IOException;
    void changeUserPassword(String userID, String newPassword);
    void viewUserDetails(User user);

    List<Request> getRequestHistory(User user);
}
