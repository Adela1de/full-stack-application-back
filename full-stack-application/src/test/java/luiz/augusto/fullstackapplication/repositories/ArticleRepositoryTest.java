package luiz.augusto.fullstackapplication.repositories;

import luiz.augusto.fullstackapplication.entities.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
@DataJpaTest
@DisplayName("Tests for Article repository")
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("Save persists article when successful")
    void save_PersistArticle_WhenSuccessful()
    {
        var article = createArticle();
        var savedArticle = articleRepository.save(article);

        Assertions.assertThat(savedArticle).isNotNull();
        Assertions.assertThat(savedArticle.getArticleId()).isNotNull();
        Assertions.assertThat(savedArticle.getTitle()).isEqualTo(article.getTitle());
        Assertions.assertThat(savedArticle.getText()).isEqualTo(article.getText());

    }

    private Article createArticle()
    {
        return new Article("Article test", "Article Test");
    }

}