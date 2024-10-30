package se.distansakademin.halloween_site.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.distansakademin.halloween_site.models.Monster;
import se.distansakademin.halloween_site.repositories.MonsterRepository;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final MonsterRepository repository;

    @GetMapping
    public String getHome(Model model){
        var monsters = repository.findAll();
        model.addAttribute("monsters", monsters);
        return "home";
    }



    @PostMapping
    public String postHome(String monsterName){
        var monster = new Monster(monsterName);
        repository.save(monster);
        return "redirect:/";
    }
}
