package com.ui.ac.shop.ir.shop.model.ResponseModels;

import com.ui.ac.shop.ir.shop.model.Product.Category;
import com.ui.ac.shop.ir.shop.model.Product.Product;
import com.ui.ac.shop.ir.shop.model.Product.ProductProperty;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;


@Value
public class FullProduct extends RepresentationModel<FullProduct> {


    Long id;
    String name;
    int count;
    int price;
    double averagePoint;
    Category category;
    String image;

    List<ProductPropertiesResponse> productProperties;



    public FullProduct(Product product, List<ProductProperty> productProperties) {
        this.id = product.getId();
        this.name = product.getName();
        this.count = product.getCount();
        this.price = product.getPrice();
        this.averagePoint = product.getAveragePoint();
        this.category = product.getCategory();
        this.image = product.getImage();
        this.productProperties = new ArrayList<>();
        for (ProductProperty productProperty : productProperties){

            String value = productProperty.getValue();
            String key = productProperty.getPropertiesKey().getName();
            this.productProperties.add(new ProductPropertiesResponse(  key , value  ));
        }


    }


}
