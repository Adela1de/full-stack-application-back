package luiz.augusto.fullstackapplication.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luiz.augusto.fullstackapplication.entities.Article;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BasicUserDTO {

    private String username;
    private String email;
    private String role;
    private boolean enabled;
    private List<Article> likedArticles;
}
