package org.lessons.java.manga.manga.controller;

import java.util.List;

import org.lessons.java.manga.manga.model.Manga;
import org.lessons.java.manga.manga.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/manga")
public class MangaRestAdvanceController {

    @Autowired
    private MangaService mangaService;

    @GetMapping("")
    public List<Manga> index(String titolo) {
        return mangaService.findAll(titolo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manga> show(@PathVariable Integer id) {
        Manga manga = mangaService.findById(id);

        if (manga == null) {
            return new ResponseEntity<Manga>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Manga>(manga, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Manga> store(@Valid @RequestBody Manga manga) {
        Manga mangaNuovo = mangaService.create(manga);
        return new ResponseEntity<Manga>(mangaNuovo, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Manga> patch(@Valid @RequestBody Manga manga, @PathVariable Integer id) {
        Manga mangaModificato = mangaService.update(id, manga);

        if (mangaModificato == null) {
            return new ResponseEntity<Manga>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Manga>(mangaModificato, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        Manga manga = mangaService.findById(id);

        if (manga == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manga non trovato");
        }

        mangaService.delete(id);
        return ResponseEntity.ok("Manga eliminato con successo");
    }

    

}
