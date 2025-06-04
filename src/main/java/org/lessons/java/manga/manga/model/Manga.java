package org.lessons.java.manga.manga.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Devi inserire un titolo")
    private String titolo;

    private String descrizione;

    @NotNull(message = "Devi inserire l'anno di pubblicazione")
    private LocalDate annoPubblicazione;

    @NotBlank(message = "Devi inserire un'immagine di copertina")
    private String copertinaUrl;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    @JsonManagedReference
    private Autore autore;

    @ManyToMany
    @JoinTable(
        name = "manga_genere", // nome tabella ponte
        joinColumns = @JoinColumn(name = "manga_id"), // chiave esterna di questa entità
        inverseJoinColumns = @JoinColumn(name = "genere_id") // chiave esterna dell'atra entità
    )
    @JsonManagedReference
    private List<Genere> generi;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return this.titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getAnnoPubblicazione() {
        return this.annoPubblicazione;
    }

    public void setAnnoPubblicazione(LocalDate annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public String getCopertinaUrl() {
        return this.copertinaUrl;
    }

    public void setCopertinaUrl(String copertinaUrl) {
        this.copertinaUrl = copertinaUrl;
    }


    public Autore getAutore() {
        return this.autore;
    }

    public void setAutore(Autore autore) {
        this.autore = autore;
    }

    public List<Genere> getGeneri() {
        return this.generi;
    }

    public void setGeneri(List<Genere> generi) {
        this.generi = generi;
    }

}
