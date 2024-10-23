package csulb.mingle.domain.auth.service;

import csulb.mingle.domain.auth.exception.AuthException;
import csulb.mingle.domain.auth.exception.AuthExceptionType;
import csulb.mingle.domain.user.exception.UserException;
import csulb.mingle.domain.user.exception.UserExceptionType;
import csulb.mingle.domain.user.repository.UserRepository;
import csulb.mingle.global.error.exception.BaseException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.security.SecureRandom;

@RequiredArgsConstructor
@Transactional
@Service
public class EmailService {

    public static final int RANDOM_CODE_LENGTH = 6;

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final UserRepository userRepository;
//    private final RedisUtil redisUtil;


    public void sendVerificationEmail(String email) {
        validateNonExistUser(email);
        try {
            String verificationCode = createRandomCode();
            MimeMessage mimeMessage = createEmailForm(email, verificationCode);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new AuthException(AuthExceptionType.EMAIL_SEND_FAILED);
        }
    }

    private void validateNonExistUser(String email) {
        if (userRepository.existsUserByEmail(email)) {
            throw new UserException(UserExceptionType.USER_ALREADY_EXIST);
        }
    }

    private String createRandomCode() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder builder = new StringBuilder(RANDOM_CODE_LENGTH);

        for (int i = 0; i < RANDOM_CODE_LENGTH; i++) {
            builder.append(secureRandom.nextInt(10));
        }
        return builder.toString();
    }

    private MimeMessage createEmailForm(String email, String verificationCode) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(email);
        helper.setSubject("Sign up for MINGLE - Email Verification");
        String content = setContext(verificationCode);
        helper.setText(content, true);
        return message;
    }

    private String setContext(String verificationCode) {
        Context context = new Context();
        context.setVariable("verificationCode", verificationCode);
        return templateEngine.process("mail/verification-email",context);
    }
}
