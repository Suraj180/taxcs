package com.example.tcs.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminController {

    @GetMapping("/adminLogin")
    public String home(Model model)
    {
        model.addAttribute("AdminLogin","TMS - Demo Application");
        return "adminLogin";
    }
}
