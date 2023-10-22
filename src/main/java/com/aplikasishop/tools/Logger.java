package com.aplikasishop.tools;

public class Logger {//function show verbose with red font color without Logger

    public static void logRed(Exception e) {
        System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
    }

    public static void logWhite(String message) {
        System.out.println("\u001B[37m" + message + "\u001B[0m");
    }//function show verbose with red green color without Logger

    public static void log(String message) {
        System.out.print(message);
    }//f
    public static void logGreen(String message) {
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }

    public static void logYellow(String message) {
        System.out.println("\u001B[33m" + message + "\u001B[0m");
    }//function show verbose with blue font color without Logger

    public static void logBlue(String message) {
        System.out.println("\u001B[34m" + message + "\u001B[0m");
    }
}