package com.ui.ac.shop.ir.shop.model.Product.Stationary;

import model.Product.ProductCondition;

public class NoteBook extends BaseStationery {
    private int pageCount;
    private String pageType;

    public NoteBook(String name, int price, ProductCondition productCondition, String producerCountry, int pageCount, String pageType, int count) {
        super(name, price, productCondition, producerCountry, count);
        this.pageCount = pageCount;
        this.pageType = pageType;
    }


    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    @Override
    public String toString() {
        return super.toString() +
                "pageCount=" + pageCount + "\n" +
                "pageType=" + pageType + "\n"
                ;
    }
}
