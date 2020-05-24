package com.boot;

import com.nitro.application.Application;

import java.util.Scanner;

public class NitroBoot {

    public static void main(String[] args) {
        Application.bootstrap(args);

        listenForConsoleInput();
    }

    private static void listenForConsoleInput() {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while(input != null) {
            System.out.println("Got input: " + input);

            input = scanner.nextLine();
        }
    }
}
