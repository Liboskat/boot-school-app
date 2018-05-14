package ru.kpfu.services;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

public class DateHelper {
    public static int getDifferenceBetweenCurrentYearAndAnother(int year) {
        Calendar then = Calendar.getInstance();
        then.setTime(new Date());
        then.set(YEAR, year);
        then.set(MONTH, SEPTEMBER);
        then.set(DAY_OF_MONTH, 1);
        Calendar now = Calendar.getInstance();
        then.setTime(new Date());
        int diff = now.get(YEAR) - then.get(YEAR);
        if (then.get(MONTH) > now.get(MONTH) || (then.get(MONTH) == now.get(MONTH) && then.get(DATE) > now.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static String getNameOfWeekday(int weekday) {
        switch (weekday) {
            case 1:
                return "Понедельник";
            case 2:
                return "Вторник";
            case 3:
                return "Среда";
            case 4:
                return "Четверг";
            case 5:
                return "Пятница";
            case 6:
                return "Суббота";
            case 7:
                return "Воскресенье";
            default:
                return "??";
        }
    }
}
