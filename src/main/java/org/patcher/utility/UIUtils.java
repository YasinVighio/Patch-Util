package org.patcher.utility;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UIUtils {
    public static List<String> convertListModelToListStr(DefaultListModel<String> stringList){
        List<String> strings = new ArrayList<>();
        if(Util.isListModelNotEmpty(stringList)) {
            try {
                for (int i=0; i<stringList.size(); i++) {
                    strings.add(stringList.get(i));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return strings;
    }

    public static void removeZipFilesFromListModel(DefaultListModel<String> files){
        if(Util.isListModelNotEmpty(files)) {
            for (int i = 0; i < files.size(); i++) {
                if(files.get(i).endsWith(".zip")){
                    files.remove(i);
                }
            }
        }
    }

    public static String stringListToOutputAreaMsg(List<String> stringsList){
        StringBuilder sb = new StringBuilder();
        if(Util.isListNotEmpty(stringsList)){
            for(String str: stringsList){
                sb.append(str).append("<br>");
            }
        }
        return sb.toString();
    }

    public static String encloseTextInParagraph(String text, String fontName, String fontSize,
                                                String color, String heading, boolean isHeadingBold){
        String para = "<p style=\"font-family:'"+fontName+"'; font-size:'"+fontSize+"';";

        if(Util.areStringsValid(color)){
            para=para+"color: '"+color+"';";
        }
        para=para+"\">";
        if(Util.areStringsValid(heading)){
            if(isHeadingBold){
                para=para+"<b>"+heading+"</b><br>";
            } else {
                para = para+heading+"<br>";
            }
        }
        para=para+text;
        para=para+"</p>";
        return para;
    }
}
