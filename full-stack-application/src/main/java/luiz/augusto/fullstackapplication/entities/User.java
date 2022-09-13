package luiz.augusto.fullstackapplication.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String role;
    private boolean enabled = false;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
