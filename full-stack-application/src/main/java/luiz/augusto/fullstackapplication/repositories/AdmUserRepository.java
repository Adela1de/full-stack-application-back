package luiz.augusto.fullstackapplication.repositories;

import luiz.augusto.fullstackapplication.entities.AdmUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmUserRepository extends JpaRepository<AdmUser, Long> {
}
