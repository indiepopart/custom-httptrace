package com.okta.developer.demo;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @GetMapping(value = "/greeting")
    @ResponseBody
    public String getGreeting(Principal user) {
        return "Buenos días " + user.getName();
    }
}
