package luiz.augusto.fullstackapplication.services.impl;

import luiz.augusto.fullstackapplication.entities.BasicUser;
import luiz.augusto.fullstackapplication.entities.User;
import luiz.augusto.fullstackapplication.exceptions.EmailAlreadyInUseException;
import luiz.augusto.fullstackapplication.exceptions.IncorrectCredentialException;
import luiz.augusto.fullstackapplication.exceptions.ObjectNotFoundException;
import luiz.augusto.fullstackapplication.exceptions.UsernameAlreadyInUseException;
import luiz.augusto.fullstackapplication.repositories.BasicUserRepository;
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
    private BasicUserRepository basicUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public BasicUser registerBasicUser(String username, String email, String password) {

        if(userRepository.findByEmail(email).isPresent())
            throw new EmailAlreadyInUseException("Email Already in use");

        if(basicUserRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyInUseException("Username already in use! ");

        var user = new BasicUser(username, email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return user;
    }

    @Override
    public BasicUser logInBasicUser(String username, String password)
    {
        var basicUser = findBasicUserByUsernameOrElseThrowException(username);

        if(!passwordEncoder.matches(password, basicUser.getPassword()))
            throw new IncorrectCredentialException("Wrong credentials ");

        return basicUser;
    }

    private BasicUser findBasicUserByUsernameOrElseThrowException(String username)
    {
        return basicUserRepository.findByUsername(username).orElseThrow(
                () -> new ObjectNotFoundException("User not found!")
        );
    }

    private User findUserByEmailOrElseThrowException(String email)
    {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ObjectNotFoundException("User not found!")
        );
    }
}
