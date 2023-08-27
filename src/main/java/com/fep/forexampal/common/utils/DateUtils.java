package com.fep.forexampal.common.utils;

import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class DateUtils {
    private static final DateTimeFormatter FORMATTER_ONLY_DAY_NAME = DateTimeFormat.forPattern("EEEE");
    private static final DateTimeFormatter FORMATTER_MONTH_AS_NAME = DateTimeFormat.forPattern("dd MMMM");

    private final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    public static String getDayOfWeekAsName(Date date) {
        return new DateTime(date).toString(FORMATTER_ONLY_DAY_NAME.withLocale(CommonLocale.getTr()));
    }

    public static String getMonthAsName(Date date) {
        return new DateTime(date).toString(FORMATTER_MONTH_AS_NAME.withLocale(CommonLocale.getTr()));
    }

    public static Date getDateWithoutTime(Date date) {
        try {
            return DATE_FORMATTER.parse(DATE_FORMATTER.format(date));
        } catch (ParseException e) {
            return date;
        }
    }

}
