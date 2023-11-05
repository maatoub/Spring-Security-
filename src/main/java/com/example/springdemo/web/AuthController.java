package com.example.springdemo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springdemo.entities.AppUser;
import com.example.springdemo.repository.RepoUser;
import com.example.springdemo.service.AppService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private AppService appService;

    @GetMapping("/login")
    public String pageLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String pageRegister(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping("/registration")
    public String handleLogin(@Valid @ModelAttribute("user") AppUser user,
            BindingResult bindingResult, Model model, @RequestParam(name = "confirm-password") String confPassword) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            AppUser newUser = appService.addUser(user.getUsername(),
                    user.getPassword(),
                    confPassword);
            appService.addRoleToUser(newUser.getUsername(), "USER");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
        return "redirect:/login";
    }
}
