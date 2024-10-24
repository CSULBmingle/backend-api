package csulb.mingle.domain.auth.controller;

import csulb.mingle.domain.auth.dto.request.SendEmailRequest;
import csulb.mingle.domain.auth.dto.request.VerifyEmailRequest;
import csulb.mingle.domain.auth.dto.response.VerifyEmailResponse;
import csulb.mingle.domain.auth.service.AuthService;
import csulb.mingle.domain.auth.service.EmailService;
import csulb.mingle.domain.auth.dto.request.SignUpRequest;
import csulb.mingle.domain.auth.dto.response.SignupResponse;
import csulb.mingle.global.common.dto.response.ResponseMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;

    @PostMapping("/email/verification-request")
    public ResponseEntity<ResponseMessage> requestVerification(@Valid @RequestBody SendEmailRequest request) {
        emailService.sendVerificationEmail(request.email());

        ResponseMessage message = new ResponseMessage(null, HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK)
                .body(message);
    }

    @PostMapping("/email/verify")
    public ResponseEntity<ResponseMessage> verifyEmail(@Valid @RequestBody VerifyEmailRequest request) {
        VerifyEmailResponse response = authService.verifyEmail(request);

        ResponseMessage message = new ResponseMessage(response, HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK)
                .body(message);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage> signup(@Valid @RequestBody SignUpRequest request) {
        SignupResponse response = authService.signup(request);

        ResponseMessage message = new ResponseMessage(response, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(message);
    }
}
