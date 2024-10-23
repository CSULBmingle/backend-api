package csulb.mingle.domain.auth.dto.response;

import lombok.Builder;

public record VerifyEmailResponse(

        String email,
        boolean isSuccess
) {
}
