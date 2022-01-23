package io.luliin.mailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Julia Wigenstedt
 * Date: 2022-01-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailResponse {
    private String message;
}
