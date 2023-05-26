package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Exception.EntityNotFoundException;
import com.ui.ac.shop.ir.shop.Repository.ApplicationRepository;
import com.ui.ac.shop.ir.shop.model.Application;
import com.ui.ac.shop.ir.shop.model.Enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> getALlApplications(){
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id){
        Optional<Application> application =  applicationRepository.findApplicationById(id);
        if (application.isPresent())return application.get();
        throw new EntityNotFoundException("Application" , id);
    }

    public void acceptApplication(Long id){
        Application application = this.getApplicationById(id);
        application.setStatus(Status.ACCEPTED);
        applicationRepository.save(application);
    }
    public void rejectApplication(Long id){
        Application application = this.getApplicationById(id);
        application.setStatus(Status.REJECTED);
        applicationRepository.save(application);
    }

    public void addApplication(Application application){
        applicationRepository.save(application);
    }

}
