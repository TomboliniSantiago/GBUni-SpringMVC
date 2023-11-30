package com.egg.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.egg.news.entities.Noticia;
@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, String> {
    @Query("SELECT n FROM Noticia n WHERE n.title = :titulo")
    public Noticia buscarPortitulo(@Param("title") String title);
}
