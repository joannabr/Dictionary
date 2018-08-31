package pl.joannabrania.Dictionary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.joannabrania.Dictionary.models.services.ForumService;
import pl.joannabrania.Dictionary.models.services.SessionService;

@Controller
public class ForumController {

    final ForumService forumService;
    final SessionService sessionService;

    public ForumController(ForumService forumService, SessionService sessionService) {
        this.forumService = forumService;
        this.sessionService = sessionService;
    }

    @GetMapping("/forum")
    public String forum(Model model){
        model.addAttribute("listComment",(forumService.getAllComents()));
        model.addAttribute("userObject",sessionService);

        return "forum";
    }

    @PostMapping("/addComment")
    public  String forum(@RequestParam("comment") String comment,
                         Model model){
        forumService.addComment(comment);

        return "redirect:/forum";
    }

    @GetMapping("/deleteComment/{commentId}")
    public String deleteComment(@PathVariable("commentId") int commentId){
        forumService.deleteComment(commentId);
        return "redirect:/forum";
    }

    @GetMapping("/addOpinion/{commentId}")
    public String addOpinion(@PathVariable("commentId") String commentId){
        int commentId1 = Integer.valueOf(commentId);
        forumService.addOpinion(commentId1);

        return "redirect:/forum";
    }
}
