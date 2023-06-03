package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Exception.EntityNotFoundException;
import com.ui.ac.shop.ir.shop.Repository.ApplicationRepository;
import com.ui.ac.shop.ir.shop.Repository.ReviewRepository;
import com.ui.ac.shop.ir.shop.model.Application;
import com.ui.ac.shop.ir.shop.model.Enums.Status;
import com.ui.ac.shop.ir.shop.model.Enums.Type;
import com.ui.ac.shop.ir.shop.model.ResponseModels.ApplicationResponseModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.ReviewResponseModel;
import com.ui.ac.shop.ir.shop.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ApplicationService {
    ApplicationRepository applicationRepository;
    ReviewRepository reviewRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, ReviewRepository reviewRepository) {
        this.applicationRepository = applicationRepository;
        this.reviewRepository = reviewRepository;
    }


    public List<ApplicationResponseModel> getALlApplications() {
        List<Application> applicationList = applicationRepository.findAll();
        List<ApplicationResponseModel> applicationResponseModels = new ArrayList<>();
        for (Application application : applicationList) {
            String content = "";

            if (application.getType() == Type.REVIEW){
                Review review = reviewRepository.findReviewsByApplication(application).get();
                content = "REVIEW" + "\nusername :" + review.getUser().getName()+ "\nContent:" + review.getComment();
            }

            applicationResponseModels.add(
                    new ApplicationResponseModel(
                            application.getId(),
                            application.getStatus(),
                            content.toString()
                    ));
        }
        return applicationResponseModels;
    }

    public Application getApplicationById(Long id) {
        Optional<Application> application = applicationRepository.findApplicationById(id);
        if (application.isPresent()) return application.get();
        throw new EntityNotFoundException("Application", id);
    }

    public void acceptApplication(Long id) {
        Application application = this.getApplicationById(id);
        application.setStatus(Status.ACCEPTED);
        applicationRepository.save(application);
    }

    public void rejectApplication(Long id) {
        Application application = this.getApplicationById(id);
        application.setStatus(Status.REJECTED);
        applicationRepository.save(application);
    }

    public void addApplication(Application application) {
        applicationRepository.save(application);
    }

}
