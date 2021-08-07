package com.im_first.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/im")
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "hello world";
    }

}
