package com.ticket.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ticket.entity.User;
import com.ticket.service.UserService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
    private final UserService userService;

    public MyPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String viewMyPage(Model model, Principal principal) {
        User user = userService.getCurrentUser(principal.getName());
        model.addAttribute("user", user);
        return "mypage";
    }
}
