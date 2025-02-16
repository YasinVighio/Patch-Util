package org.patcher.dto.viewmodels;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActionDTO {
    private String message;
    private Object data;
    public List<String> messageList = new ArrayList<>();
    private Boolean isSuccessful;
}
