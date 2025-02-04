package org.example.parsers;

import org.example.constants.CommonConstants;
import org.example.models.ActionMetadata;
import org.example.validators.ActionValidators;

import java.io.FileInputStream;
import java.io.IOException;

public class CommandLineParser {

    private static ActionMetadata processNoArguments() {
        return new ActionMetadata(System.in, CommonConstants.EMPTY_STRING, CommonConstants.EMPTY_STRING);
    }

    private static ActionMetadata processOneArgument(String argument) throws IllegalArgumentException, IOException {
        if (!ActionValidators.isAction(argument)) {
            // this is handling for the case where there is just the name of the file
            // which is provided.
            FileInputStream fileInputStream = new FileInputStream(argument);
            return new ActionMetadata(fileInputStream, argument, CommonConstants.EMPTY_STRING);
        }
        if (ActionValidators.isNotValidAction(argument)) {
            throw new IllegalArgumentException("Invalid action: " + argument);
        }
        // this is for the case when there is an stdin, with a single flag provided.
        return new ActionMetadata(System.in, CommonConstants.EMPTY_STRING, argument);
    }

    private static ActionMetadata processTwoArguments(String action, String fileName) throws IllegalArgumentException, IOException {
        if (ActionValidators.isNotValidAction(action)) {
            throw new IllegalArgumentException("Invalid action: " + action);
        }
        FileInputStream fileInputStream = new FileInputStream(fileName);
        return new ActionMetadata(fileInputStream, fileName, action);
    }

    // the purpose of this class is to just parse the arguments
    public static ActionMetadata parse(String[] args) throws IllegalArgumentException, IOException {
        return switch (args.length) {
            case 0 -> processNoArguments();
            case 1 -> processOneArgument(args[0]);
            case 2 -> processTwoArguments(args[0], args[1]);
            default -> throw new IllegalArgumentException("Invalid number of arguments provided.");
        };
    }
}
