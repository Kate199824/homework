package com.database.hw.service;

import java.util.ArrayList;

public interface UserService {

    public String doCall(long uid, String startTime, double duration);
    public String sendMessage(long uid, String startTime);
    public String localSurf(long uid, String startTime, double amount);
    public String domesticSurf(long uid, String startTime, double amount);

    public String userAddPackage(long uid, int pkgType, String pkgId, String currentDate);
    public String userRemovePackage(long uid, int pkgType, String pkgId, String currentDate);

    public ArrayList<String> searchAllPackages(long uid, int type, String currentDate);
    public ArrayList<String> searchCurrentCallPackages(long uid, String currentDate);
    public ArrayList<String> searchCurrentMessagePackages(long uid, String currentDate);
    public ArrayList<String> searchCurrentLocalPackages(long uid, String currentDate);
    public ArrayList<String> searchCurrentDomesticPackages(long uid, String currentDate);

    public ArrayList<String> searchRecords(long uid, int type, String date);
    public String searchCost(long uid, String date);
}
