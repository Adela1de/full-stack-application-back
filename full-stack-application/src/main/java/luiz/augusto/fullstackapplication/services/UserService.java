package luiz.augusto.fullstackapplication.services;

import luiz.augusto.fullstackapplication.entities.BasicUser;

public interface UserService {

    BasicUser registerBasicUser(String username, String email, String password);

    BasicUser logInBasicUser(String username, String password);
}
