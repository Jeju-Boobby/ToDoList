package kr.ac.jejunu.controller;

import kr.ac.jejunu.model.ToDo;
import kr.ac.jejunu.service.ToDoService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Boobby on 17-6-9.
 */
@Controller
@RequestMapping(value = "/todo")
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        ToDo toDo = new ToDo();
        modelMap.addAttribute("todo", toDo);
        return "addtodo";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProcessing(@AuthenticationPrincipal User user, @ModelAttribute("todo") ToDo toDo) {
        toDo.setUser(userService.findUser(user.getUsername()));

        toDoService.saveToDo(toDo);

        return "redirect:/index";
    }

    @RequestMapping(value = "/{toDoNo}/remove")
    public String deleteToDo(@AuthenticationPrincipal User user, @PathVariable Long toDoNo) {
        ToDo toDo = toDoService.getToDo(toDoNo);

        // 작성자의 요청인지 확인
        if (user.getUsername().equals(toDo.getUser().getId())) {
            toDoService.deleteToDo(toDoNo);
        } else {
            return "redirect:/index?invalidAccess";
        }

        return "redirect:/index";
    }

    @RequestMapping(value = "/{toDoNo}/detail")
    public String toDoDetail(@AuthenticationPrincipal User user, @PathVariable Long toDoNo, ModelMap modelMap) {
        ToDo toDo = toDoService.getToDo(toDoNo);

        // 작성자의 요청인지 확인
        if (user.getUsername().equals(toDo.getUser().getId())) {
            modelMap.addAttribute("todo", toDo);
        } else {
            return "redirect:/index?invalidAccess";
        }

        return "tododetail";
    }

    @RequestMapping(value = "/{toDoNo}/done")
    public String doneToDo(@AuthenticationPrincipal User user, @PathVariable Long toDoNo) {
        ToDo toDo = toDoService.getToDo(toDoNo);

        // 작성자의 요청인지 확인
        if (user.getUsername().equals(toDo.getUser().getId())) {
            toDoService.updateStatusToDone(toDoNo);
        } else {
            return "redirect:/index?invalidAccess";
        }

        return "redirect:/index";
    }

    @RequestMapping(value = "/{toDoNo}/edit")
    public String editToDo(@AuthenticationPrincipal User user, @PathVariable Long toDoNo, ModelMap modelMap) {
        ToDo oldToDo = toDoService.getToDo(toDoNo);

        // 작성자의 요청인지 확인
        if (user.getUsername().equals(oldToDo.getUser().getId())) {
            modelMap.addAttribute("oldtodo", oldToDo);
            modelMap.addAttribute("todo", new ToDo());
        } else {
            return "redirect:/index?invalidAccess";
        }

        return "edittodo";
    }

    @RequestMapping(value = "/{toDoNo}/fail")
    public String failToDo(@AuthenticationPrincipal User user, @PathVariable Long toDoNo) {
        ToDo toDo = toDoService.getToDo(toDoNo);

        // 작성자의 요청인지 확인
        if (user.getUsername().equals(toDo.getUser().getId())) {
            toDoService.updateStatusToFail(toDoNo);
        } else {
            return "redirect:/index?invalidAccess";
        }

        return "redirect:/index";
    }

    @RequestMapping(value = "/{toDoNo}/editProcessing", method = RequestMethod.POST)
    public String editProcessing(@AuthenticationPrincipal User user, @PathVariable Long toDoNo, @ModelAttribute ToDo toDo) {
        ToDo oldToDo = toDoService.getToDo(toDoNo);

        // 작성자의 요청인지 확인
        if (user.getUsername().equals(oldToDo.getUser().getId())) {
            toDo.setNo(toDoNo);
            toDo.setUser(userService.findUser(user.getUsername()));
            toDoService.updateToDo(toDo);
        } else {
            return "redirect:/index?invalidAccess";
        }
        return "redirect:/todo/" + toDoNo + "/detail";
    }
}
