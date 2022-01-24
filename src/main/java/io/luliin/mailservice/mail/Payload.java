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
        Personalization personalization = new Personalization();
        switch (templateEnum) {
            case CREATE_ACCOUNT: personalization  = Template.getWelcomeMessagePersonalization(user); break;
            case NEW_PASSWORD: personalization = Template.getNewPasswordPersonalization(user); break;
        }
        personalization.addTo(new Email(user.getEmail()));
        mail.setFrom(new Email(from, "TwoShop Team"));
        mail.setSubject(subject);
        mail.addPersonalization(personalization);
        mail.setTemplateId(templateId);
    }

    public Mail getMailWithPayload() {
        return this.mail;
    }
}
