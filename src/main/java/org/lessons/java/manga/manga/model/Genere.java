package org.lessons.java.manga.manga.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Genere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome del genere è obbligatorio")
    private String nomeGenere;

    @ManyToMany(mappedBy = "generi")
    @JsonBackReference
    private List<Manga> mangaAssociati;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeGenere() {
        return this.nomeGenere;
    }

    public void setNomeGenere(String nomeGenere) {
        this.nomeGenere = nomeGenere;
    }


    public List<Manga> getMangaAssociati() {
        return this.mangaAssociati;
    }

    public void setMangaAssociati(List<Manga> mangaAssociati) {
        this.mangaAssociati = mangaAssociati;
    }


}
