package com.homeworkoverflow.homeworkoverflowbackend.routers;

import com.homeworkoverflow.homeworkoverflowbackend.controllers.ProfileController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class profile_router {
    
    @Autowired
    private ProfileController profileController;

    @RequestMapping(value="/user/{id}", method=RequestMethod.GET)
    public ResponseEntity<String> retrievePublicProfile(@PathVariable("id") Long userid) {
        return this.profileController.retrieveProfile(userid);
    }
    
}
