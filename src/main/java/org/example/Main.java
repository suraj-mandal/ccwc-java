package org.example;

import org.example.invokers.CommandLineInvoker;

public class Main {
    public static void main(String[] args) {
        // invoking the command line
        CommandLineInvoker.getInstance(args).invoke();
    }
}