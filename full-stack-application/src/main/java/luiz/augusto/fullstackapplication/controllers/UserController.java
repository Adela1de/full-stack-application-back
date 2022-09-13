package luiz.augusto.fullstackapplication.controllers;

import luiz.augusto.fullstackapplication.dtos.BasicUserDTO;
import luiz.augusto.fullstackapplication.mappers.UserMapper;
import luiz.augusto.fullstackapplication.requests.BasicUserRequestBody;
import luiz.augusto.fullstackapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BasicUserDTO> registerBasicUser(@RequestBody BasicUserRequestBody basicUserRequestBody)
    {
        var basicUser = userService.registerBasicUser(
                basicUserRequestBody.getUsername(),
                basicUserRequestBody.getEmail(),
                basicUserRequestBody.getPassword()
        );
        var basicUserDTO = userMapper.toBasicUserDTO(basicUser);
        return ResponseEntity.ok().body(basicUserDTO);
    }
}
