package org.lessons.java.manga.manga.repository;

import org.lessons.java.manga.manga.model.Autore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoreRepository extends JpaRepository<Autore, Integer> {
    
}
