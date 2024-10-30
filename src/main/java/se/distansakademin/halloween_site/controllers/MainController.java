package se.distansakademin.halloween_site.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.distansakademin.halloween_site.models.Monster;
import se.distansakademin.halloween_site.models.User;
import se.distansakademin.halloween_site.repositories.MonsterRepository;
import se.distansakademin.halloween_site.repositories.UserRepository;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final MonsterRepository monsterRepository;
    private final UserRepository userRepository;

    @GetMapping
    public String getHome(Model model) {
        var monsters = monsterRepository.findAll();
        model.addAttribute("monsters", monsters);
        return "home";
    }


    @PostMapping
    public String postHome(String monsterName) {
        var monster = new Monster(monsterName);
        monsterRepository.save(monster);
        return "redirect:/";
    }


    @PostMapping("/register")
    public String postRegister(String username, String password, String confirmPassword) {
        var optionalUser = userRepository.findUserByUsername(username);

        if (!password.equals(confirmPassword) || optionalUser.isPresent()){
            return "error";
        }

        var passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        var pass = passwordEncoder.encode(password);

        var user = new User();
        user.setUsername(username);
        user.setPassword(pass);

        userRepository.save(user);

        return "redirect:/";
    }


    @PostMapping("/custom-login")
    public String postLogin(String username, String password){
        var optionalUser = userRepository.findUserByUsername(username);

        if(optionalUser.isEmpty()){
            return "error";
        }

        var user = optionalUser.get();

        var passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        var passwordsMatch = passwordEncoder.matches(password, user.getPassword());

        System.out.println(user.getUsername());
        System.out.println(passwordsMatch);

        return "redirect:/";
    }
}
