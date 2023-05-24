package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Service.ApplicationService;
import com.ui.ac.shop.ir.shop.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications(){
        return new ResponseEntity<>(applicationService.getALlApplications() , HttpStatus.OK);
    }
    @GetMapping("/{applicationId}")
    public ResponseEntity<Application> getApplication(@PathVariable Long applicationId){
        return new ResponseEntity<>(applicationService.getApplicationById(applicationId) , HttpStatus.OK);
    }


}
