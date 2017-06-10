package kr.ac.jejunu.controller;

import kr.ac.jejunu.model.User;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Boobby on 17-6-9.
 */
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "/registerProcessing", method = RequestMethod.POST)
    public String registerProcessing(@ModelAttribute("user") User user) {
        try {
            userService.saveUser(user);
        } catch (DuplicateKeyException e) {
            return "redirect:/register?error";
        }

        return "redirect:/login?registerSuccessful";
    }
}
