package com.database.hw.entity.records;

import com.database.hw.util.DateUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LocalSurfRecord {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    long recordId;
    long uid;
    String startTime;
    double amount;//流量多少
    double cost;//花费

    protected LocalSurfRecord() {}

    public LocalSurfRecord(long uid, String startTime, double amount, double cost) {
        this.uid = uid;
        this.startTime = startTime;
        this.amount = amount;
        this.cost = cost;
    }

    public String toString(){
        DateUtil dateUtil = new DateUtil();
        return "本地流量记录" +recordId+ " 开始时间:"+ dateUtil.getTimeString(startTime)+" 流量:"+amount+"M  费用:"+cost+"元";
    }
}
