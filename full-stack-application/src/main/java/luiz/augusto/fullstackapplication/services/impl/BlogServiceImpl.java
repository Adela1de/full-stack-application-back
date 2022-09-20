package luiz.augusto.fullstackapplication.services.impl;

import luiz.augusto.fullstackapplication.entities.Article;
import luiz.augusto.fullstackapplication.entities.BasicUser;
import luiz.augusto.fullstackapplication.exceptions.IllegalActionException;
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

        validateLikeArticle(basicUser, article);

        basicUser.getLikedArticles().add(article);
        return basicUserRepository.save(basicUser);
    }

    @Override
    public BasicUser favoriteArticle(Long userId, Long articleId) {

        var basicUser = findBasicUserByIdOrElseThrowException(userId);
        var article = findArticleByIdOrElseThrowException(articleId);

        validateFavoriteArticle(basicUser, article);

        basicUser.getFavoriteArticles().add(article);
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

    private void validateLikeArticle(BasicUser basicUser, Article article)
    {
        if(basicUser.getLikedArticles().contains(article))
            throw new IllegalActionException("User already liked this article");
    }

    private void validateFavoriteArticle(BasicUser basicUser, Article article)
    {
        if(basicUser.getFavoriteArticles().contains(article))
            throw new IllegalActionException("Article is already in users favorites");
    }
}
