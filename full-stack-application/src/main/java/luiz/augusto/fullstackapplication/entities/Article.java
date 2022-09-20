package luiz.augusto.fullstackapplication.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @JsonIgnore
    private BasicUser user;
    @ManyToMany(mappedBy = "likedArticles")
    @JsonIgnore
    private List<BasicUser> likedBy = new ArrayList<>();
    @ManyToMany(mappedBy = "favoriteArticles")
    @JsonIgnore
    private List<BasicUser> favoriteBy = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "tb_tags_in_article",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    public Article(String title, String text)
    {
        this.title = title;
        this.text = text;
    }

}
