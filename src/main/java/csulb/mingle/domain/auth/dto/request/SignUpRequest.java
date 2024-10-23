package csulb.mingle.domain.auth.dto.request;

import csulb.mingle.domain.user.entity.User;
import jakarta.validation.constraints.*;

public record SignUpRequest(

        @Pattern(regexp = NAME_FORMAT, message = "Please enter your name without blank and special character.")
        @Size(min = 1, max = 50, message = "The name length should be between 1 and 50.")
        @NotBlank(message = "Please enter your name.")
        @NotNull(message = "Please enter your name.")
        String firstname,

        @Pattern(regexp = NAME_FORMAT, message = "Please enter your name without blank and special character.")
        @Size(min = 1, max = 50, message = "The name length should be between 1 and 50.")
        @NotBlank(message = "Please enter your name.")
        @NotNull(message = "Please enter your name.")
        String lastname,

        @Size(min = 1, max = 30, message = "The username length should be between 1 and 30.")
        @NotBlank(message = "Please enter your username.")
        @NotNull(message = "Please enter your username.")
        String username,

//        @Pattern(regexp = EMAIL_FORMAT, message = "Please enter your school account email.")
        @NotBlank(message = "Please enter your email.")
        @NotNull(message = "Please enter your email.")
        String email,

        @Pattern(regexp = PASSWORD_FORMAT, message = "Password conditions do not match.")
        @NotBlank(message = "Please enter your password.")
        @NotNull(message = "Please enter your password.")
        String password
) {
    private static final String NAME_FORMAT = "^[a-zA-Z]+$";
    private static final String EMAIL_FORMAT = "^[a-zA-Z0-9._%+-]+@student\\.csulb\\.edu$";
    private static final String PASSWORD_FORMAT = "^(?=(.*[a-z]){1,})(?=(.*[A-Z]){1,})(?=(.*\\d){1,})(?=(.*[\\W_]){1,})(?!.*\\s).{8,16}$";

    public User toUser(String encodedPassword) {

        return User.builder()
                .firstname(this.firstname)
                .lastname(this.lastname)
                .username(this.username)
                .email(this.email)
                .password(encodedPassword)
                .build();
    }
}
