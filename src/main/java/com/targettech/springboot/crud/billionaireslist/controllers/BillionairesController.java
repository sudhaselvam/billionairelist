package com.targettech.springboot.crud.billionaireslist.controllers;

import com.targettech.springboot.crud.billionaireslist.entities.Billionaire;
import com.targettech.springboot.crud.billionaireslist.repositories.BillionaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.expression.Lists;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class BillionairesController {
    private final BillionaireRepository billionaireRepository;
    @Autowired
    public BillionairesController(BillionaireRepository billionaireRepository){
        this.billionaireRepository = billionaireRepository; }

        @GetMapping("/")
     public String showBillionaires(Model model) {
         model.addAttribute("billionaires", billionaireRepository.findAll(Sort.by(Sort.Direction.DESC,"networth")));
         return "index";
     }
    @GetMapping("/addBillionaire")
    public String showAddBillionaireForm(Billionaire billionaire) {
        return "add-billionaire";
    }
    @PostMapping("/addBillionaire")
    public String addBillionaire(@Valid Billionaire billionaire, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-billionaire";
        }

        billionaireRepository.save(billionaire);
        model.addAttribute("billionaires", billionaireRepository.findAll(Sort.by(Sort.Direction.DESC,"networth")));
        return "index";
    }

    @GetMapping("/updateBillionaire/{id}")
    public String showUpdateBillionaireForm(@PathVariable("id") long id, Model model) {
        Billionaire billionaire = billionaireRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid billionaire Id:" + id));
        model.addAttribute("billionaire", billionaire);
        return "update-billionaire";
    }

    @PostMapping("/updateBillionaire/{id}")
    public String updateBillionaire(@PathVariable("id") long id, @Valid Billionaire billionaire, BindingResult result, Model model) {
        if (result.hasErrors()) {
            billionaire.setId(id);
            return "update-billionaire";
        }
        billionaireRepository.save(billionaire);
        model.addAttribute("billionaires", billionaireRepository.findAll(Sort.by(Sort.Direction.DESC,"networth")));
        return "index";
    }
        @GetMapping("/deleteBillionaire/{id}")
        public String deleteContact(@PathVariable("id") long id, Model model) {
            Billionaire billionaire = billionaireRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
            billionaireRepository.delete(billionaire);
            model.addAttribute("billionaires", billionaireRepository.findAll(Sort.by(Sort.Direction.DESC,"networth")));
            return "index";
        }
    }

