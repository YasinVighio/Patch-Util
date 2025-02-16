package org.patcher.dto.viewmodels;

import lombok.Data;
import org.patcher.enums.FontTypeEnum;

@Data
public class FontDTO {
    private String pdfFontName;
    private String ttfFile;
    private FontTypeEnum type;
}
