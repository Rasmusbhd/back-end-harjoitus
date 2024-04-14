package hh.sof3.musiclibrary.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    //Kotisivu
    @GetMapping("/index")
    public String homePage(Model model) {
        return "index";
    }
}
