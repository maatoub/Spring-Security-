package com.example.springdemo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springdemo.entities.Accessory;
import com.example.springdemo.repository.RepoAccessory;
import com.example.springdemo.repository.RepoCategory;

@Controller
public class ControllerApp {

    @Autowired
    RepoAccessory repoAccessory;

    @Autowired
    RepoCategory repoCategory;

    @GetMapping("/home")
    public String pageHome() {
        return "home";
    }

    @GetMapping("/addAccessory")
    public String pageAccessory(Model model) {
        model.addAttribute("newAccessory", new Accessory());
        model.addAttribute("listCategory", repoCategory.findAll());
        return "addAccessory";
    }

    /************** Accessory ***************** */

    @PostMapping("/saveAccessory")
    public String saveAccessory(@ModelAttribute Accessory accessory) {
        repoAccessory.save(accessory);
        return "redirect:/home";
    }
}