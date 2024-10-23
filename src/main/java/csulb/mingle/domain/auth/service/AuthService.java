package csulb.mingle.domain.auth.service;

import csulb.mingle.domain.auth.dto.request.VerifyEmailRequest;
import csulb.mingle.domain.auth.dto.response.VerifyEmailResponse;
import csulb.mingle.domain.auth.exception.AuthException;
import csulb.mingle.domain.auth.exception.AuthExceptionType;
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

    public static final String VERIFIED_EMAIL = "verified:";

    private final UserRepository userRepository;
    private final RedisUtil redisUtil;

//    public SignupResponse signup(SignUpRequest request) {
//
//        validateVerifiedEmail(request);
//
//        String encodedPassword = bcryptService.encodeBcrypt(request.getPassword());
//        User user = request.toUser(encodedPassword);
//        userRepository.save(user);
//
//        // 인증 정보 삭제
//        redisTemplate.delete("verified:" + request.getEmail());
//
//        return SignupResponse.from(user);
//    }

    public VerifyEmailResponse verifyEmail(VerifyEmailRequest request) {
        String storedCode = redisUtil.getData(UNVERIFIED_PREFIX + request.email());
        verifyUnexpiredCode(storedCode);
        verifyRightCode(request, storedCode);
        redisUtil.setDataExpire(VERIFIED_PREFIX + request.email(), "true", VERIFIED_USER_DURATION_MINUTES);
        return new VerifyEmailResponse(request.email(), true);
    }

    private void verifyUnexpiredCode(String storedCode) {
        if (storedCode == null) {
            throw new AuthException(AuthExceptionType.EXPIRED_VERIFICATION_CODE);
        }
    }

    private void verifyRightCode(VerifyEmailRequest request, String storedCode) {
        if (!storedCode.equals(request.verificationCode())) {
            throw new AuthException(AuthExceptionType.WRONG_VERIFICATION_CODE);
        }
    }


//    private void validateVerifiedEmail(SignUpRequest request) {
//        String verified = redisTemplate.opsForValue().get(VERIFIED_EMAIL + request.email());
//
//        if (verified == null) {
//            throw new UserException(UserExceptionType.NEED_VERIFIED_EMAIL);
//        }
//    }

}
