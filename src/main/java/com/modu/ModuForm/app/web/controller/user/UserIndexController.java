package com.modu.ModuForm.app.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

//@RequestMapping("basic/")
@Controller
public class UserIndexController {
    @GetMapping("/login")
    public String userLogin() {
        return "login";
    }

    @GetMapping("/")
    public String mainPage(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "index";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping
    public String surveySave() {
        return "survey-save";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
