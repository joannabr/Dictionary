package pl.joannabrania.Dictionary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.joannabrania.Dictionary.models.DicitionaryEntity;
import pl.joannabrania.Dictionary.models.services.DictionaryService;
import pl.joannabrania.Dictionary.models.services.SessionService;
import pl.joannabrania.Dictionary.models.services.UserDictionaryService;

import java.util.List;

@Controller
public class DictionaryController {

    final DictionaryService dictionaryService;
    final SessionService sessionService;
    final UserDictionaryService dictionaryUserService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService, SessionService sessionService, UserDictionaryService dictionaryUserService) {
        this.dictionaryService = dictionaryService;
        this.sessionService = sessionService;
        this.dictionaryUserService = dictionaryUserService;
    }

    @GetMapping("/dictionary/{nameGroup}")
    public String groupWord(@PathVariable("nameGroup") int nameGroup,
                            Model model){

        List<DicitionaryEntity> listOfWords;
        int numberGroup = Integer.valueOf(nameGroup);
        model.addAttribute("nameGroup", nameGroup);
        model.addAttribute("userObject",sessionService);

        if (sessionService.isLogin()) {
            listOfWords = dictionaryService.getUserWords(numberGroup);
        }else{
            listOfWords = dictionaryService.getWords(numberGroup);
        }
        model.addAttribute("listWords", listOfWords);


        return "listWords";
    }

    @GetMapping("/addWordToUser/{wordId}/{numberGroup}")
    public String addWordToUser(@PathVariable("wordId") int wordId,
                                @PathVariable("numberGroup") String nameGroup,
                                Model model){

        dictionaryUserService.addWordToUser(wordId,nameGroup);
        return "redirect:/dictionary/"+nameGroup;
    }

}
