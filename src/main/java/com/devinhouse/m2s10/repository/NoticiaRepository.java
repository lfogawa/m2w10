package com.devinhouse.m2s10.repository;

import com.devinhouse.m2s10.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
}