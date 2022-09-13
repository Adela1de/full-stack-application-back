package luiz.augusto.fullstackapplication.mappers;

import luiz.augusto.fullstackapplication.dtos.BasicUserDTO;
import luiz.augusto.fullstackapplication.dtos.UserDTO;
import luiz.augusto.fullstackapplication.entities.BasicUser;
import luiz.augusto.fullstackapplication.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static final ModelMapper modelMapper = new ModelMapper();

    public UserDTO toUserDTO(User user)
    {
        var userDTO = new UserDTO();
        userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    public User toUser(UserDTO userDTO)
    {
        var user = new User();
        user = modelMapper.map(userDTO, User.class);
        return user;
    }

    //BASIC USER DTO MAPPING
    public BasicUserDTO toBasicUserDTO(BasicUser basicUser)
    {
        var basicUserDTO = new BasicUserDTO();
        basicUserDTO = modelMapper.map(basicUser, BasicUserDTO.class);
        return basicUserDTO;
    }
}

