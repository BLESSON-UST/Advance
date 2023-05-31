package com.example.Springbootkuberneties.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class controller {
    @GetMapping("/welcome")
    public String welcome()
    {
        return "Welcome Blesson";
    }
}
