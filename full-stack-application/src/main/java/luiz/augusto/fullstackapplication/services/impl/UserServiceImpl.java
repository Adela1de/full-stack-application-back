package luiz.augusto.fullstackapplication.services.impl;

import luiz.augusto.fullstackapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserService userService;


}
