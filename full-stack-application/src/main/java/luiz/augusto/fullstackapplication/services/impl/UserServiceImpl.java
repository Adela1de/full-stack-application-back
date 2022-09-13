package luiz.augusto.fullstackapplication.services.impl;

import luiz.augusto.fullstackapplication.entities.BasicUser;
import luiz.augusto.fullstackapplication.repositories.UserRepository;
import luiz.augusto.fullstackapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public BasicUser registerBasicUser(String username, String email, String password) {
        var user = new BasicUser(username, email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }
}
