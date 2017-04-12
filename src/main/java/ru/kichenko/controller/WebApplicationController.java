package ru.kichenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.kichenko.model.UpdateUserDataDto;
import ru.kichenko.model.User;
import ru.kichenko.service.UserService;
import ru.kichenko.validator.UserValidator;

@Controller
public class WebApplicationController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
        if (error != null)
            model.addAttribute("error", "Указан неверный логин или пароль");

        if (logout != null)
            model.addAttribute("logout", "Вы успешно вышли");

        return "login";
    }

    @RequestMapping(value = { "/", "/profile/view" }, method = RequestMethod.GET)
    public String index(Model model, @AuthenticationPrincipal(expression = "user") User user) {
        model.addAttribute("user", user);
        return "profile-view";
    }

    @RequestMapping("/profile/edit")
    public String edit(Model model, @AuthenticationPrincipal(expression = "user") User user) {
        model.addAttribute("user", user);
        return "profile-edit";
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("user") UpdateUserDataDto data, @AuthenticationPrincipal(expression = "user") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(data, bindingResult);
        if (bindingResult.hasErrors()) {
            return "profile-edit";
        }
        userService.edit(user, data);
        return "redirect:/profile/view";
    }
    
    @RequestMapping("/acl")
    public String acl(@AuthenticationPrincipal(expression = "user") User user) {
        userService.addAclPermission2User(user.getId(), user.getUserName(), BasePermission.WRITE);
        return "redirect:/profile/edit";
    }
    
    @ResponseBody
    @RequestMapping("/runas") 
    @Secured({"ROLE_ADMIN", "RUN_AS_SUPER_ADMIN"})
    public String tryRunAs(@AuthenticationPrincipal(expression = "user") User user) {
        userService.findById(user.getId());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Роли у текущего пользователя: " + auth.getAuthorities().toString();
    }
}
