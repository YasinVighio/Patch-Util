package org.patcher.utility;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Util {
    public static boolean areStringsValid(String... str) {
        if (str == null) {
            return false;
        }
        for (String s : str) {
            if (s == null || s.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isListModelNotEmpty(DefaultListModel<?> objs){
        return objs!=null && !objs.isEmpty();
    }
    public static boolean isListNotEmpty(List<?> objs){
        return objs!=null && !objs.isEmpty();
    }

    public static List<String> getStrListFromString(String s, String separator){
        List<String> stringList = new ArrayList<>();
        try {
            if (areStringsValid(s, separator) && s.contains(separator)) {
                stringList = Arrays.stream(s.split(separator))
                        .map(String::trim)
                        .collect(Collectors.toList());
            }
        } catch (Exception e){
            AppLogger.logSevere("Error in Util.getStrListFromString", e);
        }
        return stringList;
    }

    public static List<String> getPlaceHoldersInString(String s){
        List<String> placeholders = new ArrayList<>();
        try {
            if (areStringsValid(s)) {
                Pattern pattern = Pattern.compile("\\$\\{([^}]+)\\}");
                Matcher matcher = pattern.matcher(s);
                while (matcher.find()) {
                    placeholders.add(matcher.group(1));
                }
            }
        } catch (Exception e){
            AppLogger.logSevere("Error in Util.getPlaceHoldersInString", e);
        }
        return placeholders;
    }

    public static String replacePlaceholder(String str, String placeholderName, String value){
        String formattedString = str;
        try {
            if (areStringsValid(str, placeholderName, value)) {
                placeholderName = String.join("", "${", placeholderName, "}");
                formattedString = formattedString.replace(placeholderName, value);
            }
        } catch (Exception e){
            AppLogger.logSevere("Error in Util.replacePlaceholder", e);
        }
        return formattedString;
    }

}
