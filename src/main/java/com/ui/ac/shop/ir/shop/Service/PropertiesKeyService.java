package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Repository.PropertiesKeyRepository;
import com.ui.ac.shop.ir.shop.model.PropertiesKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

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
