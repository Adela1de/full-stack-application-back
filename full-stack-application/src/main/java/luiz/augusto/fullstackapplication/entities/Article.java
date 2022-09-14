package luiz.augusto.fullstackapplication.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tb_article")
@NoArgsConstructor
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long articleId;
    private String author;
    private String title;
    private String text;
    @ManyToOne
    @JoinColumn(
            name = "userId",
            foreignKey = @ForeignKey(name = "FK_ARTICLE_USER")
    )
    private BasicUser user;

    public Article(String title, String text)
    {
        this.title = title;
        this.text = text;
    }

}
