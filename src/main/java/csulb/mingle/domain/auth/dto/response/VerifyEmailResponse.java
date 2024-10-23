package csulb.mingle.domain.auth.dto.response;

public record VerifyEmailResponse(

        String email,
        boolean isSuccess
) {
    public static VerifyEmailResponse from(String email, boolean isSuccess) {
        return new VerifyEmailResponse(email, isSuccess);
    }
}
