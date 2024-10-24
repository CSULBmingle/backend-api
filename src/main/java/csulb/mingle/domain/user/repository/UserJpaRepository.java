package csulb.mingle.domain.user.repository;

import csulb.mingle.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserJpaRepository extends JpaRepository<User, Long> {

    boolean existsUserByEmail(String userEmail);

}
