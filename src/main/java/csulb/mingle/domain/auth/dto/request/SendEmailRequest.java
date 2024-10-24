package csulb.mingle.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SendEmailRequest(
//        @Pattern(regexp = EMAIL_FORMAT, message = "Please enter your school account email.")
        @NotBlank(message = "Please enter your email.")
        @NotNull(message = "Please enter your email.")
        String email
) {
    private static final String EMAIL_FORMAT = "^[a-zA-Z0-9._%+-]+@student\\.csulb\\.edu$";

}
