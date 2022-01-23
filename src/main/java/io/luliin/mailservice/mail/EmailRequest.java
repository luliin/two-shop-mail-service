package io.luliin.mailservice.mail;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Julia Wigenstedt
 * Date: 2022-01-23
 */
@Data
@AllArgsConstructor
public class EmailRequest {

    private String to;
    private String subject;
    private String body;
}
