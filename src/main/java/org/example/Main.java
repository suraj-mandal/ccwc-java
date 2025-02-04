package org.example;


import org.example.actions.ActionFactory;
import org.example.actions.Actionable;
import org.example.models.ActionMetadata;
import org.example.parsers.CommandLineParser;

public class Main {
    public static void main(String[] args) {
        try {
            ActionMetadata parsedActionMetadata = CommandLineParser.parse(args);
            Actionable toolAction = ActionFactory.generateAction(parsedActionMetadata);
            toolAction.action();
        } catch (Exception e) {
            System.out.println("Error occurred when running the application");
            System.out.println(e.getMessage());
        }
    }
}