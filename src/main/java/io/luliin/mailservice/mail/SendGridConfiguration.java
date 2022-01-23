package io.luliin.mailservice.mail;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Julia Wigenstedt
 * Date: 2022-01-23
 */
@Configuration
public class SendGridConfiguration {

    @Value("${spring.sendgrid.api-key}")
    private String sendGridKey;

    @Bean
    public SendGrid getSendGrid() {
        return new SendGrid(sendGridKey);
    }
}
