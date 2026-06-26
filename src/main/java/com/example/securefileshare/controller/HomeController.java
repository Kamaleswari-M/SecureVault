package com.example.securefileshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.securefileshare.entity.User;
import com.example.securefileshare.service.FileService;
import com.example.securefileshare.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final UserService userService;
    private final FileService fileService;

public HomeController(UserService userService, FileService fileService) {
    this.userService = userService;
    this.fileService = fileService;
}

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {

        userService.saveUser(user);

        return "login";
    }

   @GetMapping("/dashboard")
public String dashboardPage(Model model,
                            HttpSession session) throws Exception {

    String username = (String) session.getAttribute("username");

    model.addAttribute("username", username);

    model.addAttribute("files",
            fileService.getAllFiles(username));

    model.addAttribute("userCount",
            userService.getUserCount());

    model.addAttribute("fileCount",
            fileService.getFileCount());

    return "dashboard";
}

    @PostMapping("/login")
public String loginUser(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

    User user = userService.login(username, password);

    if (user != null) {

        session.setAttribute("username", user.getUsername());

        return "redirect:/dashboard";
    }

    return "login";
}
}
