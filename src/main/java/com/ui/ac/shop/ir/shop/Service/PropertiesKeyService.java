package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Repository.PropertiesKeyRepository;
import com.ui.ac.shop.ir.shop.model.PropertiesKey;
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

    public void addPropertiesKey(PropertiesKey propertiesKey){
        propertiesKeyRepository.save(propertiesKey);
    }
}
