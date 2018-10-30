package com.database.hw.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private long uid;//userId
    private String uname;
    private String phoneNumber;

    protected User(){};

    public User(long uid, String uname, String phoneNumber) {
        this.uid = uid;
        this.uname = uname;
        this.phoneNumber = phoneNumber;
    }
}
