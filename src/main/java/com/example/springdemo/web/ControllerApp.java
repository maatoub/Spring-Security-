package com.example.springdemo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springdemo.entities.Accessory;
import com.example.springdemo.entities.Category;
import com.example.springdemo.repository.RepoAccessory;
import com.example.springdemo.repository.RepoCategory;

@Controller
public class ControllerApp {

    @Autowired
    RepoAccessory repoAccessory;

    @Autowired
    RepoCategory repoCategory;

    @GetMapping("/home")
    public String pageHome(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "4") int size,
            @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Accessory> accessory = repoAccessory.findByBrandContains(keyword, pageable);
        model.addAttribute("listAccessory", accessory);
        model.addAttribute("pages", new int[accessory.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "home";
    }

    /************** Accessory ***************** */

    @GetMapping("/accessory/add")
    public String pageAccessory(Model model) {
        model.addAttribute("newAccessory", new Accessory());
        model.addAttribute("listCategory", repoCategory.findAll());
        return "addAccessory";
    }

    @PostMapping("/accessory/save")
    public String saveAccessory(Model model, @ModelAttribute Accessory accessory) {
        repoAccessory.save(accessory);

        return "redirect:/accessory/add";
    }

    /************** Category ***************** */

    @GetMapping("/category/add")
    public String pageCategory(Model model) {
        model.addAttribute("newCategory", new Category());
        return "addCategory";
    }

    @PostMapping("/category/save")
    public String addNewCategory(@ModelAttribute Category category) {
        repoCategory.save(category);
        return "redirect:/home";
    }

    /************** Accessory ***************** */

    @GetMapping("/accessory/delete")
    public String deleteAcc(Long id, String keyword, int page) {
        repoAccessory.deleteById(id);
        return "redirect:/home?keyword=" + keyword + "&page=" + page;
    }

    @GetMapping("/template")
    public String templatePage() {

        return "template";
    }
}