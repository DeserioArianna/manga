package org.lessons.java.manga.manga.repository;


import java.util.Optional;

import org.lessons.java.manga.manga.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByName(String name);
}