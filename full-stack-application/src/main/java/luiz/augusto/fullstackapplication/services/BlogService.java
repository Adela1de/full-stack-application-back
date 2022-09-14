package luiz.augusto.fullstackapplication.services;

import luiz.augusto.fullstackapplication.entities.Article;

public interface BlogService {

    Article addNewArticle(Long basicUserId, String title, String text);
}
