package org.patcher.core;

import org.patcher.dto.models.FontFamilies;
import org.patcher.dto.viewmodels.FontFamilyDTO;

public class AppContext {
    public static String appFullName = "";
    public static String createdPatchName = "";
    public static FontFamilyDTO newFontFamily = null;
    public static FontFamilies currentFontFamilies = new FontFamilies();

    static {
        appFullName = PropManager.getAppName() + " " + PropManager.getAppVer();
    }
}
