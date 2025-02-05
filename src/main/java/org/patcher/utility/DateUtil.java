package org.patcher.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String getFormattedDateInString(String format, LocalDateTime date){
        String dateStr = "";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            dateStr = date.format(formatter);
        } catch (Exception e){
            AppLogger.logSevere("Error in DateUtil.getFormattedDate", e);
        }
        return dateStr;
    }

    public static String getCurrentDateInFormat(String format){
        String dateStr = "";
        try {
            dateStr = getFormattedDateInString(format, LocalDateTime.now());
        } catch (Exception e){
            AppLogger.logSevere("Error in DateUtil.getCurrentDate", e);
        }
        return dateStr;
    }
}
