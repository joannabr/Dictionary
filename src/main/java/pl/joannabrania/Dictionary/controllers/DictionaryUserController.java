package pl.joannabrania.Dictionary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.joannabrania.Dictionary.models.services.DictionaryService;
import pl.joannabrania.Dictionary.models.services.SessionService;
import pl.joannabrania.Dictionary.models.services.UserDictionaryService;

@Controller
public class DictionaryUserController {

    final SessionService sessionService;
    final UserDictionaryService userDictionaryService;
    final DictionaryService dictionaryService;


    @Autowired
    public DictionaryUserController(SessionService sessionService, UserDictionaryService userDictionaryService, DictionaryService dictionaryService) {
        this.sessionService = sessionService;
        this.userDictionaryService = userDictionaryService;
        this.dictionaryService = dictionaryService;
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

    @GetMapping("/delete/{wordId}")
    public String deleteWordFromUserDictionary( @PathVariable("wordId") int wordId){
        userDictionaryService.deleteWord(wordId);
        return "redirect:/learning";
    }

    @PostMapping("/checkWord/{wordId}")

    public String checkWord(@RequestParam("checkWord") String checkWord,
                            @PathVariable("wordId") int wordId,
                            Model model){
        userDictionaryService.checkUserWord(checkWord,wordId);

        return "redirect:/learning";

    }

}
