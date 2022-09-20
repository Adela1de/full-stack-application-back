package luiz.augusto.fullstackapplication.repositories;

import luiz.augusto.fullstackapplication.entities.Article;
import luiz.augusto.fullstackapplication.entities.BasicUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for User repository")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BasicUserRepository basicUserRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("Save persists user when successful")
    void save_PersistUser_WhenSuccessful()
    {
        var basicUser = createBasicUser();
        basicUser.setPassword("00000000");
        var savedBasicUser = userRepository.save(basicUser);

        Assertions.assertThat(savedBasicUser).isNotNull();
        Assertions.assertThat(savedBasicUser.getUserId()).isNotNull();
        Assertions.assertThat(savedBasicUser.getUsername()).isEqualTo(basicUser.getUsername());
        Assertions.assertThat(savedBasicUser.getEmail()).isEqualTo(basicUser.getEmail());
        Assertions.assertThat(savedBasicUser.getPassword()).isEqualTo(basicUser.getPassword());
    }

    @Test
    @DisplayName("Save finds user by email when successful")
    void save_FindsByEmail_WhenSuccessful()
    {
        var basicUser = createBasicUser();
        basicUser.setPassword("00000000");
        var savedUser = userRepository.save(basicUser);

        var foundUser = userRepository.findByEmail(savedUser.getEmail()).get();

        Assertions.assertThat(foundUser).isNotNull();
        Assertions.assertThat(foundUser.getUserId()).isNotNull();
        Assertions.assertThat(foundUser.getEmail()).isEqualTo(savedUser.getEmail());
        Assertions.assertThat(foundUser.getPassword()).isEqualTo(savedUser.getPassword());

    }

    @Test
    @DisplayName("Save finds user by username when successful")
    void save_FindsByUsername_WhenSuccessful()
    {
        var basicUser = createBasicUser();
        basicUser.setPassword("00000000");
        var savedBasicUser = userRepository.save(basicUser);

        var basicUserFound = basicUserRepository.findByUsername(savedBasicUser.getUsername()).get();

        Assertions.assertThat(basicUserFound).isNotNull();
        Assertions.assertThat(basicUserFound.getUserId()).isNotNull();
        Assertions.assertThat(basicUserFound.getUsername()).isEqualTo(savedBasicUser.getUsername());
        Assertions.assertThat(basicUserFound.getEmail()).isEqualTo(savedBasicUser.getEmail());
        Assertions.assertThat(basicUserFound.getPassword()).isEqualTo(savedBasicUser.getPassword());

    }

    @Test
    @DisplayName("find basic user by username returns null when user is not found")
    void findBasicUserByUserName_ReturnsNull_WhenUserIsNotFound()
    {
        var basicUser = createBasicUser();
        basicUser.setPassword("00000000");
        userRepository.save(basicUser);

        var basicUserNotFound = basicUserRepository.findByUsername("Username").isEmpty();

        Assertions.assertThat(basicUserNotFound).isTrue();
    }

    @Test
    @DisplayName("find user by email returns null when user is not found")
    void findUserByEmail_ReturnsNull_WhenUserIsNotFound()
    {
        var basicUser = createBasicUser();
        basicUser.setPassword("00000000");
        userRepository.save(basicUser);

        var user = userRepository.findByEmail("Email").isEmpty();

        Assertions.assertThat(user).isTrue();
    }

    @Test
    @DisplayName("add article to a list when successful")
    void add_Article_toListOfArticlesLiked_WhenSuccessful()
    {
        var basicUser = createBasicUser();
        basicUser.setPassword("00000000");
        userRepository.save(basicUser);

        var article = createArticle();

        basicUser.getLikedArticles().add(article);
        var savedBasicUser = userRepository.save(basicUser);

        Assertions.assertThat(savedBasicUser.getLikedArticles()).isNotEmpty();
    }

    @Test
    @DisplayName("add article to a list when successful")
    void add_Article_toListOfArticlesFavorite_WhenSuccessful()
    {
        var basicUser = createBasicUser();
        basicUser.setPassword("00000000");
        userRepository.save(basicUser);

        var article = createArticle();

        basicUser.getFavoriteArticles().add(article);
        var savedBasicUser = userRepository.save(basicUser);

        Assertions.assertThat(savedBasicUser.getFavoriteArticles()).isNotEmpty();
    }


    private BasicUser createBasicUser()
    {
        return new BasicUser("TestUsername", "TestEmail");
    }

    private Article createArticle()
    {
        return new Article("Test title", "Test text");
    }
}