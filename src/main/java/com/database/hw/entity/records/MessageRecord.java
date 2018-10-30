package com.database.hw.entity.records;

import com.database.hw.util.DateUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MessageRecord {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    long recordId;
    long uid;
    String startTime;
    double cost;//花费

    protected MessageRecord() {}

    public MessageRecord(long uid, String startTime, double cost) {
        this.uid = uid;
        this.startTime = startTime;
        this.cost = cost;
    }

    public String toString(){
        DateUtil dateUtil = new DateUtil();
        return "短信记录" +recordId+ " 时间:"+ dateUtil.getTimeString(startTime)+"  短信费用:"+cost+"元";
    }
}
