package com.ciwr.service;

import com.ciwr.global.utils.LocalDateUtils;

import java.util.Calendar;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        System.out.println(LocalDateUtils.getMaxDayOfMonth(2018, 1));
        System.out.println(LocalDateUtils.date2String(LocalDateUtils.getDateOfNextWeek(Calendar.THURSDAY)));
        System.out.println(LocalDateUtils.date2String(LocalDateUtils.getDateOfCurrentWeek(Calendar.MONDAY)));
        System.out.println(LocalDateUtils.getTime(new Date()));
    }
}
