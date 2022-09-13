package luiz.augusto.fullstackapplication.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@Table(name = "tb_user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("userId")
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;
    private String username;
    @Column(name = "email_address")
    private String email;
    private String password;
    private String role;
    private boolean enabled = false;

    public User(String email, String role) {
        this.email = email;
        this.role = role;
    }
}