package org.lessons.java.manga.manga.repository;

import org.lessons.java.manga.manga.model.Genere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenereRepository extends JpaRepository<Genere, Integer> {
    
}
