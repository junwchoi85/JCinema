package com.jcinema.securityserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/")
    public  String getAccountDetails () {
        return "/";
    }

    @GetMapping("/myAccount")
    public  String getMyAccount() {
        return "Here are the account details from the DB!";
    }
}