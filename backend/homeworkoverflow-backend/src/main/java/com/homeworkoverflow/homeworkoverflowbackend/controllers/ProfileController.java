package com.homeworkoverflow.homeworkoverflowbackend.controllers;

import com.homeworkoverflow.homeworkoverflowbackend.models.User;
import com.homeworkoverflow.homeworkoverflowbackend.repositories.ProfileRepostiory;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfileController {
    private HttpHeaders responseHeaders;
    private ProfileRepostiory profileRepostiory;

    public ProfileController(ProfileRepostiory profileRepository) {
        this.responseHeaders = new HttpHeaders();
        this.responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        this.profileRepostiory = profileRepository;
    }

    public ResponseEntity<String> retrieveProfile(Long userid) {
        try {
            User user = profileRepostiory.public_findUserById(userid);
            return new ResponseEntity<String>(user.toString(), this.responseHeaders, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
