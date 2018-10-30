package com.database.hw.util;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

    public String getThisMonthStart(String dateAndTime){
        String yearAndMonth = dateAndTime.substring(0,6);
        String newDate = yearAndMonth + "00000000";
        return newDate;
    }

    public String getNextMonthStart(String dateAndTime){
        String year = dateAndTime.substring(0,4);
        String month = dateAndTime.substring(4,6);
        int yearValue = Integer.parseInt(year);
        int monthValue = Integer.parseInt(month);
        if(monthValue < 12){
            monthValue += 1;
            if(monthValue < 10){
                month = "0"+monthValue;
            }
            else{
                month = monthValue+"";
            }
        }
        else{
            month = "01";
            yearValue += 1;
            year = yearValue+"";
        }

        return year + month + "00000000";
    }

    public String getDateMonthBeginString(String dateAndTime){
        String year = dateAndTime.substring(0,4);
        String month = dateAndTime.substring(4,6);

        return year+"/"+month+"/01";
    }

    public String getTimeString(String dateAndTime){
        String year = dateAndTime.substring(0,4);
        String month = dateAndTime.substring(4,6);
        String day = dateAndTime.substring(6,8);
        String hour = dateAndTime.substring(8,10);
        String minute = dateAndTime.substring(10,12);
        String second = dateAndTime.substring(12,14);

        return year+"/"+month+"/"+day+" "+hour+":"+minute+":"+second;
    }
}
