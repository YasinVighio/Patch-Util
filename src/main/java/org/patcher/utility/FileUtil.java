package org.patcher.utility;

import java.io.File;

public class FileUtil {
    public static String getLastFolderFromPath(String path) {
        String retVal = "";
        try {
            String[] pathParts = path.split(Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH_ESCAPE.getValue());
            retVal = pathParts[pathParts.length - 1];
        } catch (Exception e){
            AppLogger.logSevere("Error in FileUtil.getLastFolderFromPath", e);
        }
        return retVal;
    }

    public static String getFirstFolderFromPatchFilePath(String path) {
        String retVal = "";
        try {
            String[] pathParts = path.split(Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH_ESCAPE.getValue());
            retVal = pathParts[0];
        } catch (Exception e){
            AppLogger.logSevere("Error in FileUtil.getFirstFolderFromPatchFilePath", e);
        }
        return retVal;
    }

    public static String getPathExcludingLastFileOrFolder(String path) {
        String retVal = path;
        try {
            if(Util.areStringsValid(path)) {
                path = makePathCompatible(path, true);

                retVal = path.substring(0, path.lastIndexOf(Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH.getValue()));
            }
        } catch (Exception e){
            AppLogger.logSevere("Error in FileUtil.getPatchFilePathExcludingRootAndFileName", e);
        }
        return retVal;
    }

    public static String getPathExcludingFirstFolder(String path) {
        String retVal = "";
        try {
            String driveLetter = "";
            if(Util.areStringsValid(path)){
                if(pathContainsDriveLetter(path)){
                    driveLetter = path.substring(0, 3);
                }
            }
            retVal = driveLetter + path.substring(path.indexOf(Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH.getValue()) + 1);
        } catch (Exception e){
            AppLogger.logSevere("Error in FileUtil.getPathExcludingFirstFolder", e);
        }
        return retVal;
    }

    public static boolean pathContainsDriveLetter(String path) {
        return ((Util.areStringsValid(path) && path.length() >= 2) && (path.charAt(1) == ':' && ((path.charAt(2) == '\\')
                || (path.charAt(2) == '/'))));
    }

    public static String getFileExtension(String fileName){
        String fileExt = "";
        try {
            if (Util.areStringsValid(fileName) && fileName.contains(".")) {
                fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
            }
        } catch (Exception e){
            AppLogger.logSevere("Error in FileUtil.getFileExtension", e);
        }
        return fileExt;
    }

    public static String removeDriveLetterFromPath(String path){
        String newPath = path;
        try {
            if(pathContainsDriveLetter(path)){
                newPath = path.substring(3);
            }
        } catch (Exception e){
            AppLogger.logSevere("Error in FileUtil.removeDriveLetterFromPath", e);
        }
        return newPath;
    }

    public static String makePathCompatible(String path, boolean backSlash) {
        String retVal = path;
        try {
            if(Util.areStringsValid(path)) {
                if(backSlash) {
                    retVal = path.replace(Constants.STRING_SEPARATOR.PATH_SEPARATOR_FOWARD_SLASH.getValue(),
                            Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH.getValue());
                } else {
                    retVal = path.replace(Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH.getValue(),
                            Constants.STRING_SEPARATOR.PATH_SEPARATOR_FOWARD_SLASH.getValue());
                }
            }
        } catch (Exception e){
            AppLogger.logSevere("Error in FileUtil.makePathCompatible", e);
        }
        return retVal;
    }

    public static String[] breakPath(String path){
        String[] retVal = new String[1];
        retVal[0] = path;
        try{
            if(Util.areStringsValid(path) && path.contains(Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH.getValue())) {
                retVal = path.split(Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH_ESCAPE.getValue());
            }
        } catch (Exception e) {
            AppLogger.logSevere("Error in FileUtil.breakPath", e);
        }
        return retVal;
    }

    public static String joinPath(String path, CharSequence pathValues){
        String retVal = path;
        try{
            retVal = String.join(Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH.getValue(), path, pathValues);
            retVal = makePathCompatible(retVal, true);
        } catch (Exception e) {
            AppLogger.logSevere("Error in FileUtil.joinPath", e);
        }
        return retVal;
    }

    public static void deleteFolder(File folder) {
        try {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        deleteFolder(f);
                    } else {
                        f.delete();
                    }
                }
            }
            folder.delete();
        } catch (Exception e) {
            AppLogger.logSevere("Error in FileUtil.deleteFolder", e);
            throw e;
        }
    }
}
