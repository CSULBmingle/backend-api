package csulb.mingle.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record VerifyEmailRequest(
//        @Pattern(regexp = EMAIL_FORMAT, message = "Please enter your school account email.")
        @NotBlank(message = "Please enter your email.")
        @NotNull(message = "Please enter your email.")
        String email,

        @Pattern(regexp = VERIFICATION_CODE_FORMAT, message = "Verification code must be exactly 6 digits.")
        @NotBlank(message = "Please enter the verification code.")
        @NotNull(message = "Please enter your verification code.")
        String verificationCode

) {
        private static final String EMAIL_FORMAT = "^[a-zA-Z0-9._%+-]+@student\\.csulb\\.edu$";
        public static final String VERIFICATION_CODE_FORMAT = "^[0-9]{6}$";
}