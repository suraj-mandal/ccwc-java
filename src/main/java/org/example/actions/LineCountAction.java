package org.example.actions;

import java.io.InputStream;

public class LineCountAction implements Actionable {
    private final InputStream inputStream;
    private final String fileName;

    public LineCountAction(InputStream inputStream, String name) {
        this.inputStream = inputStream;
        this.fileName = name;
    }

    @Override
    public void action() {
        try {
            StringBuilder characterBuilder = new StringBuilder();
            int currentByte;
            while ((currentByte = inputStream.read()) != -1) {
                characterBuilder.append((char) currentByte);
            }
            long totalLines = characterBuilder.toString().chars().filter(c -> c == '\n').count();
            System.out.println(totalLines + "\t" + fileName);
        } catch (Exception e) {
            System.out.println("Could not read the contents from the input stream");
        }
    }
}
