package com.example.tcs.Controller;

import com.example.tcs.*;
import com.example.tcs.CRUD.entity.Posts;
import com.example.tcs.CRUD.repository.postRepository;
import com.example.tcs.Controller.CTaxRepository;
import com.example.tcs.Controller.TaxRepository;
import com.example.tcs.CTax;
import com.example.tcs.Taxs;
//import com.example.tms.service;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
public class mainController {

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private AdminRepository repo;

    @Autowired
    private postRepository postRepo;

    @Autowired
    private CTaxRepository CTaxRepository;

    @GetMapping("/home")
    public String home(Model model)
    {
        model.addAttribute("title","TMS - Demo Application");
        return "home";
    }

    @GetMapping("/News")
    public String pic(Principal p, Model m)
    {
//        String em= p.getName();
//
//        UserDtls u= repo.findByUsername(em);
//        m.addAttribute("username",u.getUsername());

        List<Posts> list = postRepo.findAll();
        m.addAttribute("all_posts",list);
        return "News";
    }

    @GetMapping("/NewsPage/{id}")
    public String editForm(@PathVariable(value = "id")Long id,Model m) throws UnsupportedEncodingException {
        Optional<Posts> posts = postRepo.findById(id);
        Posts pos = posts.get();
        m.addAttribute("posts",pos);


        return"NewsPage";
    }

    @GetMapping("/calculator")
    public String calculator(Model model)
    {
        model.addAttribute("taxForm",new Taxs());
        return "calculator";
    }

    @PostMapping("/save")
    public String saveTax(@ModelAttribute("taxForm") Taxs tax){
        String status = tax.getStatus();
        String pName = tax.getPName();
        int salary = tax.getSalary();
        int bonus = tax.getBonus();
        int allowance = tax.getAllowance();
        int others = tax.getOthers();
        int fund = tax.getFund();
        int CIT = tax.getCIT();
        int Ins = tax.getIns();
        int time = tax.getTime();
        int total = tax.getTotalIncome();
        double totalTax = tax.getTotalTax();
        Taxs taxsObject = new Taxs();
        taxsObject.setStatus(status);
        taxsObject.setPName(pName);
        taxsObject.setSalary(salary);
        taxsObject.setBonus(bonus);
        taxsObject.setAllowance(allowance);
        taxsObject.setOthers(others);
        taxsObject.setFund(fund);
        taxsObject.setCIT(CIT);
        taxsObject.setIns(Ins);
        taxsObject.setTime(time);
        taxsObject.setTotalIncome(total);
        taxsObject.setTotalTax((int) totalTax);
        taxRepository.save(taxsObject);
        return "redirect:/showResults";
    }
    @GetMapping( "/showResults")
    public String fetch(Model model) {
        model.addAttribute("taxes",taxRepository.findAll());
        return "showResults";
    }

    @GetMapping("/CorporateCalculator")
    public String CorporateCalculator(Model model)
    {
        model.addAttribute("CTaxForm",new CTax());
        return "CorporateCalculator";
    }

    @PostMapping("/save1")
    public String saveTax1(@ModelAttribute("CTaxForm") CTax Ctax){

        int AnnualIncome = Ctax.getAnnualIncome();
        String companyName = Ctax.getCompanyName();
        int others = Ctax.getOthers();
        int TaxRate = Ctax.getTaxRate();
        int TIncome = Ctax.getTIncome();
        double TTax = Ctax.getTTax();
        CTax taxObject = new CTax();
        taxObject.setAnnualIncome(AnnualIncome);
        taxObject.setCompanyName(companyName);
        taxObject.setOthers(others);
        taxObject.setTIncome(TIncome);
        taxObject.setTaxRate(TaxRate);
        taxObject.setTTax(TTax);
        CTaxRepository.save(taxObject);
        return "redirect:/showResults1";
    }

    @GetMapping( "/showResults1")
    public String fetch2(Model model) {
        model.addAttribute("CTaxes",CTaxRepository.findAll());
        return "showResults1";
    }



}
