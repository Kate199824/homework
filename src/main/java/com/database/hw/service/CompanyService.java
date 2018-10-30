package com.database.hw.service;

public interface CompanyService {

    public void addUser(long uid, String uname, String phoneNumber);

    public void addCallPackage(String id, double cost, double maxTime);
    public void addMessagePackage(String id, double cost, int maxNumber);
    public void addLocalSurfPackage(String id, double cost, double maxAmount);
    public void addDomesticSurfPackage(String id, double cost, double maxAmount);
}
