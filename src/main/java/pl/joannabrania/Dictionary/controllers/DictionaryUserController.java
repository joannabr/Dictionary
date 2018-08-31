package pl.joannabrania.Dictionary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.joannabrania.Dictionary.models.services.SessionService;
import pl.joannabrania.Dictionary.models.services.UserDictionaryService;

@Controller
public class DictionaryUserController {

    final SessionService sessionService;
    final UserDictionaryService userDictionaryService;

    @Autowired
    public DictionaryUserController(SessionService sessionService, UserDictionaryService userDictionaryService) {
        this.sessionService = sessionService;
        this.userDictionaryService = userDictionaryService;
    }

    @GetMapping("/learning")
    public String learning(Model model){
        model.addAttribute("userObject",sessionService);
        if(sessionService.isLogin()) {
            model.addAttribute("userWord", userDictionaryService.getUserWord());
        }
        model.addAttribute("infoAboutNotLogin", "Nie jesteś zalogowany");

        return "learning";
    }

}
