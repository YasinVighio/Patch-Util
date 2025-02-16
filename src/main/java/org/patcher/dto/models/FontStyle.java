package org.patcher.dto.models;

import lombok.Data;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.ToString;
import org.patcher.xmlprocessor.CDataAdapter;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class FontStyle {

    @XmlElement(name = "ttf")
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String ttf;

    @XmlElement(name = "pdf")
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String pdf;
}
