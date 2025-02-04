package org.example.processors;

import org.example.metadata.FileMetadata;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Optional;

public class FileProcessor extends Processor {
    // let us process

    private final FileMetadata fileMetadata;
    private final String filePath;
    private final boolean isReadSuccess;

    private static FileProcessor processor = null;

    private FileProcessor(String filePath) {
        this.filePath = filePath;
        isReadSuccess = checkIfFileExists(filePath);
        fileMetadata = readFile(filePath).orElse(null);
    }

    private static boolean checkIfFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && !file.isDirectory();
    }

    private static long countCharactersWithDefaultEncoding(String filePath) {
        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath))) {
            long count = 0;
            while (inputStreamReader.read() != -1) {
                count++;
            }
            return count;
        } catch (IOException e) {
            System.out.println("Error while reading file " + filePath);
            return 0L;
        }
    }

    private static Optional<FileMetadata> readFile(String filePath) {
        StringBuilder builder = new StringBuilder();

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            long byteCount = 0;
            int currentByte;
            while ((currentByte = fileInputStream.read()) != -1) {
                byteCount++;
                char currentChar = (char) currentByte;
                builder.append(currentChar);
            }
            // updating the metadata
            return Optional.of(new FileMetadata(builder.toString(), byteCount, Charset.defaultCharset()));
        } catch (IOException e) {
            System.out.println("Error while reading file " + filePath);
        }
        return Optional.empty();
    }

    public static FileProcessor generateFileProcessor(String filePath) {
        if (processor == null) {
            processor = new FileProcessor(filePath);
        }
        return processor;
    }

    @Override
    public long getBytes() {
        if (fileMetadata == null) {
            return 0;
        }
        return fileMetadata.totalBytes();
    }

    @Override
    public long getLines() {
        if (fileMetadata == null) {
            return 0;
        }
        return fileMetadata.fileContents().chars().filter(c -> c == '\n').count();
    }

    @Override
    public long getWords() {
        return fileMetadata.fileContents().split("\\s+").length;
    }

    @Override
    public long getChars() {
        if (fileMetadata == null) {
            return 0L;
        }
        if (fileMetadata.encoding().newEncoder().maxBytesPerChar() > 1) {
            // the current encoding supports multi-byte encoding
            return countCharactersWithDefaultEncoding(filePath);
        }
        return getBytes();
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean isReadSuccess() {
        return isReadSuccess;
    }
}
