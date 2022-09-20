package luiz.augusto.fullstackapplication.services;

import luiz.augusto.fullstackapplication.entities.Article;
import luiz.augusto.fullstackapplication.entities.BasicUser;

import java.util.List;

public interface BlogService {

    Article addNewArticle(Long basicUserId, String title, String text, List<String> tags);

    BasicUser likeArticle(Long userId, Long articleId);

    BasicUser favoriteArticle(Long userId, Long articleId);

    Article editExistingArticle(Long articleId, String title, String text, List<String> tags);
}
