package com.database.hw.serviceImpl;

import com.database.hw.entity.packages.CallPackage;
import com.database.hw.entity.packages.DomesticSurfPackage;
import com.database.hw.entity.packages.LocalSurfPackage;
import com.database.hw.entity.packages.MessagePackage;
import com.database.hw.entity.records.CallRecord;
import com.database.hw.entity.records.DomesticSurfRecord;
import com.database.hw.entity.records.LocalSurfRecord;
import com.database.hw.entity.records.MessageRecord;
import com.database.hw.entity.userPackageMap.AllMap;
import com.database.hw.repository.UserRepository;
import com.database.hw.repository.packages.CallPackageRepository;
import com.database.hw.repository.packages.DomesticSurfPackageRepository;
import com.database.hw.repository.packages.LocalSurfPackageRepository;
import com.database.hw.repository.packages.MessagePackageRepository;
import com.database.hw.repository.records.CallRecordRepository;
import com.database.hw.repository.records.DomesticSurfRecordRepository;
import com.database.hw.repository.records.LocalSurfRecordRepository;
import com.database.hw.repository.records.MessageRecordRepository;
import com.database.hw.repository.userPackageMap.AllMapRepository;
import com.database.hw.service.UserService;
import com.database.hw.util.DateUtil;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    DateUtil dateUtil;
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
    @Autowired
    CallRecordRepository callRecordRepository;
    @Autowired
    MessageRecordRepository messageRecordRepository;
    @Autowired
    LocalSurfRecordRepository localSurfRecordRepository;
    @Autowired
    DomesticSurfRecordRepository domesticSurfRecordRepository;

    /**
     * 用户产生通话时长
     * @param startTime
     * @param duration 分钟数
     * @return 产生费用
     */
    @Override
    public String doCall(long uid, String startTime, double duration) {
        //1.查询用户现在是否有套餐
        double usedTime = getTotalCallTime(uid,startTime);//已使用时间
        List<AllMap> packageList = allMapRepository.findUserCurrentPackage(1,"20180918000000",1);
        //  1.1有
        double packageTime = 0.0;//套餐时间
        for(AllMap map : packageList){
            double maxTime = callPackageRepository.getPackageMaxTimeByPkgId(map.getPkgId());
            packageTime += maxTime;
        }

        CallRecord callRecord;
        double cost = 0.0;
        if(packageTime-usedTime >= duration){
            //使用套餐
            callRecord = new CallRecord(uid,startTime,duration,0.0);
        }
        else if(packageTime-usedTime > 0){
            cost = 0.5 * (duration-packageTime+usedTime);
            callRecord = new CallRecord(uid,startTime,duration,cost);
        }
        else{
            cost = 0.5 * duration;
            callRecord = new CallRecord(uid,startTime,duration,cost);
        }

        callRecordRepository.saveAndFlush(callRecord);

        return "尊敬的用户您好！您本次通话时长："+duration+"分钟  产生费用："+cost+"元";
    }

    //用户发送短信
    @Override
    public String sendMessage(long uid, String startTime) {
        int usedNumber = this.getTotalMessageNumber(uid,startTime);
        List<AllMap> packageList = allMapRepository.findUserCurrentPackage(1,"20180918000000",2);
        int packageNumber = 0;
        for(AllMap map : packageList){
            int maxNumber = (int) messagePackageRepository.getPackageMaxNumberByPkgId(map.getPkgId());
            packageNumber += maxNumber;
        }

        MessageRecord messageRecord;
        double cost = 0.0;
        if(packageNumber<=usedNumber){
            cost = 0.1;
        }
        messageRecord = new MessageRecord(uid,startTime,cost);
        messageRecordRepository.saveAndFlush(messageRecord);
        return "尊敬的用户您好！您本次短信产生费用："+cost+"元";
    }

    @Override
    public String localSurf(long uid, String startTime, double amount) {
        double usedAmount = getTotalLocalAmount(uid, startTime);
        List<AllMap> packageList = allMapRepository.findUserCurrentPackage(1,"20180918000000",3);
        double packageAmount = 0.0;
        for(AllMap map : packageList){
            double maxAmount = localSurfPackageRepository.getPackageMaxAmountByPkgId(map.getPkgId());
            packageAmount += maxAmount;
        }
        double cost = 0.0;

        if(packageAmount - usedAmount >= amount){
            cost = 0.0;
        }
        else if(packageAmount-usedAmount>0){
            cost = 3.0 * (amount-packageAmount+usedAmount);
        }
        else{
            cost = 3.0 * amount;
        }

        LocalSurfRecord localSurfRecord = new LocalSurfRecord(uid, startTime, amount,cost);
        localSurfRecordRepository.saveAndFlush(localSurfRecord);

        return "尊敬的用户您好！您本次使用本地流量："+amount+"M  产生费用："+cost+"元";
    }

    @Override
    public String domesticSurf(long uid, String startTime, double amount) {
        double usedAmount = getTotalLocalAmount(uid, startTime);
        List<AllMap> packageList = allMapRepository.findUserCurrentPackage(1,"20180918000000",4);
        double packageAmount = 0.0;
        for(AllMap map : packageList){
            double maxAmount = domesticSurfPackageRepository.getPackageMaxAmountByPkgId(map.getPkgId());
            packageAmount += maxAmount;
        }
        double cost = 0.0;
        if(packageAmount - usedAmount >= amount){
            cost = 0.0;
        }
        else if(packageAmount-usedAmount>0){
            cost = 5.0 * (amount-packageAmount+usedAmount);
        }
        else{
            cost = 5.0 * amount;
        }

        DomesticSurfRecord domesticSurfRecord = new DomesticSurfRecord(uid, startTime, amount,cost);
        domesticSurfRecordRepository.saveAndFlush(domesticSurfRecord);

        return "尊敬的用户您好！您本次使用国内流量："+amount+"M  产生费用："+cost+"元";
    }

    /**
     * 用户增加套餐
     * @param uid
     * @param pkgType
     * @param pkgId
     * @param currentDate
     * @return 是否成功
     */
    @Override
    public String userAddPackage(long uid, int pkgType, String pkgId, String currentDate) {
        if(allMapRepository.findAllByUidAndAndPkgId(uid,pkgId).size() > 0) {
            return "不可重复订购";
        }
        else{
            String startDate = dateUtil.getNextMonthStart(currentDate);//下月生效
            AllMap allMap = new AllMap(uid,pkgId,pkgType,startDate,"99999999000000");
            allMapRepository.saveAndFlush(allMap);
            double t2=System.currentTimeMillis();
            return "成功订购套餐"+pkgId;
        }
    }

    /**
     * 用户退订套餐
     * @param uid
     * @param pkgType
     * @param pkgId
     * @param currentDate
     */
    @Override
    public String userRemovePackage(long uid, int pkgType, String pkgId, String currentDate) {
        String endDate = dateUtil.getNextMonthStart(currentDate);//下月失效
        allMapRepository.removeUserPackage(uid,endDate,pkgId,pkgType);
        return "成功取消套餐"+pkgId;
    }

    @Override
    public ArrayList<String> searchAllPackages(long uid, int type, String currentDate) {
        return null;
    }

    //查询现在有效的通话套餐
    @Override
    public ArrayList<String> searchCurrentCallPackages(long uid, String currentDate) {
        ArrayList<String> result  = new ArrayList<String>();
        List<AllMap> packageMap = allMapRepository.findUserCurrentPackage(uid,currentDate,1);
        for(AllMap m : packageMap){
            CallPackage callPackage = callPackageRepository.getCallPackageByPkgId(m.getPkgId());
            result.add(callPackage.toString() + "      本套餐开始时间: "+dateUtil.getDateMonthBeginString(m.getStartDate()));
        }

        return result;
    }

    //查询现在有效的短信套餐
    @Override
    public ArrayList<String> searchCurrentMessagePackages(long uid, String currentDate) {
        ArrayList<String> result  = new ArrayList<String>();
        List<AllMap> packageMap = allMapRepository.findUserCurrentPackage(uid,currentDate,2);
        for(AllMap m : packageMap){
            MessagePackage messagePackage = messagePackageRepository.getMessagePackageByPkgId(m.getPkgId());
            result.add(messagePackage.toString() + "      本套餐开始时间: "+dateUtil.getDateMonthBeginString(m.getStartDate()));
        }

        return result;
    }

    //查询现在有效的本地流量套餐
    @Override
    public ArrayList<String> searchCurrentLocalPackages(long uid, String currentDate) {
        ArrayList<String> result  = new ArrayList<String>();
        List<AllMap> packageMap = allMapRepository.findUserCurrentPackage(uid,currentDate,3);
        for(AllMap m : packageMap){
            LocalSurfPackage localPackage = localSurfPackageRepository.getLocalSurfPackageByPkgId(m.getPkgId());
            result.add(localPackage.toString() + "      本套餐开始时间: "+dateUtil.getDateMonthBeginString(m.getStartDate()));
        }

        return result;
    }

    //查询现在有效的国内流量套餐
    @Override
    public ArrayList<String> searchCurrentDomesticPackages(long uid, String currentDate) {
        ArrayList<String> result  = new ArrayList<String>();
        List<AllMap> packageMap = allMapRepository.findUserCurrentPackage(uid,currentDate,4);
        for(AllMap m : packageMap){
            DomesticSurfPackage domesticPackage = domesticSurfPackageRepository.getDomesticSurfPackageByPkgId(m.getPkgId());
            result.add(domesticPackage.toString() + "      本套餐开始时间: "+dateUtil.getDateMonthBeginString(m.getStartDate()));
        }

        return result;
    }

    /**
     * 查询某种记录
     * @param uid
     * @param type
     * @param date
     * @return
     */
    @Override
    public ArrayList<String> searchRecords(long uid, int type, String date) {
        if(type == 1){
            return this.searchCallRecords(uid,date);
        }
        else if(type == 2){
            return this.searchMessageRecords(uid,date);
        }
        else if(type == 3){
            return this.searchLocalRecords(uid,date);
        }
        else {
            return this.searchDomesticRecords(uid,date);
        }
    }


    /**
     * 返回消费明细
     * @param uid
     * @param date
     * @return
     */
    @Override
    public String searchCost(long uid, String date) {
        String resultString = "\n*********套餐内消费*********\n";
        List<AllMap> callPackageMap = allMapRepository.findUserCurrentPackage(uid,date,1);
        List<AllMap> messagePackageMap = allMapRepository.findUserCurrentPackage(uid,date,2);
        List<AllMap> localPackageMap = allMapRepository.findUserCurrentPackage(uid,date,3);
        List<AllMap> domesticPackageMap = allMapRepository.findUserCurrentPackage(uid,date,4);
        double result1p = 0.0;
        double result2p = 0.0;
        double result3p = 0.0;
        double result4p = 0.0;

        //
        resultString += "****通话套餐****\n";
        for(AllMap m : callPackageMap){
            CallPackage callPackage = callPackageRepository.getCallPackageByPkgId(m.getPkgId());
            resultString += callPackage.toString()+"\n";
            result1p += callPackage.getCost();
        }
        resultString += "通话套餐总消费:"+result1p+"元\n\n";

        resultString += "****短信套餐****\n";
        for(AllMap m : messagePackageMap){
            MessagePackage messagePackage = messagePackageRepository.getMessagePackageByPkgId(m.getPkgId());
            resultString += messagePackage.toString()+"\n";
            result2p += messagePackage.getCost();
        }
        resultString += "短信套餐总消费:"+result2p+"元\n\n";

        resultString += "****本地流量套餐****\n";
        for(AllMap m : localPackageMap){
            LocalSurfPackage localSurfPackage = localSurfPackageRepository.getLocalSurfPackageByPkgId(m.getPkgId());
            resultString += localSurfPackage.toString()+"\n";
            result3p += localSurfPackage.getCost();
        }
        resultString += "本地流量套餐总消费:"+result3p+"元\n\n";

        resultString += "****国内流量套餐****\n";
        for(AllMap m : domesticPackageMap){
            DomesticSurfPackage domesticSurfPackage = domesticSurfPackageRepository.getDomesticSurfPackageByPkgId(m.getPkgId());
            resultString += domesticSurfPackage.toString()+"\n";
            result4p += domesticSurfPackage.getCost();
        }
        resultString += "国内流量套餐总消费:"+result4p+"元\n\n";

        List<Double> callCost = callRecordRepository.getTotalCost(dateUtil.getThisMonthStart(date),dateUtil.getNextMonthStart(date),uid);
        List<Double> messageCost = new ArrayList<Double>();
        List<Double> localCost = new ArrayList<Double>();
        List<Double> domesticCost = new ArrayList<Double>();
        double result1 = 0.0;
        double result2 = 0.0;
        double result3 = 0.0;
        double result4 = 0.0;

        for(double d : callCost){
            result1+=d;
        }

        for(double d : messageCost){
            result2+=d;
        }
        for(double d : localCost){
            result3+=d;
        }
        for(double d : domesticCost){
            result4+=d;
        }

        double total = result1+result2+result3+result4+result1p+result2p+result3p+result4p;

        resultString = resultString + "*********套餐外消费*********\n通话消费: "+result1+"元\n"+"短信消费: "+result2+"元\n"+"本地流量消费: "+result3+"元\n"+"国内流量消费: "+result4+"元\n";
        resultString = resultString + "套餐外总消费: "+(result1+result2+result3+result4)+"元\n\n";
        resultString = resultString + "****************\n当月总消费: "+ total + "元";

        return resultString;
    }

    //获得本月通话时长
    private double getTotalCallTime(long uid, String date){
        double result = 0.0;
        String beginTime = dateUtil.getThisMonthStart(date);
        String endTime = dateUtil.getNextMonthStart(date);
        List<Double> list = callRecordRepository.getCallTime(beginTime,endTime,uid);
        for(double time : list){
            result += time;
        }
        return result;
    }

    //获得本月短信条数
    private int getTotalMessageNumber(long uid, String date){
        String beginTime = dateUtil.getThisMonthStart(date);
        String endTime = dateUtil.getNextMonthStart(date);
        List<MessageRecord> list = messageRecordRepository.getMessageRecord(beginTime,endTime,uid);
        return list.size();
    }

    //获得本月本地流量总数
    private double getTotalLocalAmount(long uid, String date){
        double result = 0.0;
        String beginTime = dateUtil.getThisMonthStart(date);
        String endTime = dateUtil.getNextMonthStart(date);
        List<Double> list = localSurfRecordRepository.getLocalSurfAmount(beginTime,endTime,uid);
        for(double time : list){
            result += time;
        }
        return result;
    }

    //获得本月国内流量总数
    private double getTotalDomesticAmount(long uid, String date){
        double result = 0.0;
        String beginTime = dateUtil.getThisMonthStart(date);
        String endTime = dateUtil.getNextMonthStart(date);
        List<Double> list = domesticSurfRecordRepository.getDomesticSurfAmount(beginTime,endTime,uid);
        for(double time : list){
            result += time;
        }
        return result;
    }


    //查询本月通话记录
    private ArrayList<String> searchCallRecords(long uid, String date){
        ArrayList<String> result = new ArrayList<String>();
        List<CallRecord> records = callRecordRepository.getCallRecord(dateUtil.getThisMonthStart(date),dateUtil.getNextMonthStart(date),uid);
        for(CallRecord c : records){
            String s = c.toString();
            result.add(s);
        }
        return result;
    }

    //查询本月短信记录
    private ArrayList<String> searchMessageRecords(long uid,String date){
        ArrayList<String> result = new ArrayList<String>();
        List<MessageRecord> records = messageRecordRepository.getMessageRecord(dateUtil.getThisMonthStart(date),dateUtil.getNextMonthStart(date),uid);
        for(MessageRecord c : records){
            String s = c.toString();
            result.add(s);
        }
        return result;
    }

    //查询本月本地流量记录
    private ArrayList<String> searchLocalRecords(long uid,String date){
        ArrayList<String> result = new ArrayList<String>();
        List<LocalSurfRecord> records = localSurfRecordRepository.getLocalSurfRecord(dateUtil.getThisMonthStart(date),dateUtil.getNextMonthStart(date),uid);
        for(LocalSurfRecord c : records){
            String s = c.toString();
            result.add(s);
        }
        return result;
    }

    //查询本月国内流量记录
    private ArrayList<String> searchDomesticRecords(long uid,String date){
        ArrayList<String> result = new ArrayList<String>();
        List<DomesticSurfRecord> records = domesticSurfRecordRepository.getDomesticSurfRecord(dateUtil.getThisMonthStart(date),dateUtil.getNextMonthStart(date),uid);
        for(DomesticSurfRecord c : records){
            String s = c.toString();
            result.add(s);
        }
        return result;
    }
}
