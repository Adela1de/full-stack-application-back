package luiz.augusto.fullstackapplication.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class BasicUserRegisterRequestBody {

    @NotEmpty(message = "Username is empty!")
    private String username;
    @NotEmpty(message = "Email is empty!")
    private String email;
    @NotEmpty(message = "Password is empty!")
    private String password;

}
