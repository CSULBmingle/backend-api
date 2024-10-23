package csulb.mingle.domain.auth.dto.response;

public record SignupResponse(
        String firstname,
        String lastname,
        String username,
        String email
) {
    public static SignupResponse of( String firstname,  String lastname,  String username, String email) {
        return new SignupResponse(
                firstname,
                lastname,
                username,
                email
        );
    }
}
