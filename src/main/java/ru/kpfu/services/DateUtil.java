package ru.kpfu.services;


import java.util.Date;

public interface DateUtil {
    String STRING_DATE_TYPE_RUS = "dd.MM.yyyy";
    String STRING_DATE_TYPE_ISO = "yyyy-MM-dd";

    Date convertFromString(String stringType, String dateString);
    String convertCurrentDateToString(String stringType);
    int getDayOfWeek(Date date);
    int toWeek(Date date);
    int getMonth(Date date);
    int getMaximumWeek(Date date);
}
