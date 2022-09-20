package luiz.augusto.fullstackapplication.repositories;

import luiz.augusto.fullstackapplication.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
