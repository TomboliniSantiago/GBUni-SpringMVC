package com.egg.news.controllers;

import com.egg.news.entities.Noticia;
import com.egg.news.exceptions.MyException;
import com.egg.news.services.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NoticiaController {
    @Autowired
    NoticiaService noticiaService;

    @GetMapping("/register")
    public String register(){
        return "news_form";
    }
    @GetMapping("/list")
    public String listNews(ModelMap model){
        List<Noticia> noticias = noticiaService.listNews();
        model.addAttribute("noticias", noticias);
        return "news_list";

    }

    @PostMapping("/create")
    public String createNews(@RequestParam String title, @RequestParam String body){
        try {
            noticiaService.createNews(title,body);
        } catch (MyException e) {
            throw new RuntimeException(e);
        }
        return "index";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable String id, ModelMap modelo){
        modelo.put("noticia", noticiaService.getOne(id));
        return  "news_modify";
    }
    @PostMapping("/modifyNews/{id}")
    public String modifyNews(@PathVariable String id, String title,String body, ModelMap modelo){
        try{
            noticiaService.modificateNews(title,body, id);
            return "redirect:/news/list";
        }catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "news_modify";
        }
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, ModelMap modelo){
        modelo.put("noticia", noticiaService.getOne(id));
        return  "news_delete";
    }
    @PostMapping ("/deleteNews/{id}")
    public String deleteNews(@PathVariable String id, ModelMap modelo){

        try{
            noticiaService.deleteNews(id);
            return "redirect:/news/list";
        }catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "redirect:/news/list";
        }
    }
}
