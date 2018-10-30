package com.database.hw.entity.packages;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LocalSurfPackage {
    @Id
    String pkgId;
    double cost;
    double maxAmount;

    protected LocalSurfPackage(){}

    public LocalSurfPackage(String pkgId, double cost, double maxAmount) {
        this.pkgId = pkgId;
        this.cost = cost;
        this.maxAmount = maxAmount;
    }

    public String getPkgId() {
        return pkgId;
    }

    public double getCost() {
        return cost;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public String toString(){
        return "本地流量套餐 "+pkgId+"  套餐价格："+cost+"元  套餐流量："+maxAmount+"M";
    }
}
