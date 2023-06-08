package com.example.UserLogin.Controller;

import com.example.UserLogin.Entity.User;
import com.example.UserLogin.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

   @PostMapping("/register")
    public User Register(@RequestBody  User user)
   {
       return authService.register(user);
   }

    @PostMapping("/login")
    public User Login(@RequestBody  User user)
    {
        return authService.login(user);
    }


}
