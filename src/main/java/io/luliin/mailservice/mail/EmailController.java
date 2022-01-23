package io.luliin.mailservice.mail;

import com.sendgrid.Response;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/mail")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        final Response response = emailService.sendEmail(emailRequest);

        if(response.getStatusCode()==200 || response.getStatusCode()==202) {
            return ResponseEntity.ok("Email was sent successfully");
        }
        return new ResponseEntity<>("Email failed", HttpStatus.NOT_FOUND);


    }
}
