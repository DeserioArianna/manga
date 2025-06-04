package org.lessons.java.manga.manga.controller;

import org.lessons.java.manga.manga.model.Autore;
import org.lessons.java.manga.manga.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/autore")
public class AutoreController {
    
    @Autowired
    private AutoreRepository autoreRepository;

    // Lista degli autori
    @GetMapping("")
    public String getautore(Model model) {
       
        model.addAttribute("autori", autoreRepository.findAll());
       
        return "autore/index";
    }

    // Form per inserire l' autore
    @GetMapping("/create")
    public String createautore(Model model) {
        
        model.addAttribute("autore", new Autore());

        return "autore/create-or-edit";
    }
    
    // Salvataggio del form
    @PostMapping("/create")
    public String storeautore(@Valid @ModelAttribute Autore autore, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            return "autore/create-or-edit";
        }

        autoreRepository.save(autore);
        return "redirect:/autore";
    }

    // Per modificare un o più parametri
    @GetMapping("/edit/{id}")
    public String editAutore(@PathVariable Integer id, Model model){

        model.addAttribute("autore", autoreRepository.findById(id).orElse(null));

        return "autore/create-or-edit";
    }

    // Salvataggio della o delle modifiche 
    @PostMapping("/edit/{id}")
    public String updateAutore(@Valid @ModelAttribute Autore autore, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            return "autore/create-or-edit";
        }
        
        autoreRepository.save(autore);
        return "redirect:/autore";
    }
    

    // Cancellare un autore
    @PostMapping("/delete/{id}")
    public String deleteautore(@PathVariable Integer id) {
        
        autoreRepository.deleteById(id);
        
        return "redirect:/autore";
    }
    
}
