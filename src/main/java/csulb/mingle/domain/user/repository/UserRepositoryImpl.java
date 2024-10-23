package csulb.mingle.domain.user.repository;

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
}
