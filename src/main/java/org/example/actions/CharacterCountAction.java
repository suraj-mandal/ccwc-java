package org.example.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class CharacterCountAction implements Actionable {
    private final InputStream inputStream;
    private final String fileName;

    public CharacterCountAction(InputStream inputStream, String name) {
        this.inputStream = inputStream;
        this.fileName = name;
    }

    @Override
    public void action() {
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
            long characterCount = 0;
            while (inputStreamReader.read() != -1) {
                characterCount++;
            }
            System.out.println(characterCount + "\t" + fileName);
        } catch (IOException e) {
            System.out.println("Unable to read the input stream to get the character count");
        }
    }
}
