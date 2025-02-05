package org.patcher.dto;

import java.util.ArrayList;
import java.util.List;

public class ActionDTO {
    private String message;
    private Object data;
    public List<String> messageList = new ArrayList<>();
    private Boolean isSuccessful;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
