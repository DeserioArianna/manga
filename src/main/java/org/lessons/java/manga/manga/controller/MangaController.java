package org.lessons.java.manga.manga.controller;

import org.lessons.java.manga.manga.model.Manga;
import org.lessons.java.manga.manga.repository.AutoreRepository;
import org.lessons.java.manga.manga.repository.GenereRepository;
import org.lessons.java.manga.manga.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/manga")
public class MangaController {
    
    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private GenereRepository genereRepository;

    // Lista Manga
    @GetMapping("")
    public String getListaManga(Model model) {
        
        model.addAttribute("mangaLista", mangaRepository.findAll());
        
        return "manga/index";
    }

    // Un solo manga
    @GetMapping("/{id}")
    public String getManga(@PathVariable Integer id, Model model) {
        
        model.addAttribute("manga", mangaRepository.findById(id).orElse(null));
        
        return "manga/show";
    }

    // Form Inserimento Manga
    @GetMapping("/create")
    public String createManga(Model model) {
        
        model.addAttribute("manga", new Manga());
        model.addAttribute("autore", autoreRepository.findAll());
        model.addAttribute("generi", genereRepository.findAll());
        
        return "manga/create-or-edit"; 
    }

    // Salvataggio del Form Manga
    @PostMapping("/create")
    public String storeManga(@Valid @ModelAttribute("manga") Manga manga, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("autore", autoreRepository.findAll());
            model.addAttribute("generi", genereRepository.findAll());
            return "manga/create-or-edit";
        }

        mangaRepository.save(manga);
        return "redirect:/manga";
    }

    // Modificare un solo parametro o più parametri
    @GetMapping("/edit/{id}")
    public String editManga(@PathVariable Integer id, Model model) {
        
        model.addAttribute("manga", mangaRepository.findById(id).get());
        model.addAttribute("autore", autoreRepository.findAll());
        model.addAttribute("generi", genereRepository.findAll());

        return "manga/create-or-edit";
    }

    // Salvare modifiche 
    @PostMapping("/edit/{id}")
    public String updateManga(@Valid @ModelAttribute Manga manga, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("autore", autoreRepository.findAll());
            model.addAttribute("generi", genereRepository.findAll());
            return "manga/create-or-edit";
        }

        mangaRepository.save(manga);
        return "redirect:/manga/" + manga.getId();
    }

    // Cancellare Manga
    @PostMapping("/delete/{id}")
    public String deleteManga(@PathVariable Integer id) {
        
        mangaRepository.deleteById(id);

        return "redirect:/manga";
    }
    

    
}
