package com.qsoft.OnlineDio.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 11/5/13
 * Time: 10:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConvertDate
{
    public static java.util.Date convertToDate(String dateString){
        DateFormat formatter ;
        java.util.Date date = null;
        formatter = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
        try
        {
            date = (java.util.Date)formatter.parse(dateString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return date;
    }
    public static long getDays(java.util.Date date1, java.util.Date date2){

        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        try {
            date1 = formatter.parse(date1.toString());
            calendar1.setTime(date1);
            date2 = formatter.parse(date2.toString());
            calendar2.setTime(date2);
        }
        catch (ParseException e)
        {
            System.out.println("Exception :"+e);
        }

        long milliseconds1 = calendar1.getTimeInMillis();
        long milliseconds2 = calendar2.getTimeInMillis();
        long diff = milliseconds2 - milliseconds1;

        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffDays;
    }
}
