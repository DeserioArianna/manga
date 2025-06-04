package org.lessons.java.manga.manga.repository;

import org.lessons.java.manga.manga.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaRepository extends JpaRepository<Manga, Integer> {
    
}
