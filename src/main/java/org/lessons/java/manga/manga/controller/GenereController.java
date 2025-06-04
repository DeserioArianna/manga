package org.lessons.java.manga.manga.controller;

import org.lessons.java.manga.manga.model.Genere;
import org.lessons.java.manga.manga.repository.GenereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/genere")
public class GenereController {
    
    @Autowired
    private GenereRepository genereRepository;


    // Lista dei generi
    @GetMapping("")
    public String getGenere(Model model) {
       
        model.addAttribute("generi", genereRepository.findAll());
       
        return "genere/index";
    }

    // Form per inserire il genere
    @GetMapping("/create")
    public String createGenere(Model model) {
        
        model.addAttribute("genere", new Genere());

        return "genere/create-or-edit";
    }
    
    // Salvataggio del form
    @PostMapping("/create")
    public String storeGenere(@Valid @ModelAttribute Genere genere, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            return "genere/create-or-edit";
        }

        genereRepository.save(genere);
        return "redirect:/genere";
    }

    // Per modificare un o più parametri
    @GetMapping("/edit/{id}")
    public String editGenere(@PathVariable Integer id, Model model){

        model.addAttribute("genere", genereRepository.findById(id).orElse(null));

        return "genere/create-or-edit";
    }

    // Salvataggio della o delle modifiche 
    @PostMapping("/edit/{id}")
    public String updateGenere(@Valid @ModelAttribute Genere genere, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            return "genere/create-or-edit";
        }
        
        genereRepository.save(genere);
        return "redirect:/genere";
    }

    // Cancellare un genere
    @PostMapping("/delete/{id}")
    public String deleteGenere(@PathVariable Integer id) {
        
        genereRepository.deleteById(id);
        
        return "redirect:/genere";
    }
    
    
}
