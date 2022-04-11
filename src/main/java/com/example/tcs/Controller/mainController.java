package com.example.tcs.Controller;

import com.example.tcs.Tax;
import com.example.tcs.Tax;
//import com.example.tms.service;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class mainController {

    @Autowired
    private TaxRepository taxRepository;

    @GetMapping("/home")
    public String home(Model model)
    {
        model.addAttribute("title","TMS - Demo Application");
        return "home";
    }@GetMapping("/calculator")
    public String calculator(Model model)
    {
        model.addAttribute("taxForm",new Tax());
        return "calculator";
    }
    @GetMapping("/CorporateCalculator")
    public String CorporateCalculator(Model model)
    {
        model.addAttribute("CTaxForm",new Tax());
        return "CorporateCalculator";
    }
    @PostMapping("/save")
    public String saveTax(@ModelAttribute("taxForm") Tax tax){
        String status = tax.getStatus();
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
        Tax taxObject = new Tax();
        taxObject.setStatus(status);
        taxObject.setSalary(salary);
        taxObject.setBonus(bonus);
        taxObject.setAllowance(allowance);
        taxObject.setOthers(others);
        taxObject.setFund(fund);
        taxObject.setCIT(CIT);
        taxObject.setIns(Ins);
        taxObject.setTime(time);
        taxObject.setTotalIncome(total);
        taxObject.setTotalTax((int) totalTax);
        taxRepository.save(taxObject);
        return "redirect:/showResults";
    }

    @GetMapping( "/showResults")
    public String fetch(Model model) {
        model.addAttribute("taxes",taxRepository.findAll());
        return "showResults";
    }

}
