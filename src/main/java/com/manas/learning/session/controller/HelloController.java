package com.manas.learning.session.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class HelloController {

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/hello")
    public int helloMessage(){
        String count =  httpSession.getAttribute("count") == null ? "0" : (String)httpSession.getAttribute("count");
        Integer countInt = Integer.parseInt(count);
        httpSession.setAttribute("count", String.valueOf(countInt+1));
        return countInt;
    }
}
