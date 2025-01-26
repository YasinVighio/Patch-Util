package org.patcher;

import org.patcher.screens.AppScreen;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
       initApp();
    }

    public static void initApp(){
        AppScreen appScreen = new AppScreen();
        appScreen.setTitle("Patch Utils v1.0");
        appScreen.setResizable(false);
        appScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appScreen.setVisible(true);
    }
}