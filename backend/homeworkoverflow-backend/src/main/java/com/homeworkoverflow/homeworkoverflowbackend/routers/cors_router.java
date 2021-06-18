package com.homeworkoverflow.homeworkoverflowbackend.routers;

import com.google.rpc.context.AttributeContext.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class cors_router {

    @RequestMapping(value="", method = RequestMethod.OPTIONS)
    public ResponseEntity preflightCorsRequest() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
