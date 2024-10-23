package csulb.mingle.domain.auth.service;

import csulb.mingle.domain.auth.dto.request.SignUpRequest;
import csulb.mingle.domain.auth.dto.request.VerifyEmailRequest;
import csulb.mingle.domain.auth.dto.response.SignupResponse;
import csulb.mingle.domain.auth.dto.response.VerifyEmailResponse;
import csulb.mingle.domain.auth.exception.AuthException;
import csulb.mingle.domain.auth.exception.AuthExceptionType;
import csulb.mingle.domain.user.entity.User;
import csulb.mingle.domain.user.exception.UserException;
import csulb.mingle.domain.user.exception.UserExceptionType;
import csulb.mingle.domain.user.repository.UserRepository;
import csulb.mingle.global.util.redis.RedisUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static csulb.mingle.global.util.redis.RedisConstants.*;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final BcryptService bcryptService;
    private final UserRepository userRepository;
    private final RedisUtil redisUtil;

    public SignupResponse signup(SignUpRequest request) {
        validateVerifiedEmail(request);

        String encodedPassword = bcryptService.encodeBcrypt(request.password());
        User user = request.toUser(encodedPassword);
        userRepository.save(user);

        redisUtil.deleteData(VERIFIED_PREFIX + request.email());

        return SignupResponse.of(
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getEmail());
    }

    public VerifyEmailResponse verifyEmail(VerifyEmailRequest request) {
        String storedCode = redisUtil.getData(UNVERIFIED_PREFIX + request.email());

        validateUnexpiredCode(storedCode);
        validateRightCode(request, storedCode);

        redisUtil.setDataExpire(VERIFIED_PREFIX + request.email(), "true", VERIFIED_USER_DURATION_MINUTES);
        return VerifyEmailResponse.from(request.email(), true);
    }

    private void validateUnexpiredCode(String storedCode) {
        if (storedCode == null) {
            throw new AuthException(AuthExceptionType.EXPIRED_VERIFICATION_CODE);
        }
    }

    private void validateRightCode(VerifyEmailRequest request, String storedCode) {
        if (!storedCode.equals(request.verificationCode())) {
            throw new AuthException(AuthExceptionType.WRONG_VERIFICATION_CODE);
        }
    }

    private void validateVerifiedEmail(SignUpRequest request) {
        String verified = redisUtil.getData(VERIFIED_PREFIX + request.email());

        if (verified == null) {
            throw new UserException(UserExceptionType.NEED_VERIFIED_EMAIL);
        }
    }

}
