package luiz.augusto.fullstackapplication.services;

import luiz.augusto.fullstackapplication.entities.Article;
import luiz.augusto.fullstackapplication.entities.BasicUser;

public interface BlogService {

    Article addNewArticle(Long basicUserId, String title, String text);

    BasicUser likeArticle(Long userId, Long articleId);

    BasicUser favoriteArticle(Long userId, Long articleId);
}
