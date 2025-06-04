package org.lessons.java.manga.manga.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.manga.manga.model.Manga;
import org.lessons.java.manga.manga.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MangaService {

    @Autowired
    private MangaRepository mangaRepository;

    public Manga findById(Integer id) {
        Optional<Manga> mangaOptional = mangaRepository.findById(id);
        return mangaOptional.orElse(null);
    }

    public List<Manga> findAll(String titolo) {
        if (titolo == null || titolo.isBlank()) {
            return mangaRepository.findAll();
        }
        return mangaRepository.findAll();
    }

    public Manga create(Manga manga) {
        return mangaRepository.save(manga);
    }

    public Manga update(Integer id, Manga updateManga) {
        Manga mangaEsistente = findById(id);

        mangaEsistente.setTitolo(updateManga.getTitolo());
        mangaEsistente.setDescrizione(updateManga.getDescrizione());
        mangaEsistente.setAnnoPubblicazione(updateManga.getAnnoPubblicazione());
        mangaEsistente.setCopertinaUrl(updateManga.getCopertinaUrl());

        return mangaRepository.save(mangaEsistente);
    }

    public void delete(Integer id) {
        Manga manga = mangaRepository.findById(id).orElse(null);

        mangaRepository.delete(manga);
    }
}
