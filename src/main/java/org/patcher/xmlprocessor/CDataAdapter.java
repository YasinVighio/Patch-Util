package org.patcher.xmlprocessor;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CDataAdapter extends XmlAdapter<String, String> {

    @Override
    public String marshal(String arg0) {
        return "<![CDATA[" + arg0 + "]]>";
    }
    @Override
    public String unmarshal(String arg0) {
        return arg0;
    }

}