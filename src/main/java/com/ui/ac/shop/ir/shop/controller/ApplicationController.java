package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Service.ApplicationService;
import com.ui.ac.shop.ir.shop.model.Application;
import com.ui.ac.shop.ir.shop.model.ResponseModels.ApplicationResponseModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.MessageResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponseModel>> getAllApplications(){
        return new ResponseEntity<>(applicationService.getALlApplications() , HttpStatus.OK);
    }
    @GetMapping("/{applicationId}")
    public ResponseEntity<Application> getApplication(@PathVariable Long applicationId){
        return new ResponseEntity<>(applicationService.getApplicationById(applicationId) , HttpStatus.OK);
    }
    @PostMapping("/{applicationId}/accept")
    public ResponseEntity<MessageResponseModel> acceptApplication(@PathVariable Long applicationId){
        applicationService.acceptApplication(applicationId);
        return new ResponseEntity<>(new MessageResponseModel("application accepted successfully"), HttpStatus.OK);
    }

    @PostMapping("/{applicationId}/reject")
    public ResponseEntity<MessageResponseModel> rejectApplication(@PathVariable Long applicationId){
        applicationService.rejectApplication(applicationId);
        return new ResponseEntity<>(new MessageResponseModel("application rejected successfully"), HttpStatus.OK);
    }




}
