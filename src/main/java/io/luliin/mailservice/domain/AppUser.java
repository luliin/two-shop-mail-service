package io.luliin.mailservice.domain;

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
public class AppUser {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String updatedPassword;
}
