package luiz.augusto.fullstackapplication.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String email;
    private String role;
    private boolean enabled;

}
