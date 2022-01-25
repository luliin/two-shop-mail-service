package io.luliin.mailservice.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import io.luliin.mailservice.domain.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

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
    @Value("${sendgrid.welcome.template}")
    private String WELCOME_MESSAGE_TEMPLATE_ID;
    @Value("${sendgrid.password.template}")
    private String PASSWORD_MESSAGE_TEMPLATE_ID;
    @Value("${sendgrid.collaborator.template}")
    private String COLLABORATOR_MESSAGE_TEMPLATE_ID;


    /**
     * Sends an email to a user that created an account
     * @param appUser AppUser object to send email to and to populate dynamic fields in email
     */
    public void sendWelcomeMessage(AppUser appUser) {
        Payload payload = new Payload(fromEmail, "Välkommen till TwoShop!", WELCOME_MESSAGE_TEMPLATE_ID, appUser, TemplateEnum.CREATE_ACCOUNT);
        throwErrorIfStatusCodeNotValid(sendEmail(payload.getMailWithPayload()));
    }

    /**
     * Sends an email when a user has requested a password reset
     * @param appUser AppUser object to send email to and to populate dynamic fields in email
     */
    public void sendPasswordMessage(AppUser appUser) {
        Payload payload = new Payload(fromEmail, "Ditt lösenord har återställts", PASSWORD_MESSAGE_TEMPLATE_ID, appUser, TemplateEnum.NEW_PASSWORD);
        throwErrorIfStatusCodeNotValid(sendEmail(payload.getMailWithPayload()));
    }

    /**
     * Sends an email when a user has requested a password reset
     * @param appUser AppUser object to send email to and to populate dynamic fields in email
     */
    public void sendCollaboratorMessage(AppUser appUser) {
        Payload payload = new Payload(fromEmail, "Du har fått en inbjudan!", PASSWORD_MESSAGE_TEMPLATE_ID, appUser, TemplateEnum.COLLABORATOR);
        throwErrorIfStatusCodeNotValid(sendEmail(payload.getMailWithPayload()));
    }

    public Response sendEmail(Mail payload) {

        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(payload.build());
            Response response = sendGrid.api(request);
            log.info("Status code: {}", response.getStatusCode());
            return response;
        } catch (IOException ex) {
            throw new ResponseStatusException(BAD_REQUEST, ex.getMessage());
        }
    }

    /**
     * Check if the status code from the request is 200-299
     * @param response the response to check the status code of
     * @throws RuntimeException if status code is not a 2xx
     */
    public void throwErrorIfStatusCodeNotValid(Response response) {
        var statusCode = response.getStatusCode();
        if(statusCode < 200 || statusCode > 299) throw new RuntimeException("Could not send email");
    }


}
