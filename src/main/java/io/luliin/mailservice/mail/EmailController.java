package io.luliin.mailservice.mail;

import com.sendgrid.Response;
import io.luliin.mailservice.domain.AppUser;
import io.luliin.mailservice.dto.MailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Julia Wigenstedt
 * Date: 2022-01-23
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mail")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/welcome")
    public ResponseEntity<?> sendEmail(@RequestBody AppUser appUser) {
        emailService.sendWelcomeMessage(appUser);
        final String message = String.format("Welcome message sent to %s", appUser.getEmail());
        log.info(message);
        return ResponseEntity.ok(new MailResponse(message));


    }
}
