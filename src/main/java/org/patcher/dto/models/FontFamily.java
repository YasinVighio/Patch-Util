package org.patcher.dto.models;

import lombok.Data;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.ToString;
import org.patcher.xmlprocessor.CDataAdapter;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class FontFamily {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "normal")
    private FontStyle normal;

    @XmlElement(name = "bold")
    private FontStyle bold;

    @XmlElement(name = "italic")
    private FontStyle italic;

    @XmlElement(name = "boldItalic")
    private FontStyle boldItalic;

    @XmlElement(name = "pdfEmbedded")

    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String pdfEmbedded;

    @XmlElement(name="exportFonts")
    private String exportFonts = null;
}
