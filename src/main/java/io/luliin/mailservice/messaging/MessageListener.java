package io.luliin.mailservice.messaging;

import io.luliin.mailservice.domain.AppUser;
import io.luliin.mailservice.dto.MailResponse;
import io.luliin.mailservice.mail.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Julia Wigenstedt
 * Date: 2022-01-24
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class MessageListener {

    private final EmailService emailService;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "welcome")
    public void receiveWelcomeRequest(AppUser appUser) {
        emailService.sendWelcomeMessage(appUser);
        final String message = String.format("Welcome message sent to %s", appUser.getEmail());
        log.info(message);
        rabbitTemplate.convertAndSend("mail", "confirm.welcome", new MailResponse(message));
    }


    @RabbitListener(queues = "password")
    public void receivePasswordRequest(AppUser appUser) {
        emailService.sendPasswordMessage(appUser);
        final String message = String.format("Reset password message sent to %s", appUser.getEmail());
        log.info(message);
        rabbitTemplate.convertAndSend("mail", "confirm.password", new MailResponse(message));
    }


    @RabbitListener(queues = "collaborator")
    public void receiveCollaboratorRequest(AppUser appUser) {
        emailService.sendCollaboratorMessage(appUser);
        final String message = String.format("Collaborator invitation message sent to %s", appUser.getEmail());
        log.info(message);
        rabbitTemplate.convertAndSend("mail", "confirm.collaborator", new MailResponse(message));
    }


}
