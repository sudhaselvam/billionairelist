package com.targettech.springboot.crud.billionaireslist.controllers;

import com.targettech.springboot.crud.billionaireslist.entities.Billionaire;
import com.targettech.springboot.crud.billionaireslist.service.BillionaireServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;


@Controller
public class BillionairesController {

    @Autowired
     private  BillionaireServices billionaireServices;

    // it will show index page with initial billionaire  list
    @GetMapping("/")
    public String showBillionaires(Model model) {
        return loadBillionaires(model);
    }
    //show the  add billionaire form to enter data.
    @GetMapping("/addBillionaire")
    public String showAddBillionaireForm(Billionaire billionaire) {
        return "add-billionaire";
    }
    // add billionaire and check the value either its valid or not if value is erorr
    @PostMapping("/addBillionaire")
    public String addBillionaire(@Valid Billionaire billionaire, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-billionaire";
        }

        billionaireServices.save(billionaire);
        return loadBillionaires(model);
    }

    @GetMapping("/updateBillionaire/{id}")
    public String showUpdateBillionaireForm(@PathVariable("id") long id, Model model) {
       // model stores a billionaire object from service based on Id .
        model.addAttribute("billionaire",billionaireServices.findById(id));
        return "update-billionaire";
    }

    @PostMapping("/updateBillionaire/{id}")
    public String updateBillionaire(@PathVariable("id") long id, @Valid Billionaire billionaire, BindingResult result, Model model) {
        if (result.hasErrors()) {
            billionaire.setId(id);
            return "update-billionaire";

        }
        billionaireServices.save(billionaire);
        return loadBillionaires(model);
    }

    @GetMapping("/deleteBillionaire/{id}")
    public String deleteContact(@PathVariable("id") long id, Model model) {
        billionaireServices.delete(billionaireServices.findById(id));
        return loadBillionaires(model);
    }

    private String loadBillionaires(Model model) {
        //model stores sorted billionaire objects from service and return to index.
        model.addAttribute("billionaires", billionaireServices.findSortedBillionaireNetWorth());
        return "index";
    }
}

