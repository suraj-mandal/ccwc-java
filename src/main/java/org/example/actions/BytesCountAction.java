package org.example.actions;

import java.io.InputStream;

public class BytesCountAction implements Actionable {

    private final InputStream inputStream;
    private final String fileName;

    public BytesCountAction(InputStream inputStream, String name) {
        this.inputStream = inputStream;
        this.fileName = name;
    }

    @Override
    public void action() {
        try {
            long bytesCount = 0L;
            while (inputStream.read() != -1) {
                bytesCount++;
            }
            System.out.println(bytesCount + "\t" + fileName);
        } catch (Exception e) {
            System.out.println("Could not read the contents from the input stream");
        }
    }
}
