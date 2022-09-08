package com.example.camelconsumer.bean;

public class Car {
    private String carName;
    private String carModel;
    private String companyName;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Consumed Response [CarName=" + carName + ", CarModel=" + carModel + ", companyName=" + companyName
                + "]";
    }

}
