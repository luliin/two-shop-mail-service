package io.luliin.mailservice.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Julia Wigenstedt
 * Date: 2022-01-24
 */
@Configuration
public class RabbitConfig{

    @Bean
    public TopicExchange mailTopic() {
        return new TopicExchange("mail");
    }

    // Needs to be anonymous, otherwise two instances of this application will receive every other message.
    @Bean
    public Queue queue1() {
        return new Queue("welcome");
    }

    @Bean
    public Queue queue2() {
        return new Queue("password");
    }

    @Bean
    public Queue queue3() {
        return new Queue("collaborator");
    }

    @Bean
    public Binding welcomeBinding() {
        return BindingBuilder.bind(queue1())
                .to(mailTopic())
                .with("welcome.*");
    }

    @Bean
    public Binding passwordBinding() {
        return BindingBuilder.bind(queue2())
                .to(mailTopic())
                .with("password.*");
    }

    @Bean
    public Binding collaboratorBinding() {
        return BindingBuilder.bind(queue3())
                .to(mailTopic())
                .with("collaborator.*");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, final Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter(ObjectMapper objectMapper) {
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
