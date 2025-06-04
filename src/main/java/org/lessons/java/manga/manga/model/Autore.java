package org.lessons.java.manga.manga.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Autore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Devi inserire almeno un autore")
    private String nomeAutore;

    private String nazionalità;

    @OneToMany(mappedBy = "autore")
    @JsonBackReference
    private List<Manga> mangaScritti;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeAutore() {
        return this.nomeAutore;
    }

    public void setNomeAutore(String nomeAutore) {
        this.nomeAutore = nomeAutore;
    }

    public String getNazionalità() {
        return this.nazionalità;
    }

    public void setNazionalità(String nazionalità) {
        this.nazionalità = nazionalità;
    }


    public List<Manga> getMangaScritti() {
        return this.mangaScritti;
    }

    public void setMangaScritti(List<Manga> mangaScritti) {
        this.mangaScritti = mangaScritti;
    }

}
