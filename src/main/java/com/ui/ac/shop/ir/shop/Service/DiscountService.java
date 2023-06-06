package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Repository.DiscountRepository;
import com.ui.ac.shop.ir.shop.model.Discount;
import com.ui.ac.shop.ir.shop.model.ResponseModels.DiscountResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService {
    //TODO : fix set discount big
    DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public void addDiscount(Discount discount) {
        discountRepository.save(discount);
    }


    public List<DiscountResponseModel> getAllUserDiscounts(Long id) {
        List<DiscountResponseModel> discounts = new ArrayList<>();
        if (discountRepository.findAllByUserId(id).isPresent()) {
            discountRepository.findAllByUserId(id).get().forEach(
                    discount -> {
                        discounts.add(getDiscountResponseModel(discount)
                        );
                    }
            );
            return discounts;
        }
        return List.of();
    }

    public List<DiscountResponseModel> getAllDiscounts() {
        List<DiscountResponseModel> discounts = new ArrayList<>();
        discountRepository.findAll().forEach(
                discount -> {
                    discounts.add(getDiscountResponseModel(discount)
                    );
                }
        );
        return discounts;
    }

    private static DiscountResponseModel getDiscountResponseModel(Discount discount) {
        return new DiscountResponseModel(
                discount.getId(),
                discount.getCode(),
                discount.getUser().getName(),
                discount.getPercent(),
                discount.getCount(),
                discount.getExpireDate());
    }
}