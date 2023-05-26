package com.ui.ac.shop.ir.shop.Service.product;


import com.ui.ac.shop.ir.shop.Repository.PropertiesKeyRepository;
import com.ui.ac.shop.ir.shop.model.Product.PropertiesKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertiesKeyService {
    PropertiesKeyRepository propertiesKeyRepository;

    @Autowired
    public PropertiesKeyService(PropertiesKeyRepository propertiesKeyRepository) {
        this.propertiesKeyRepository = propertiesKeyRepository;
    }


    public List<PropertiesKey> getPropertiesKey(){
        return propertiesKeyRepository.findAll();
    }

    public PropertiesKey addPropertiesKey(PropertiesKey propertiesKey){

        if(propertiesKeyRepository.findByName(propertiesKey.getName()).isPresent()){
            return propertiesKeyRepository.findByName(propertiesKey.getName()).get();
        }
        propertiesKeyRepository.save(propertiesKey);
        return propertiesKey;
    }
}
