package io.luliin.mailservice.mail;

import com.sendgrid.helpers.mail.objects.Personalization;
import io.luliin.mailservice.domain.AppUser;

/**
 * @author Julia Wigenstedt
 * Date: 2022-01-23
 */
public class Template {

    /**
     * Setting the variables to use for sending information to twilio about
     * the content in the email to send. The key must match the create account template
     * key on twilio to work
     * @param user AppUser information to use to populate dynamic template
     * @return Personalization object with all the variables used in the template
     */
    public static Personalization getWelcomeMessagePersonalization(AppUser user) {
        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("firstName", user.getFirstName());
        personalization.addDynamicTemplateData("username", user.getUsername());
        return personalization;
    }

    /**
     * Setting the variables to use for sending information to twilio about
     * the content in the email to send. The key must match the new password template
     * key on twilio to work
     * @param user AppUser information to use to populate dynamic template
     * @return Personalization object with all the variables used in the template
     */
    public static Personalization getNewPasswordPersonalization(AppUser user) {
        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("first_name", user.getFirstName());
        personalization.addDynamicTemplateData("updated_password", user.getUpdatedPassword());
        return personalization;
    }

    /**
     * Setting the variables to use for sending information to twilio about
     * the content in the email to send. The key must match the collaborator template
     * key on twilio to work
     * @param user AppUser information to use to populate dynamic template
     * @return Personalization object with all the variables used in the template
     */
    public static Personalization getCollaboratorPersonalization(AppUser user) {
        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("firstName", user.getFirstName());
        personalization.addDynamicTemplateData("username", user.getUsername());
        personalization.addDynamicTemplateData("ownerUsername", user.getOwnerUsername());
        personalization.addDynamicTemplateData("shoppingListName", user.getShoppingListName());

        return personalization;
    }
}
