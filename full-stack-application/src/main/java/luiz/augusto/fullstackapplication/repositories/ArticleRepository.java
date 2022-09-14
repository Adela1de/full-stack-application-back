package luiz.augusto.fullstackapplication.repositories;

import luiz.augusto.fullstackapplication.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
