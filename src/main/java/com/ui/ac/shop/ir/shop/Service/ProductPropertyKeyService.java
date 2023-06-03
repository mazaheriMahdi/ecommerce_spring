package com.ui.ac.shop.ir.shop.Service;

import com.ui.ac.shop.ir.shop.Repository.PropertiesKeyRepository;
import com.ui.ac.shop.ir.shop.model.Product.PropertiesKey;
import com.ui.ac.shop.ir.shop.model.ResponseModels.PropertyKeyResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductPropertyKeyService {
    PropertiesKeyRepository propertiesKeyRepository;


    @Autowired
    public ProductPropertyKeyService(PropertiesKeyRepository propertiesKeyRepository) {
        this.propertiesKeyRepository = propertiesKeyRepository;
    }


    public List<PropertyKeyResponseModel> getAllPropertyKey(){
        List<PropertiesKey> propertiesKeys =  propertiesKeyRepository.findAll();
        List<PropertyKeyResponseModel> propertyKeyResponseModels = new ArrayList<>();
        for (PropertiesKey propertiesKey : propertiesKeys) {
            propertyKeyResponseModels.add(new PropertyKeyResponseModel(propertiesKey.getName()));
        }
        return propertyKeyResponseModels;
    }

}
