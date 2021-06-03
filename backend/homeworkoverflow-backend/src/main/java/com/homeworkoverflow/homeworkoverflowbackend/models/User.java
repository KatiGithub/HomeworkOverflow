package com.homeworkoverflow.homeworkoverflowbackend.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class User {
    Long userid;
    String firstname;
    String lastname;
    String email;
    String firebaseuid;
    String githubhandle;
    String twitterhandle;
    String facebookhandle;
    String userlocation;


    public User(Long userid, String firstname, String lastname, String email, String firebaseuid, String githubhandle, String twitterhandle, String facebookhandle, String userlocation) {
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.firebaseuid = firebaseuid;
        this.githubhandle = githubhandle;
        this.twitterhandle = twitterhandle;
        this.facebookhandle = facebookhandle;
        this.userlocation = userlocation;
    }


    public User() {}


    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebaseuid() {
        return this.firebaseuid;
    }

    public void setFirebaseuid(String firebaseuid) {
        this.firebaseuid = firebaseuid;
    }

    public String getGithubhandle() {
        return this.githubhandle;
    }

    public void setGithubhandle(String githubhandle) {
        this.githubhandle = githubhandle;
    }

    public String getTwitterhandle() {
        return this.twitterhandle;
    }

    public void setTwitterhandle(String twitterhandle) {
        this.twitterhandle = twitterhandle;
    }

    public String getFacebookhandle() {
        return this.facebookhandle;
    }

    public void setFacebookhandle(String facebookhandle) {
        this.facebookhandle = facebookhandle;
    }

    public String getUserlocation() {
        return this.userlocation;
    }

    public void setUserlocation(String userlocation) {
        this.userlocation = userlocation;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
