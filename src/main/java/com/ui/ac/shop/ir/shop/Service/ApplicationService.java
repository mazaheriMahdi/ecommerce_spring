package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Exception.EntityNotFoundException;
import com.ui.ac.shop.ir.shop.Repository.ApplicationRepository;
import com.ui.ac.shop.ir.shop.Repository.CustomerRepository;
import com.ui.ac.shop.ir.shop.Repository.PaymentRepository;
import com.ui.ac.shop.ir.shop.Repository.ReviewRepository;
import com.ui.ac.shop.ir.shop.model.Application;
import com.ui.ac.shop.ir.shop.model.Enums.Status;
import com.ui.ac.shop.ir.shop.model.Enums.Type;
import com.ui.ac.shop.ir.shop.model.Payment.Payment;
import com.ui.ac.shop.ir.shop.model.ResponseModels.ApplicationResponseModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.ReviewResponseModel;
import com.ui.ac.shop.ir.shop.model.Review;
import com.ui.ac.shop.ir.shop.model.User.Customer;
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
    PaymentRepository paymentRepository;
    CustomerRepository customerRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, ReviewRepository reviewRepository, PaymentRepository paymentRepository, CustomerRepository customerRepository) {
        this.applicationRepository = applicationRepository;
        this.reviewRepository = reviewRepository;
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
    }

    public List<ApplicationResponseModel> getALlApplications() {
        List<Application> applicationList = applicationRepository.findAll();
        List<ApplicationResponseModel> applicationResponseModels = new ArrayList<>();
        for (Application application : applicationList) {
            String content = "";

            if (application.getType() == Type.REVIEW){
                Review review = reviewRepository.findReviewsByApplication(application).get();
                content = "REVIEW" + "\nusername :" + review.getUser().getName()+ "\nContent:" + review.getComment();
            } else if (application.getType() == Type.PAYMENT) {
                Payment payment = paymentRepository.findAllByApplication(application).get();
                content = "PAYMENT" + "\nAmount :" + payment.getAmount() + "\nCard Number:" + payment.getCardNumber();

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
        if (application.getType() == Type.PAYMENT){
            Optional<Payment> payment = paymentRepository.findAllByApplication(application);
            if (payment.isPresent()){
                Customer customer = payment.get().getCustomer();
                customer.setCredit(payment.get().getAmount());
                customerRepository.save(customer);
            }
        }
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
