package com.database.hw;

import com.database.hw.service.CompanyService;
import com.database.hw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;

@SpringBootApplication
public class HwApplication {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HwApplication.class, args);
        CompanyService companyService = context.getBean(CompanyService.class);
        UserService userService = context.getBean(UserService.class);

//        addUser(companyService);
//
//        addCallPackage(companyService);
//        addMessagePackage(companyService);
//        addLocalSurfPackage(companyService);
//        addDomesticSurfPackage(companyService);
//
//        userAddCallPackage(userService);
//        userAddMessagePackage(userService);
        userAddLocalPackage(userService);
        userAddDomesticPackage(userService);

//          userRemoveCallPackage(userService);
//          userRemoveMessagePackage(userService);
//          userRemoveLocalPackage(userService);
//          userRemoveDomesticPackage(userService);

//        searchCurrentCallPackages(userService);
//        searchCurrentMessagePackages(userService);
//        searchCurrentLocalPackages(userService);
//        searchCurrentDomesticPackages(userService);

//        userDoCall(userService);
//        userDoMessage(userService);
//        userDoLocal(userService);
//        userDoDomestic(userService);

//        searchCurrentCallPackages(userService);
//        searchThisMonthCallRecord(userService);

        searchThisMonthCost(userService);
    }

    /**
     * 添加用户
     * @param companyService
     */
    private static void addUser(CompanyService companyService){
        companyService.addUser(1,"qyc","15952000000");
        companyService.addUser(2,"jbs","15952022680");
    }

    /**
     * 公司制定通话套餐
     * @param companyService
     */
    private static void addCallPackage(CompanyService companyService){
        companyService.addCallPackage("通话套餐1",20.0,100.0);
        companyService.addCallPackage("通话套餐2",30.0,150.0);
        companyService.addCallPackage("通话套餐3",40.0,200.0);
        companyService.addCallPackage("通话套餐4",50.0,300.0);
    }

    /**
     * 公司制定短信套餐
     * @param companyService
     */
    private static void addMessagePackage(CompanyService companyService){
        companyService.addMessagePackage("短信套餐1",10.0,200);
        companyService.addMessagePackage("短信套餐2",15.0,400);
    }

    /**
     * 公司制定本地流量套餐
     * @param companyService
     */
    private static void addLocalSurfPackage(CompanyService companyService){
        companyService.addLocalSurfPackage("本地流量套餐1",20.0,2048);
        companyService.addLocalSurfPackage("本地流量套餐2",25.0,4096);
        companyService.addLocalSurfPackage("本地流量套餐3",30.0,8192);
    }

    /**
     * 公司制定国内流量套餐
     * @param companyService
     */
    private static void addDomesticSurfPackage(CompanyService companyService){
        companyService.addDomesticSurfPackage("国内流量套餐1",30.0,2048);
        companyService.addDomesticSurfPackage("国内流量套餐2",35.0,4096);
        companyService.addDomesticSurfPackage("国内流量套餐3",40.0,8192);
    }

    /**
     * 用户订购通话套餐
     * @param userService
     */
    private static void userAddCallPackage(UserService userService){
        double t1 = System.currentTimeMillis();
//        String res = userService.userAddPackage(1,1,"通话套餐1","20180830000000");
        String res = userService.userAddPackage(1,1,"通话套餐2","20180830000000");
        double t2 = System.currentTimeMillis();
        System.out.println(res);
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户订购短信套餐
     * @param userService
     */
    private static void userAddMessagePackage(UserService userService){
        double t1 = System.currentTimeMillis();
//        String res = userService.userAddPackage(1,2,"短信套餐1","20180830000000");
        String res = userService.userAddPackage(1,2,"短信套餐2","20180830000000");
        double t2 = System.currentTimeMillis();
        System.out.println(res);
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户订购本地流量套餐
     * @param userService
     */
    private static void userAddLocalPackage(UserService userService){
        double t1 = System.currentTimeMillis();
        String res = userService.userAddPackage(1,3,"本地流量套餐2","20180831000000");
        double t2 = System.currentTimeMillis();
        System.out.println(res);
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户订购国内流量套餐
     * @param userService
     */
    private static void userAddDomesticPackage(UserService userService){
        double t1 = System.currentTimeMillis();
        String res = userService.userAddPackage(1,4,"国内流量套餐2","20180830000000");
        double t2 = System.currentTimeMillis();
        System.out.println(res);
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户退订通话套餐
     * @param userService
     */
    private static void userRemoveCallPackage(UserService userService){
        double t1 = System.currentTimeMillis();
        String res = userService.userRemovePackage(1,1,"电话套餐1","20180829000000");
        System.out.println(res);
        double t2 = System.currentTimeMillis();
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户退订短信套餐
     * @param userService
     */
    private static void userRemoveMessagePackage(UserService userService){
        double t1 = System.currentTimeMillis();
        String res = userService.userRemovePackage(1,2,"短信套餐1","20180829000000");
        System.out.println(res);
        double t2 = System.currentTimeMillis();
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户退订本地流量套餐
     * @param userService
     */
    private static void userRemoveLocalPackage(UserService userService){
        double t1 = System.currentTimeMillis();
        String res = userService.userRemovePackage(1,3,"本地流量套餐1","20180829000000");
        System.out.println(res);
        double t2 = System.currentTimeMillis();
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户退订国内流量套餐
     * @param userService
     */
    private static void userRemoveDomesticPackage(UserService userService){
        double t1 = System.currentTimeMillis();
        String res = userService.userRemovePackage(1,4,"国内流量套餐1","20180829000000");
        System.out.println(res);
        double t2 = System.currentTimeMillis();
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户产生通话时长
     * @param userService
     */
    private static void userDoCall(UserService userService){
        double t1 = System.currentTimeMillis();
        String res =userService.doCall(1,"20180916202000",12);
        System.out.println(res);
        double t2 = System.currentTimeMillis();
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户发短信
     * @param userService
     */
    private static void userDoMessage(UserService userService){
        double t1 = System.currentTimeMillis();
        String res =userService.sendMessage(1,"20180923000000");
        System.out.println(res);
        double t2 = System.currentTimeMillis();
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户产生本地流量
     * @param userService
     */
    private static void userDoLocal(UserService userService){
        double t1 = System.currentTimeMillis();
        String res =userService.localSurf(1,"20180922000000",50);
        System.out.println(res);
        double t2 = System.currentTimeMillis();
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户产生国内流量
     * @param userService
     */
    private static void userDoDomestic(UserService userService){
        double t1 = System.currentTimeMillis();
        String res =userService.domesticSurf(1,"20180922000000",60);
        System.out.println(res);
        double t2 = System.currentTimeMillis();
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户查询当前有效通话套餐
     * @param userService
     */
    private static void searchCurrentCallPackages(UserService userService){
        System.out.println("===========用户当前有效通话套餐===========");
        double t1 = System.currentTimeMillis();
        ArrayList<String> list = userService.searchCurrentCallPackages(1,"20180905000000");
        for(String s : list){
            System.out.println(s);
        }
        double t2 = System.currentTimeMillis();
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户查询当前有效短信套餐
     * @param userService
     */
    private static void searchCurrentMessagePackages(UserService userService){
        System.out.println("===========用户当前有效短信套餐===========");
        double t1 = System.currentTimeMillis();
        ArrayList<String> list = userService.searchCurrentMessagePackages(1,"20180905000000");
        for(String s : list){
            System.out.println(s);
        }
        double t2 = System.currentTimeMillis();
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户查询当前有效本地流量套餐
     * @param userService
     */
    private static void searchCurrentLocalPackages(UserService userService){
        System.out.println("===========用户当前有效本地流量套餐===========");
        double t1 = System.currentTimeMillis();
        ArrayList<String> list = userService.searchCurrentLocalPackages(1,"20180905000000");
        for(String s : list){
            System.out.println(s);
        }
        double t2 = System.currentTimeMillis();
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户查询当前有效国内流量套餐
     * @param userService
     */
    private static void searchCurrentDomesticPackages(UserService userService){
        System.out.println("===========用户当前有效国内流量套餐===========");
        double t1 = System.currentTimeMillis();
        ArrayList<String> list = userService.searchCurrentDomesticPackages(1,"20180905000000");
        for(String s : list){
            System.out.println(s);
        }
        double t2 = System.currentTimeMillis();
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }

    /**
     * 用户查询本月通话记录
     * @param userService
     */
    private static void searchThisMonthCallRecord(UserService userService){
        System.out.println("===========用户本月通话记录===========");
        ArrayList<String> list = userService.searchRecords(1,1,"20180912000000");
        for(String s : list){
            System.out.println(s);
        }
    }

    /**
     * 用户查询本月账单
     * @param userService
     */
    private static void searchThisMonthCost(UserService userService){
        System.out.println("===========用户本月消费===========");
        double t1 = System.currentTimeMillis();
        String result = userService.searchCost(1,"20180903000000");
        double t2 = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("使用时长："+(t2-t1)+"Millis");
    }
}
