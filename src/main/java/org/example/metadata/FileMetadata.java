package org.example.metadata;

import java.nio.charset.Charset;

public record FileMetadata(String fileContents, long totalBytes, Charset encoding) {
}
