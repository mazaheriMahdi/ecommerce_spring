package com.ui.ac.shop.ir.shop.model.Product.Digital.PC;

import model.Product.Digital.BaseDigital;
import model.Product.ProductCondition;

public class Pc extends BaseDigital {

    String cpuModel;
    int memoryCapacity;

    public Pc(String name, int price, ProductCondition productCondition, int weight, int height, int width, int length, String cpuModel, int memoryCapacity , int count) {
        super(name, price, productCondition, weight, height, width, length , count);
        this.cpuModel = cpuModel;
        this.memoryCapacity = memoryCapacity;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    @Override
    public String toString() {
        return super.toString() +
                "cpuModel=" + cpuModel + "\n"+
                "memoryCapacity=" + memoryCapacity +"\n";
    }
}
