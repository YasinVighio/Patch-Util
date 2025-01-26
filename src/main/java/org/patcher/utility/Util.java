package org.patcher.utility;

import javax.swing.*;
import java.util.List;

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

    public static boolean matchStringNonStrict(String s1, String s2){
        if(areStringsValid(s1, s2)){
            return s1.toLowerCase().equals(s2);
        }
        return false;
    }

    public static String getLastFolderFromPath(String path) {
        String[] pathParts = path.split("\\\\");
        return pathParts[pathParts.length - 1];
    }

    public static String getFirstFolderFromPatchFilePath(String path) {
        String[] pathParts = path.split("\\\\");
        return pathParts[0];
    }

    public static String getPatchFilePathExcludingRootAndFileName(String path) {
        return path.substring(path.indexOf("\\") + 1, path.lastIndexOf("\\"));
    }

}
