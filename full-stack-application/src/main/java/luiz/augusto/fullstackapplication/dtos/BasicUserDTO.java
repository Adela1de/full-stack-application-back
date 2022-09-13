package luiz.augusto.fullstackapplication.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BasicUserDTO {

    private String username;
    private String email;
    private String password;
    private boolean enabled;
}
