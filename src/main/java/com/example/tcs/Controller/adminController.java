package com.example.tcs.Controller;

import com.example.tcs.LoginForm;
import com.example.tcs.Taxs;
import com.example.tcs.UserDtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class adminController {

    @Autowired
    private AdminRepository repo;

    @Autowired
    private BCryptPasswordEncoder bp;

    @GetMapping("/adminLogin")
    public String AdminForm(Model model)
    {
        model.addAttribute("AdminLogin","TMS - Demo Application");
        return "adminLogin";
    }

    @GetMapping("/adminRegister")
    public String RegisterForm(Model model)
    {
        model.addAttribute("AdminLogin","TMS - Demo Application");
        return "adminRegister";
    }

    @RequestMapping(value = "/adminRegister", method = { RequestMethod.POST })
    public String RegisterForm(@ModelAttribute(name="u") UserDtls u, HttpSession session)
    {
        System.out.println(u);
        u.setPassword((bp.encode(u.getPassword())));
        u.setRole("ROLE_ADMIN");

        repo.save(u);
        session.setAttribute("message","User Registered sucessfully.");
        return "adminRegister";
    }

//    @PostMapping("/dashboard")
//    public String calculator(Model model)
//    {
//        model.addAttribute("dashboard","TMS - dashboard");
//        return "dashboard";
//    }
//    //Checking for login credentials
//    @PostMapping("/adminLogin")
//    public String Login(@ModelAttribute(name="loginForm") LoginForm loginForm, Model model){
//
//        String username = LoginForm.getUsername();
//        String password = LoginForm.getPassword();
//
//        if ("admin".equals(username) && "admin".equals(password)){
//            //if username and password is correct, we return dashboard
//            return "dashboard";
//        }
//
//        // if username or password is wrong,
//        model.addAttribute("invalidCredentials",  true);
//        //return again to login page
//        return "adminLogin";
//
//    }
}
