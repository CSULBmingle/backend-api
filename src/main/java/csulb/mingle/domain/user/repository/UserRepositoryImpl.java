package csulb.mingle.domain.user.repository;

import csulb.mingle.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean existsUserByEmail(String userEmail) {
        return userJpaRepository.existsUserByEmail(userEmail);
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }
}
