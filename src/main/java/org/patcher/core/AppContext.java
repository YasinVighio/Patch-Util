package org.patcher.core;
public class AppContext {
    public static String appFullName = "";
    public static String createdPatchName = "";

    static {
        appFullName = PropManager.getAppName() + " " + PropManager.getAppVer();
    }
}
