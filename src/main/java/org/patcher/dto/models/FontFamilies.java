package org.patcher.dto.models;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "fontFamilies")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@ToString
public class FontFamilies {
    @XmlElement(name = "fontFamily")
    private List<FontFamily> fontFamilies;
}
