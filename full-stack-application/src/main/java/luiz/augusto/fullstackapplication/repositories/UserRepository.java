package luiz.augusto.fullstackapplication.repositories;

import luiz.augusto.fullstackapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
