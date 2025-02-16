package org.patcher.dto.viewmodels;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FontFamilyDTO {
    private String fontFamilyName;
    public List<FontDTO> fontDTOList = new ArrayList<>();
    private Boolean isEmbedded;
}
