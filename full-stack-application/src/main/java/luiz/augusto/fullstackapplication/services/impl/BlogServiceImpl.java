package luiz.augusto.fullstackapplication.services.impl;

import luiz.augusto.fullstackapplication.entities.Article;
import luiz.augusto.fullstackapplication.entities.BasicUser;
import luiz.augusto.fullstackapplication.exceptions.ObjectNotFoundException;
import luiz.augusto.fullstackapplication.repositories.ArticleRepository;
import luiz.augusto.fullstackapplication.repositories.BasicUserRepository;
import luiz.augusto.fullstackapplication.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private BasicUserRepository basicUserRepository;

    @Override
    public Article addNewArticle(Long basicUserId, String title, String text) {

        var basicUser = findBasicUserByIdOrElseThrowException(basicUserId);
        var article = new Article(title, text);
        article.setUser(basicUser);
        article.setAuthor(basicUser.getUsername());

        return articleRepository.save(article);
    }

    @Override
    public BasicUser likeArticle(Long userId, Long articleId) {

        var basicUser = findBasicUserByIdOrElseThrowException(userId);
        var article = findArticleByIdOrElseThrowException(articleId);

        basicUser.getLikedArticles().add(article);
        return basicUserRepository.save(basicUser);
    }

    private BasicUser findBasicUserByIdOrElseThrowException(Long basicUserId)
    {
        return basicUserRepository.findById(basicUserId).orElseThrow(
                () -> new ObjectNotFoundException("User not found!")
        );
    }

    private Article findArticleByIdOrElseThrowException(Long articleId)
    {
        return articleRepository.findById(articleId).orElseThrow(
                () -> new ObjectNotFoundException("Article not found!")
        );
    }
}
