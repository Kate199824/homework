package com.database.hw.entity.records;

import com.database.hw.util.DateUtil;
import jdk.nashorn.internal.codegen.CompilerConstants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CallRecord {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    long recordId;
    long uid;
    String startTime;
    double duration;//时长，按分钟
    double cost;//花费

    protected CallRecord() {};

    public CallRecord(long uid, String startTime, double duration, double cost) {
        this.uid = uid;
        this.startTime = startTime;
        this.duration = duration;
        this.cost = cost;
    }

    public String toString(){
        DateUtil dateUtil = new DateUtil();
        return "通话记录" +recordId+ " 开始时间:"+ dateUtil.getTimeString(startTime)+" 通话时长:"+duration+"分钟 通话费用:"+cost+"元";
    }
}
