package ru.kpfu.services;

import org.joda.time.DateTime;
import org.joda.time.Weeks;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

@Service
public class DateUtilImpl implements DateUtil {
    @Override
    public int getMonth(Date date) {
        Calendar c = getCalendar(date);
        return c.get(MONTH);
    }

    @Override
    public String convertCurrentDateToString(String stringType) {
        DateFormat dateFormat = new SimpleDateFormat(stringType);
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public Date convertFromString(String stringType, String dateString) {
        DateFormat dateFormat = new SimpleDateFormat(stringType);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Серверная ошибка");
        }
    }

    @Override
    public int getDayOfWeek(Date date) {
        Calendar c = getCalendar(date);
        return getNormalDayOfWeek(c.get(DAY_OF_WEEK));
    }

    @Override
    public int getMaximumWeek() {
        Date date = new Date();
        Calendar start = Calendar.getInstance();
        start.set(MONTH, SEPTEMBER);
        start.set(DAY_OF_MONTH, 1);

        if(getMonth(date) < SEPTEMBER) {
            start.set(YEAR, start.get(YEAR) - 1);
        }

        Calendar end = Calendar.getInstance();
        end.set(MONTH, MAY);
        end.set(DAY_OF_MONTH, 31);
        end.set(YEAR, start.get(YEAR) + 1);

        DateTime dateTime1 = new DateTime(start.getTime());
        DateTime dateTime2 = new DateTime(end.getTime());
        return Weeks.weeksBetween(dateTime1, dateTime2).getWeeks() + 1;
    }

    @Override
    public int getCurrentWeek() {
        return toWeek(new Date());
    }

    @Override
    public int toWeek(Date date) {
        Calendar start = Calendar.getInstance();
        start.set(MONTH, SEPTEMBER);
        start.set(DAY_OF_MONTH, 1);
        start.set(DAY_OF_WEEK, start.getFirstDayOfWeek());

        if(getMonth(date) < SEPTEMBER) {
            start.set(YEAR, start.get(YEAR) - 1);
        }
        DateTime dateTime1 = new DateTime(start.getTime());
        DateTime dateTime2 = new DateTime(date);
        return Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
    }

    private int getNormalDayOfWeek(int dayOfWeekFromCalendar) {
        if(dayOfWeekFromCalendar == SUNDAY) {
            return 7;
        } else {
            return dayOfWeekFromCalendar - 1;
        }
    }

    private Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
