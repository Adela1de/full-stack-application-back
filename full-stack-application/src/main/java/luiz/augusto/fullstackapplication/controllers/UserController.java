package luiz.augusto.fullstackapplication.controllers;

import luiz.augusto.fullstackapplication.dtos.BasicUserDTO;
import luiz.augusto.fullstackapplication.mappers.UserMapper;
import luiz.augusto.fullstackapplication.requests.BasicUserLoginRequestBody;
import luiz.augusto.fullstackapplication.requests.BasicUserRegisterRequestBody;
import luiz.augusto.fullstackapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    public ResponseEntity<String> test()
    {
        return ResponseEntity.ok("successful!");
    }

    @PostMapping("/register")
    public ResponseEntity<BasicUserDTO> registerBasicUser
            (@RequestBody @Valid BasicUserRegisterRequestBody basicUserRegisterRequestBody)
    {
        var basicUser = userService.registerBasicUser(
                basicUserRegisterRequestBody.getUsername(),
                basicUserRegisterRequestBody.getEmail(),
                basicUserRegisterRequestBody.getPassword()
        );
        var basicUserDTO = userMapper.toBasicUserDTO(basicUser);
        return ResponseEntity.ok().body(basicUserDTO);
    }

    @PostMapping("login")
    public ResponseEntity<BasicUserDTO> loginBasicUser
            (@RequestBody @Valid BasicUserLoginRequestBody basicUserLoginRequestBody)
    {
        var loggedUser = userService.logInBasicUser(
                basicUserLoginRequestBody.getUsername(),
                basicUserLoginRequestBody.getPassword()
        );
        var loggedUserDTO = userMapper.toBasicUserDTO(loggedUser);
        return ResponseEntity.ok().body(loggedUserDTO);
    }
}

