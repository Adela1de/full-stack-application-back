package luiz.augusto.fullstackapplication.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BasicUserRequestBody {

    private String username;
    private String email;
    private String password;

}
