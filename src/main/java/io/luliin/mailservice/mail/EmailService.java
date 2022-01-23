package io.luliin.mailservice.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Julia Wigenstedt
 * Date: 2022-01-23
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final SendGrid sendGrid;

    @Value("${sendgrid.email}")
    private String fromEmail;


    public Response sendEmail(EmailRequest emailRequest) {

        Mail mail = new Mail(new Email(fromEmail),
                emailRequest.getSubject(),
                new Email(emailRequest.getTo()),
                new Content("text/plain" ,emailRequest.getBody()));

        mail.setReplyTo(new Email(fromEmail));
        Request request = new Request();
        Response response = null;
        try {
            log.info("In send body");
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = this.sendGrid.api(request);
            log.info("Response {}", response);
            log.info("Response status code {}", response.getStatusCode());
            log.info("Response body {}", response.getBody());

        } catch (IOException e) {
            log.error("sendEmail(): Error message {}", e.getMessage(), e);
        }

        return response;
    }
}
