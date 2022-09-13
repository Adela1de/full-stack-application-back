package luiz.augusto.fullstackapplication.repositories;

import luiz.augusto.fullstackapplication.entities.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicUserRepository extends JpaRepository<BasicUser, Long> {
}
