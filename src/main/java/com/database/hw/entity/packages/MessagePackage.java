package com.database.hw.entity.packages;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MessagePackage {

    @Id
    String pkgId;
    double cost;
    int maxNumber;

    protected MessagePackage(){}

    public String getPkgId() {
        return pkgId;
    }

    public double getCost() {
        return cost;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public MessagePackage(String pkgId, double cost, int maxNumber) {
        this.pkgId = pkgId;
        this.cost = cost;
        this.maxNumber = maxNumber;
    }

    public String toString(){
        return "短信套餐 "+pkgId+"  套餐价格："+cost+"元  套餐短信数："+maxNumber+" 条";
    }
}
