package org.example.processors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class FileProcessor extends Processor {
    // let us process
    private String fileContents;
    private final String filePath;
    private boolean isReadSuccess;

    private static FileProcessor processor = null;

    private FileProcessor(String filePath) {
        this.filePath = filePath;
        this.processFileContents();
    }

    private static Optional<String> readFileUsingBufferedReader(String filePath) {
        StringBuilder fileContentBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                fileContentBuilder.append(line).append("\n");
            }

            return Optional.of(fileContentBuilder.toString());

        } catch (IOException e) {
            System.out.println("Error while reading the file: " + e.getMessage());
            return Optional.empty();
        }
    }


    private void processFileContents() {
        // processing the
        Optional<String> fileContentOptional = readFileUsingBufferedReader(filePath);
        if (fileContentOptional.isPresent()) {
            // then the things can be updated
            this.fileContents = fileContentOptional.get();
            isReadSuccess = true;
        } else {
            isReadSuccess = false;
        }
    }

    public static FileProcessor generateFileProcessor(String filePath) {
        if (processor == null) {
            processor = new FileProcessor(filePath);
        }
        return processor;
    }

    @Override
    public long getBytes() {
        return fileContents != null ? fileContents.getBytes().length : 0;
    }

    @Override
    public long getLines() {
        return fileContents.split("\n").length;
    }

    @Override
    public long getWords() {
        String[] lines = fileContents.split("\n");
        long totalWords = 0;
        for (String line : lines) {
            totalWords += line.split("\\s+").length;
        }
        return totalWords;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean isReadSuccess() {
        return isReadSuccess;
    }
}
