package pl.joannabrania.Dictionary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.joannabrania.Dictionary.models.services.AuthService;
import pl.joannabrania.Dictionary.models.services.CategoryService;
import pl.joannabrania.Dictionary.models.services.DictionaryService;
import pl.joannabrania.Dictionary.models.services.SessionService;


@Controller
public class MainController {

    final SessionService sessionService;
    final DictionaryService dictionaryService;
    final AuthService authService;
    final CategoryService categoryService;

    @Autowired
    public MainController(SessionService sessionService, DictionaryService dictionaryService, AuthService authService, CategoryService categoryService) {
        this.sessionService = sessionService;
        this.dictionaryService = dictionaryService;
        this.authService = authService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userObject", sessionService);
        if(sessionService.isLogin()) {
            model.addAttribute("userPoints", authService.getPoints());
        }
        model.addAttribute("groupAllWord",categoryService.getNameAllgroup());
        model.addAttribute("winner",authService.findWinner());

        return "index";
    }
}
