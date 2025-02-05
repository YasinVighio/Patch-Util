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

    public static String getLastFolderFromPath(String path) {
        String retVal = "";
        try {
            String[] pathParts = path.split("\\\\");
            retVal = pathParts[pathParts.length - 1];
        } catch (Exception e){
            AppLogger.logSevere("Error in Util.getLastFolderFromPath", e);
        }
        return retVal;
    }

    public static String getFirstFolderFromPatchFilePath(String path) {
        String retVal = "";
        try {
            String[] pathParts = path.split("\\\\");
            retVal = pathParts[0];
        } catch (Exception e){
            AppLogger.logSevere("Error in Util.getFirstFolderFromPatchFilePath", e);
        }
        return retVal;
    }

    public static String getPatchFilePathExcludingRootAndFileName(String path) {
        String retVal = "";
        try {
            retVal = path.substring(path.indexOf("\\") + 1, path.lastIndexOf("\\"));
        } catch (Exception e){
            AppLogger.logSevere("Error in Util.getPatchFilePathExcludingRootAndFileName", e);
        }
        return retVal;
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

    public static String getFileExtension(String fileName){
        String fileExt = "";
        try {
            if (areStringsValid(fileName) && fileName.contains(".")) {
                fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
            }
        } catch (Exception e){
            AppLogger.logSevere("Error in Util.getFileExtension", e);
        }
        return fileExt;
    }

    public static String removeDriveLetterFromPath(String path){
        String newPath = path;
        try {
            if(Util.areStringsValid(path) && path.length()>=3){
                newPath = path.substring(3);
            }
        } catch (Exception e){
            AppLogger.logSevere("Error in Util.removeDriveLetterFromPath", e);
        }
        return newPath;
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
