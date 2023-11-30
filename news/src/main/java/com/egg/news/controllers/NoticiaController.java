package com.egg.news.controllers;

import com.egg.news.services.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
public class NoticiaController {
    @Autowired
    NoticiaService noticiaService;

    @GetMapping("/register")
    public String register(){
        return "";
    }


}
