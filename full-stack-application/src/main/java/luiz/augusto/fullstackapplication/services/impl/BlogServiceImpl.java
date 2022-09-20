package luiz.augusto.fullstackapplication.services.impl;

import luiz.augusto.fullstackapplication.entities.Article;
import luiz.augusto.fullstackapplication.entities.BasicUser;
import luiz.augusto.fullstackapplication.entities.Tag;
import luiz.augusto.fullstackapplication.exceptions.IllegalActionException;
import luiz.augusto.fullstackapplication.exceptions.ObjectNotFoundException;
import luiz.augusto.fullstackapplication.repositories.ArticleRepository;
import luiz.augusto.fullstackapplication.repositories.BasicUserRepository;
import luiz.augusto.fullstackapplication.repositories.TagRepository;
import luiz.augusto.fullstackapplication.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private BasicUserRepository basicUserRepository;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Article addNewArticle(Long basicUserId, String title, String text, List<String> tags) {

        var basicUser = findBasicUserByIdOrElseThrowException(basicUserId);
        var article = new Article(title, text);

        var listOfCreateTags =  createNonExistentTags(tags);

        article.setUser(basicUser);
        article.setAuthor(basicUser.getUsername());
        article.setTags(listOfCreateTags);

        return articleRepository.save(article);
    }

    private List<Tag> createNonExistentTags(List<String> tags)
    {
        List<Tag> existingTags = new ArrayList<>();

        var nonExistentTags = tags.stream().filter(this::tagDoesNotExists).collect(Collectors.toList());
        nonExistentTags = nonExistentTags.stream().distinct().collect(Collectors.toList());
        var existentTagsString = tags.stream().filter(
                x -> !tagDoesNotExists(x)).collect(Collectors.toList()
        );

        existentTagsString.forEach(x -> existingTags.add(findTagByNameOrElseThrowException(x)));
        nonExistentTags.forEach(x -> existingTags.add(tagRepository.save(new Tag(x))));

        return existingTags;
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

    @Override
    public Article editExistingArticle(Long articleId, String title, String text, List<String> tags) {
        var article = findArticleByIdOrElseThrowException(articleId);

        article.setTitle(title);
        article.setText(text);
        article.setTags(createNonExistentTags(tags));

        articleRepository.save(article);
        return article;
    }

    @Override
    public List<Article> getArticlesByTag(String tagName)
    {

        var tag = findTagByNameOrElseThrowException(tagName);

        return articleRepository.
                findAll().
                stream().
                filter(
                        x -> x.getTags().contains(tag)
                ).
                collect(Collectors.toList());
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

    private Tag findTagByIdOrElseThrowException(Long tagId)
    {
        return tagRepository.findById(tagId).orElseThrow(() -> new ObjectNotFoundException("Tag not found !"));
    }

    private Tag findTagByNameOrElseThrowException(String tagName)
    {
        return tagRepository.findByName(tagName).orElseThrow(() -> new ObjectNotFoundException("Tag not found !"));
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

    private boolean tagDoesNotExists(String tagName)
    {
        return !tagRepository.findByName(tagName).isPresent();
    }
}
