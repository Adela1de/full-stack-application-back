package luiz.augusto.fullstackapplication.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "tb_basic_user")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BasicUser extends User{

    private String username;
    @OneToMany(mappedBy = "user")
    private List<Article> articles;

    public BasicUser(String username, String email) {
        super(email, "USER");
        this.username = username;
    }
}
