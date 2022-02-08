package io.luliin.mailservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Julia Wigenstedt
 * Date: 2022-02-08
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://twoshop.netlify.app"})
public class Controller {

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pong");
    }
}
