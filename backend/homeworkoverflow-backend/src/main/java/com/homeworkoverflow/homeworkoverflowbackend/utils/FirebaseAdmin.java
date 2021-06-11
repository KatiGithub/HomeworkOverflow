package com.homeworkoverflow.homeworkoverflowbackend.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.homeworkoverflow.homeworkoverflowbackend.models.User;

import org.springframework.stereotype.Component;

@Component
public class FirebaseAdmin {

    public FirebaseAdmin() {
        try {

            URL resource = getClass().getClassLoader().getResource("homeworkoverflow-e307a-firebase-adminsdk-2fnye-38e3540368.json");

            File serviceAccountKeyFile = new File(resource.toURI());

            FileInputStream serviceAccount = new FileInputStream(serviceAccountKeyFile);
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
            
            FirebaseApp.initializeApp(options);
            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String idToken) {
        try {
            FirebaseToken firebaseToken = null;
            firebaseToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            User user = new User();

            user.setEmail(firebaseToken.getEmail());
            user.setFirebaseuid(firebaseToken.getUid());
            
            String fullname = firebaseToken.getName();
            String firstname = fullname.substring(0, fullname.indexOf(" "));
            String lastname = fullname.substring(fullname.indexOf(" ") + 1, fullname.length());
    
            user.setFirstname(firstname);
            user.setLastname(lastname);
    
            return user;
        } catch (FirebaseAuthException ex) {
            ex.printStackTrace();
            try {
                throw ex;
            } catch (FirebaseAuthException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }   

            return null;
        }
    }
}
