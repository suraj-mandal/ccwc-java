package org.example.validators;

import org.example.constants.ActionConstants;

public class ActionValidators {

    public static boolean isAction(String action) {
        return action.startsWith("-");
    }

    public static boolean isNotValidAction(String action) {
        return !switch (action) {
            case ActionConstants.BYTES, ActionConstants.CHARS, ActionConstants.LINES, ActionConstants.WORDS -> true;
            default -> false;
        };
    }
}
