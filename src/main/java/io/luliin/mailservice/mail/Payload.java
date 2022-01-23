package io.luliin.mailservice.mail;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import io.luliin.mailservice.domain.AppUser;

/**
 * @author Julia Wigenstedt
 * Date: 2022-01-23
 */
public class Payload {
    private final Mail mail;

    public Payload(String from, String subject, String templateId, AppUser user, TemplateEnum templateEnum) {
        this.mail = new Mail();
        // select with personalization we wanna use
        Personalization personalization = new Personalization();
        switch (templateEnum) {
            case CREATE_ACCOUNT: personalization  = Template.getWelcomeMessagePersonalization(user.getUsername()); break;
            case NEW_PASSWORD: personalization = Template.getNewPasswordPersonalization(user); break;
        }
        // Add who you wanna send the email to
        personalization.addTo(new Email(user.getEmail()));
        // set who the sender is
        mail.setFrom(new Email(from));
        // set the subject line of the email
        mail.setSubject(subject);
        // add personal variables that match the template on twilio
        mail.addPersonalization(personalization);
        // the id for the template
        mail.setTemplateId(templateId);
    }

    public Mail getMailWithPayload() {
        return this.mail;
    }
}
