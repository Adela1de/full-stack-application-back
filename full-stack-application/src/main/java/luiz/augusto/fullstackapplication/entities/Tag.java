package luiz.augusto.fullstackapplication.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "id")
    private Long tagId;
    private String name;
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Article> articles = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }
}
