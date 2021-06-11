package com.homeworkoverflow.homeworkoverflowbackend.routers;

import com.homeworkoverflow.homeworkoverflowbackend.controllers.AuthController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class auth_router {

    @Autowired
    private AuthController authController;

    @RequestMapping(value="/signup/", method=RequestMethod.POST)
    public ResponseEntity signup(@RequestBody String tokenBody) {
        return authController.signup(tokenBody);
    }
    
    @RequestMapping(value="/login/", method=RequestMethod.POST)
    public ResponseEntity login(@RequestBody String loginBody) {
        return authController.login(loginBody);
    }
}
