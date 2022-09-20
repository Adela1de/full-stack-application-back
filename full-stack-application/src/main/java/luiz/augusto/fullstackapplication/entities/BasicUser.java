package luiz.augusto.fullstackapplication.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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
    @ManyToMany
    @JoinTable(name = "tb_articles_liked_by_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> likedArticles = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "tb_articles_favorite_by_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> favoriteArticles = new ArrayList<>();

    public BasicUser(String username, String email) {
        super(email, "USER");
        this.username = username;
    }
}
