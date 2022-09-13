package luiz.augusto.fullstackapplication.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_adm_user")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AdmUser extends User{

    private String username;

    public AdmUser(String email) {
        super(email, "ADMIN");
    }
}
