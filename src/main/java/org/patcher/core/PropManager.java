package org.patcher.core;

import org.patcher.utility.AppLogger;
import org.patcher.utility.Constants;
import org.patcher.utility.PropertyUtil;
import org.patcher.utility.Util;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

public class PropManager {
    private static final Properties properties = new Properties();
    private static final String FILE_EXT_ALLOWED_PROPERTY = "fileExtensionsAllowed";
    private static final String PATCH_CREATED_SUCCESS_MSG = "patchCreateSuccessMessage";
    private static final String PATCHES_FOLDER_PROP = "patchesFolder";
    private static final String PATCH_DEFAULT_NAME_PROP = "patchDefaultName";
    private static final String APP_NAME_PROP = "appName";
    private static final String APP_VER_PROP = "appVersion";
    private static final String PROP_FILE_PATH = "configs/app.config";

    public static void initProperties() throws Exception{
        try (FileInputStream input = new FileInputStream(PROP_FILE_PATH)) {
            properties.load(input);
        } catch (Exception e) {
            AppLogger.logSevere("Error in PropManager.initProperties", e);
            throw e;
        }
    }

    public static List<String> getFileExtensionsAllowed(){
        return Util.getStrListFromString(getPropertyValue(FILE_EXT_ALLOWED_PROPERTY), ",");
    }

    public static String getPatchCreateSuccessMsg(){
        String successMsg = Constants.MESSAGES.DEFAULT_PATCH_CREATED_MESSAGE.getValue();
        try {
            String msgProp = getPropertyValue(PATCH_CREATED_SUCCESS_MSG);
            successMsg = PropertyUtil.fillPropertyPlaceholders(msgProp);
        } catch (Exception e){
            AppLogger.logSevere("Error in PropManager.getPatchCreateSuccessMsg", e);
        }
        return successMsg;
    }

    public static String getDefaultPatchesFolder(){
        return getPropertyValue(PATCHES_FOLDER_PROP);
    }

    public static String getPropertyValue(String propName){
        String propVal = "";
        try{
            if(Util.areStringsValid(propName)) {
                if(PropertyUtil.isPlaceholderProperty(propName)){
                    propName = propName.substring(propName.indexOf(Constants.STRING_SEPARATOR.DOT.getValue())+1);
                }
                propVal = properties.getProperty(propName);
            }
        } catch (Exception e){
            AppLogger.logSevere("Error in PropManager.getProperty", e);
        }
        return propVal;
    }

    public static String getPatchDefaultName(){
        String defaultName = "patch_";
        try {
            String msgProp = getPropertyValue(PATCH_DEFAULT_NAME_PROP);
            defaultName = PropertyUtil.fillPropertyPlaceholders(msgProp);
        } catch (Exception e){
            AppLogger.logSevere("Error in PropManager.getPatchDefaultName", e);
        }
        return defaultName;
    }

    public static String getAppName(){
        return getPropertyValue(APP_NAME_PROP);
    }

    public static String getAppVer(){
        return getPropertyValue(APP_VER_PROP);
    }
}
