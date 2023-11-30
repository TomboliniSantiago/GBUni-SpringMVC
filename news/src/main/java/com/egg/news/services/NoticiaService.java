package com.egg.news.services;

import com.egg.news.exceptions.*;
import com.egg.news.entities.Noticia;
import com.egg.news.repositories.NoticiaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class NoticiaService {
    @Autowired
    NoticiaRepository noticiaRepository;
    @Transactional
    public void createNews(String title, String body) throws MyException {
        validation(title,body);
        Noticia noticia = new Noticia();
        noticia.setTitle(title);
        noticia.setBody(body);
        noticiaRepository.save(noticia);
    }
    private void validation(String title, String body) throws MyException {
        if(title.isEmpty()){
            throw new MyException("El titulo no puede ser nulo");
        }
        if(body.isEmpty()){
            throw new MyException("El cuerpo de la noticia no puede ser nulo");
        }
    }
    @Transactional
    public void modificateNews(String title,String body, String id) throws MyException {
        validation(title, body);

        Optional<Noticia> respuesta = noticiaRepository.findById(id);
        if (respuesta.isPresent()) {
            Noticia noticia  = new Noticia();
            noticia.setTitle(title);
            noticia.setBody(body);
            noticiaRepository.save(noticia);
        }
    }
    public List<Noticia> listNews(){
        List<Noticia> noticias= new ArrayList<>();
        noticias = noticiaRepository.findAll();
        return noticias;
    }

    public void eliminateNews(String id)throws MyException {


    }

    public Noticia getOne(String id) {
        return noticiaRepository.getReferenceById(id);
    }
}