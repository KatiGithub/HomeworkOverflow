package com.homeworkoverflow.homeworkoverflowbackend;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@RestController
public class HomeworkoverflowController {
    
    @RequestMapping(value="/test/", method=RequestMethod.GET)
    public String getTest() {
        return "test request";
    }
    
    
}
