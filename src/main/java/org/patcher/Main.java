package org.patcher;

import org.patcher.core.AppContext;
import org.patcher.core.PropManager;
import org.patcher.screens.AppScreen;
import org.patcher.utility.AppLogger;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
       AppLogger.initLogger();
       PropManager.initProperties();
       initApp();
    }

    public static void initApp(){
        AppScreen appScreen = new AppScreen();
        appScreen.setTitle(AppContext.appFullName);
        appScreen.setResizable(false);
        appScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appScreen.setVisible(true);
    }
}