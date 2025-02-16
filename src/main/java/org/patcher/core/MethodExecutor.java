package org.patcher.core;

import org.patcher.dto.viewmodels.ActionDTO;
import org.patcher.utility.AppLogger;
import org.patcher.utility.Constants;
import org.patcher.utility.DateUtil;
import org.patcher.utility.Util;

public class MethodExecutor {
    private static final String TODAY_DATE_METHOD_NAME = "get_today_date";

    private static final String GET_PATCH_NAME_METHOD_NAME = "get_patch_name";

    public static ActionDTO executeMethod(String exposedMethod){
        ActionDTO actionDTO = new ActionDTO();
        try {
            if (Util.areStringsValid(exposedMethod)) {
                return exposedMethod.contains(Constants.STRING_SEPARATOR.COMMA.getValue()) ?
                        executeMethodWithArgs(exposedMethod) : executeMethodWithoutArgs(exposedMethod);
            }
            actionDTO.setIsSuccessful(false);
            actionDTO.setMessage("Method Not Found");
        } catch (Exception e) {
            actionDTO.setIsSuccessful(false);
            actionDTO.setMessage("Can not execute method, check logs for more details");
            AppLogger.logSevere("Error in MethodExecutor.executeMethod()", e);
        }
        return actionDTO;
    }

    private static ActionDTO executeMethodWithArgs(String exposedMethod){
        ActionDTO actionDTO = new ActionDTO();
        String[] methodInfo = exposedMethod.split(Constants.STRING_SEPARATOR.COMMA.getValue());
        switch (methodInfo[0]) {
            case TODAY_DATE_METHOD_NAME:
                actionDTO.setData(DateUtil.getCurrentDateInFormat(methodInfo[1]));
                actionDTO.setIsSuccessful(true);
                break;
        }
        return actionDTO;
    }

    private static ActionDTO executeMethodWithoutArgs(String exposedMethod){
        ActionDTO actionDTO = new ActionDTO();
        switch (exposedMethod) {
            case GET_PATCH_NAME_METHOD_NAME:
                actionDTO.setData(AppContext.createdPatchName);
                actionDTO.setIsSuccessful(true);
                break;
        }
        return actionDTO;
    }
}
