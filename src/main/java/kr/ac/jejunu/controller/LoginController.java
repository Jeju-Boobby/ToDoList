package kr.ac.jejunu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Boobby on 17-6-9.
 */
@Controller
public class LoginController {
    @Autowired

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
