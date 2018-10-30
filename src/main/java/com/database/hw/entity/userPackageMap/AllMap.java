package com.database.hw.entity.userPackageMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AllMap {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    long mapId;
    long uid;
    String pkgId;
    int pkgType;//1.call 2.message 3.local 4.domestic
    String startDate;//开始时间
    String endDate;//结束时间

    public long getMapId() {
        return mapId;
    }

    public long getUid() {
        return uid;
    }

    public String getPkgId() {
        return pkgId;
    }

    public int getPkgType() {
        return pkgType;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    protected AllMap(){}

    public AllMap(long uid, String pkgId, int pkgType, String startDate, String endDate) {
        this.uid = uid;
        this.pkgId = pkgId;
        this.pkgType = pkgType;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
