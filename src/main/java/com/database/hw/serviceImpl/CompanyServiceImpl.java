package com.database.hw.serviceImpl;

import com.database.hw.entity.User;
import com.database.hw.entity.packages.CallPackage;
import com.database.hw.entity.packages.DomesticSurfPackage;
import com.database.hw.entity.packages.LocalSurfPackage;
import com.database.hw.entity.packages.MessagePackage;
import com.database.hw.repository.packages.CallPackageRepository;
import com.database.hw.repository.packages.DomesticSurfPackageRepository;
import com.database.hw.repository.packages.LocalSurfPackageRepository;
import com.database.hw.repository.packages.MessagePackageRepository;
import com.database.hw.repository.userPackageMap.AllMapRepository;
import com.database.hw.repository.UserRepository;
import com.database.hw.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AllMapRepository allMapRepository;
    @Autowired
    CallPackageRepository callPackageRepository;
    @Autowired
    MessagePackageRepository messagePackageRepository;
    @Autowired
    LocalSurfPackageRepository localSurfPackageRepository;
    @Autowired
    DomesticSurfPackageRepository domesticSurfPackageRepository;

    /**
     * 公司添加一个用户
     * @param uid
     * @param uname
     * @param phoneNumber
     */
    @Override
    public void addUser(long uid, String uname, String phoneNumber) {
        User user = new User(uid, uname, phoneNumber);
        userRepository.saveAndFlush(user);
    }

    /**
     * 公司制定添加一个电话套餐
     * @param id
     * @param cost
     * @param maxTime
     */
    @Override
    public void addCallPackage(String id, double cost, double maxTime) {
        CallPackage callPackage = new CallPackage(id, cost, maxTime);
        callPackageRepository.saveAndFlush(callPackage);
    }

    /**
     * 公司制定添加一个短信套餐
     * @param id
     * @param cost
     * @param maxNumber
     */
    @Override
    public void addMessagePackage(String id, double cost, int maxNumber) {
        MessagePackage messagePackage = new MessagePackage(id, cost, maxNumber);
        messagePackageRepository.saveAndFlush(messagePackage);
    }

    /**
     * 公司制定添加一个本地流量套餐
     * @param id
     * @param cost
     * @param maxAmount
     */
    @Override
    public void addLocalSurfPackage(String id, double cost, double maxAmount) {
        LocalSurfPackage localSurfPackage = new LocalSurfPackage(id, cost, maxAmount);
        localSurfPackageRepository.saveAndFlush(localSurfPackage);
    }

    /**
     * 公司制定添加一个国内流量套餐
     * @param id
     * @param cost
     * @param maxAmount
     */
    @Override
    public void addDomesticSurfPackage(String id, double cost, double maxAmount) {
        DomesticSurfPackage domesticSurfPackage = new DomesticSurfPackage(id, cost, maxAmount);
        domesticSurfPackageRepository.saveAndFlush(domesticSurfPackage);
    }

}
