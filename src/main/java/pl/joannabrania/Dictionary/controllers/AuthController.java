package pl.joannabrania.Dictionary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.joannabrania.Dictionary.models.forms.RegisterForm;
import pl.joannabrania.Dictionary.models.services.AuthService;
import pl.joannabrania.Dictionary.models.services.SessionService;

@Controller
public class AuthController {

    final SessionService sessionService;
    final AuthService authService;

    @Autowired
    public AuthController(SessionService sessionService, AuthService authService) {
        this.sessionService = sessionService;
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userObject",sessionService);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        if(!authService.tryLogin(username, password)){
            model.addAttribute("infoAboutLogin", "Nieprawidłowy login lub hasło");
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute("registerForm") RegisterForm registerForm,
                           Model model){
        if(!authService.tryToRegister(registerForm)){
            model.addAttribute("infoAboutRegister", "Podany username jest już zajęty");
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(){
        sessionService.setLogin(false);
        sessionService.setUserEntity(null);

        return "redirect:/";
    }

    @GetMapping("/removeAccount")
    public String reomoveAccount(){
        authService.removeAccount();
        sessionService.setLogin(false);
        sessionService.setUserEntity(null);
        return "redirect:/";
    }
}
