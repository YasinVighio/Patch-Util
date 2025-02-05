package org.patcher.core;

import org.patcher.dto.ActionDTO;
import org.patcher.utility.Constants;
import org.patcher.utility.DateUtil;
import org.patcher.utility.Util;

public class MethodExecutor {
    private static final String TODAY_DATE_METHOD_NAME = "get_today_date";

    private static final String GET_PATCH_NAME_METHOD_NAME = "get_patch_name";

    public static ActionDTO executeMethod(String exposedMethod){
        ActionDTO actionDTO = new ActionDTO();
        if(Util.areStringsValid(exposedMethod)){
            if(exposedMethod.contains(Constants.STRING_SEPARATOR.COMMA.getValue())){
                String[] methodInfo = exposedMethod.split(Constants.STRING_SEPARATOR.COMMA.getValue());
                switch (methodInfo[0]) {
                    case TODAY_DATE_METHOD_NAME:
                        actionDTO.setData(DateUtil.getCurrentDateInFormat(methodInfo[1]));
                        actionDTO.setSuccessful(true);
                        return actionDTO;
                }
            } else {
                switch (exposedMethod) {
                    case GET_PATCH_NAME_METHOD_NAME:
                        actionDTO.setData(AppContext.createdPatchName);
                        actionDTO.setSuccessful(true);
                        return actionDTO;
                }
            }
        }
        actionDTO.setSuccessful(false);
        actionDTO.setMessage("Method Not Found");
        return actionDTO;
    }
}
