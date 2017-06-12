package kr.ac.jejunu.controller;

import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.nio.file.AccessDeniedException;

/**
 * Created by Boobby on 17-6-12.
 */
@Controller
public class ProfileController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/editprofile")
    public String editProfile(@AuthenticationPrincipal User user, ModelMap modelMap) {
        kr.ac.jejunu.model.User profile = userService.findUser(user.getUsername());
        kr.ac.jejunu.model.User updateUser = new kr.ac.jejunu.model.User();
        modelMap.addAttribute("profile", profile);
        modelMap.addAttribute("user", updateUser);

        return "editprofile";
    }

    @RequestMapping(value = "/editProfileProcessing", method = RequestMethod.POST)
    public String editProfileProcessing(@ModelAttribute kr.ac.jejunu.model.User user) {
        try {
            userService.update(user);
        } catch (AccessDeniedException e) {
            return "redirect:/editprofile?incorrectPassword";
        }

        return "redirect:/index";
    }
}
