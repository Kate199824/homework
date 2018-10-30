package com.database.hw.entity.packages;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DomesticSurfPackage {
    @Id
    String pkgId;
    double cost;
    double maxAmount;

    public String getPkgId() {
        return pkgId;
    }

    public double getCost() {
        return cost;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    protected DomesticSurfPackage(){}

    public DomesticSurfPackage(String pkgId, double cost, double maxAmount) {
        this.pkgId = pkgId;
        this.cost = cost;
        this.maxAmount = maxAmount;
    }

    public String toString(){
        return "国内流量套餐 "+pkgId+"  套餐价格："+cost+"元  套餐流量："+maxAmount+"M";
    }
}
