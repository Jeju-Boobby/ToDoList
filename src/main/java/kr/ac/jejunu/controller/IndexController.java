package kr.ac.jejunu.controller;

import kr.ac.jejunu.model.ToDo;
import kr.ac.jejunu.service.ToDoService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Boobby on 17-6-9.
 */
@Controller
public class IndexController {
    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index")
    public String index(@AuthenticationPrincipal User user, ModelMap modelMap) {
        if (user != null) {
            List<ToDo> toDoList = toDoService.getUsersToDoList(userService.findUser(user.getUsername()));
            modelMap.addAttribute("toDoList", toDoList);
        }

        return "/index";
    }
}
