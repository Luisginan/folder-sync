package com.aplikasishop.tools;

public class Logger {

    public static void logRed(Exception e) {
        System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
    }

    public static void log(String message) {
        System.out.print(message);
    }

    public static void logGreen(String message) {
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }

    public static void logYellow(String message) {
        System.out.println("\u001B[33m" + message + "\u001B[0m");
    }

    public static void logBlue(String message) {
        System.out.println("\u001B[34m" + message + "\u001B[0m");
    }
}