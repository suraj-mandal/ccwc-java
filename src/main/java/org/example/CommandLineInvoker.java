package org.example;

import org.example.constants.ActionArgumentConstants;
import org.example.processors.FileProcessor;


public class CommandLineInvoker {
    private final String[] args;

    public CommandLineInvoker(String[] args) {
        this.args = args;
    }

    private void invokeLineCountForFile(FileProcessor fileProcessor) {
        long lineCount = fileProcessor.getLines();
        System.out.println(lineCount + "\t" + fileProcessor.getFilePath());
    }

    private void invokeByteCountForFile(FileProcessor fileProcessor) {
        long byteCount = fileProcessor.getBytes();
        System.out.println(byteCount + "\t" + fileProcessor.getFilePath());
    }

    private void invokeWordCountForFile(FileProcessor fileProcessor) {
        long wordCount = fileProcessor.getWords();
        System.out.println(wordCount + "\t" + fileProcessor.getFilePath());
    }

    private void invokeAllCountsForFile(FileProcessor fileProcessor) {
        long lineCount = fileProcessor.getLines();
        long byteCount = fileProcessor.getBytes();
        long wordCount = fileProcessor.getWords();

        System.out.println(lineCount + "\t" + byteCount + "\t" + wordCount + "\t" + fileProcessor.getFilePath());
    }

    private void invokeForFile(String textFileName, String actionArgument) {
        // invoke a file processor
        FileProcessor fileProcessor = FileProcessor.generateFileProcessor(textFileName);
        if (fileProcessor.isReadSuccess()) {
            if (actionArgument.startsWith("-")) {
                String action = actionArgument.substring(1);
                switch (action) {
                    case ActionArgumentConstants.LINES:
                        invokeLineCountForFile(fileProcessor);
                        break;
                    case ActionArgumentConstants.BYTES:
                        invokeByteCountForFile(fileProcessor);
                        break;
                    case ActionArgumentConstants.WORDS:
                        invokeWordCountForFile(fileProcessor);
                        break;
                    case ActionArgumentConstants.ALL:

                    default:
                        System.out.println("Unknown action type: " + actionArgument);
                        System.exit(1);
                }
            } else {
                System.out.println("Permissible Action types are [-w, -b, -l]");
                System.exit(1);
            }
        } else {
            System.out.println("Invalid file.");
            System.exit(1);
        }
    }

    public void invoke() {
        if (args.length == 2) {
            // this means that there are two attributes
            String actionArgument = args[0];
            String textFileName = args[1];
            invokeForFile(textFileName, actionArgument);
        } else if (args.length == 1) {
            String textFileName = args[0];
            invokeForFile(textFileName, ActionArgumentConstants.ALL);
        } else {
            System.out.println("TO BE IMPLEMENTED...");
        }
    }
}
