package org.patcher.utility;

import org.patcher.core.MethodExecutor;
import org.patcher.core.PropManager;
import org.patcher.dto.viewmodels.ActionDTO;

import java.util.List;

public class PropertyUtil {

    public static boolean isFileAllowed(String fileName, boolean isFont) {
        boolean isAllowed = false;
        List<String> allowedFiles = PropManager.getFileExtensionsAllowed(isFont);
        if(Util.isListNotEmpty(allowedFiles)){
            isAllowed = allowedFiles.contains(FileUtil.getFileExtension(fileName));
        }
        return isAllowed;
    }

    public static String fillPropertyPlaceholders(String property) {
        String propVal = property;
        try {
            List<String> msgPlaceHolders = Util.getPlaceHoldersInString(propVal);
            if (Util.isListNotEmpty(msgPlaceHolders)) {
                for (String str : msgPlaceHolders) {
                    String placeholderValue = Constants.PLACE_HOLDER_CONSTANTS.VALUE_NOT_FOUND.getValue();
                    if (isPlaceholderProperty(str)){
                        String propertyValue = PropManager.getPropertyValue(str);
                        if(Util.areStringsValid(propertyValue)) {
                            placeholderValue = propertyValue;
                        }
                    } else {
                        ActionDTO actionDTO = MethodExecutor.executeMethod(str);
                        if(actionDTO.getIsSuccessful()) {
                            placeholderValue = (String) actionDTO.getData();
                        }
                    }
                    propVal = Util.replacePlaceholder(propVal, str, placeholderValue);
                }
            }
        } catch (Exception e){
            AppLogger.logSevere("Error in PropertyUtil.fillPropertyPlaceholders", e);
        }
        return propVal;
    }

    public static boolean isPlaceholderProperty(String placeholder){
        return Util.areStringsValid(placeholder) && placeholder.startsWith(Constants.PLACE_HOLDER_CONSTANTS.PROPERTY_PREFIX.getValue());
    }
}
