package com.example.tcs.Controller;

import com.example.tcs.CTax;
import com.example.tcs.Controller.CTaxRepository;
import com.example.tcs.Controller.TaxRepository;
import com.example.tcs.Taxs;
import com.example.tcs.CTax;
import com.example.tcs.Taxs;
//import com.example.tms.service;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class mainController {

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private CTaxRepository CTaxRepository;

    @GetMapping("/home")
    public String home(Model model)
    {
        model.addAttribute("title","TMS - Demo Application");
        return "home";
    }

    @GetMapping("/ptaximg")
    public String pic(Model model)
    {
        model.addAttribute("title","TCS - Picture");
        return "ptaximg";
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
