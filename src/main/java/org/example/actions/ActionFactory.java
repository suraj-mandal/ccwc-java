package org.example.actions;

import org.example.constants.ActionConstants;
import org.example.models.ActionMetadata;

public class ActionFactory {
    public static Actionable generateAction(ActionMetadata actionMetadata) {
        var inputStream = actionMetadata.inputStream();
        var fileName = actionMetadata.fileName();
        return switch (actionMetadata.action()) {
            case ActionConstants.BYTES -> new BytesCountAction(inputStream, fileName);
            case ActionConstants.CHARS -> new CharacterCountAction(inputStream, fileName);
            case ActionConstants.LINES -> new LineCountAction(inputStream, fileName);
            case ActionConstants.WORDS -> new WordCountAction(inputStream, fileName);
            default -> new DefaultAction(inputStream, fileName);
        };
    }
}
