package com.nitro.common.logger;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;

import java.util.Date;

public class NitroLogger implements INitroLogger {

    private static Ansi ANSI = new Ansi();
    private static Date LAST_TIMESTAMP = new Date();

    private String name;
    private String description;
    private boolean print;

    public NitroLogger(String name, String description) {
        this.name = name;
        this.description = description;
        this.print = true;
    }

    public NitroLogger(String name) {
        this(name, null);
    }

    public NitroLogger() {
        this(null, null);
    }

    public static void install() {
        AnsiConsole.systemInstall();
    }

    public static void uninstall() {
        AnsiConsole.systemUninstall();
    }

    public void log(String message) {
        this.printMessage(message, Color.GREEN);
    }

    public void error(String message) {
        this.printMessage(message, Color.RED);
    }

    public void warn(String message) {
        this.printMessage(message, Color.YELLOW);
    }

    private void printMessage(String message, Color color) {
        if(!this.print) return;

        printMessage(message, color, this.name, this.description);
    }

    public static void printMessage(String message, Color color, String name, String description) {
        ANSI.fg(color).a("[Nitro] " + new Date().toString() + " ");

        if(name != null) ANSI.fg(Color.CYAN).a("[" + name + "] ");

        if(description != null) ANSI.fg(Color.WHITE).a("[" + description + "] ");

        if(message != null) ANSI.fg(color).a(message);

        printTimestamp();

        System.out.println(ANSI);

        ANSI = new Ansi();
    }

    public static void printLog(String message) {
        printMessage(message, Color.WHITE, null, null);
    }

    public static void printError(String message) {
        printMessage(message, Color.RED, null, null);
    }

    public static void printWarn(String message) {
        printMessage(message, Color.YELLOW, null, null);
    }

    private static void printTimestamp() {
        Date now = new Date();

        ANSI.fg(Color.WHITE).a("");

        LAST_TIMESTAMP = now;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrint(boolean flag) {
        this.print = flag;
    }
}
