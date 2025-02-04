package org.example.actions;

import java.io.InputStream;

public class WordCountAction implements Actionable {
    private final InputStream inputStream;
    private final String fileName;

    public WordCountAction(InputStream inputStream, String fileName) {
        this.inputStream = inputStream;
        this.fileName = fileName;
    }

    @Override
    public void action() {
        try {
            StringBuilder characterBuilder = new StringBuilder();
            int currentByte;
            while ((currentByte = inputStream.read()) != -1) {
                characterBuilder.append((char) currentByte);
            }
            long totalWords = characterBuilder.toString().split("\\s+").length;
            System.out.println(totalWords + "\t" + fileName);
        } catch (Exception e) {
            System.out.println("Could not read the contents from the input stream");
        }
    }
}
