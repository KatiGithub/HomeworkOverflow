package com.homeworkoverflow.homeworkoverflowbackend.controllers;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuthException;
import com.homeworkoverflow.homeworkoverflowbackend.models.User;
import com.homeworkoverflow.homeworkoverflowbackend.repositories.AuthRepository;
import com.homeworkoverflow.homeworkoverflowbackend.utils.FirebaseAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthController {

    private ObjectMapper objectMapper;
    private HttpHeaders responseHeaders;

    private FirebaseAdmin firebaseAdmin;
    private AuthRepository authRepository;

    public AuthController(ObjectMapper objectMapper, FirebaseAdmin firebaseAdmin, AuthRepository authRepository) {
        this.objectMapper = objectMapper;
        this.responseHeaders = new HttpHeaders();
        this.responseHeaders.setContentType(MediaType.APPLICATION_JSON);


        this.firebaseAdmin = firebaseAdmin;
        this.authRepository = authRepository;
    }

    public ResponseEntity<String> login(String loginBody) {
        HashMap<String, String> mapCredential = new HashMap<>();

        try {
            mapCredential = objectMapper.readValue(loginBody, HashMap.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String email = mapCredential.get("email");

        try {
            String idtoken = mapCredential.get("token");
            User user = firebaseAdmin.getUser(idtoken);

            if(user.getEmail().equals(email)) {
                if(authRepository.getUserId(user.getEmail()) == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new Exception();
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity signup(String signUpBody) {
        HashMap<String, String> mapCredential = new HashMap<>();

        try {
            mapCredential = objectMapper.readValue(signUpBody, HashMap.class);
            if(!mapCredential.containsKey("username") || !mapCredential.containsKey("email") || !mapCredential.containsKey("token")) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            String idToken = mapCredential.get("token");
            User user = firebaseAdmin.getUser(idToken);
            user.setUsername(mapCredential.get("username"));

            authRepository.signUpUser(user);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
