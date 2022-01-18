package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("users", userService.index());
        return "index";
    }

    @GetMapping("/new")
    public String newUser(ModelMap model) {
        model.addAttribute("users", new User());
        return "posting";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, ModelMap model) {
        model.addAttribute(userService.getUser(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }


}