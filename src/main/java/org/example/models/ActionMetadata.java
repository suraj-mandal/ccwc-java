package org.example.models;

import java.io.InputStream;

public record ActionMetadata(InputStream inputStream, String fileName, String action) {
}
