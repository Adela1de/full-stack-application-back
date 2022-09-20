package luiz.augusto.fullstackapplication.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tb_tag")
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    private Long name;
    @ManyToMany(mappedBy = "tags")
    private List<Article> articles = new ArrayList<>();

    public Tag(Long name) {
        this.name = name;
    }
}
