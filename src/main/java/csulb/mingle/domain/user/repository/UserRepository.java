package csulb.mingle.domain.user.repository;

import csulb.mingle.domain.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    boolean existsUserByEmail(String userEmail);

    void save(User user);
}
