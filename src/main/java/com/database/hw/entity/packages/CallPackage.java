package com.database.hw.entity.packages;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CallPackage {
    @Id
    String pkgId;
    double cost;
    double maxTime;

    protected CallPackage(){}

    public CallPackage(String pkgId, double cost, double maxTime) {
        this.pkgId = pkgId;
        this.cost = cost;
        this.maxTime = maxTime;
    }

    public String toString(){
        return "话费套餐 "+pkgId+"  套餐价格："+cost+" 元  套餐最高通话时长："+maxTime+" 分钟";
    }

    public double getCost() {
        return cost;
    }

    public double getMaxTime() {
        return maxTime;
    }

    public String getPkgId() {

        return pkgId;
    }
}
