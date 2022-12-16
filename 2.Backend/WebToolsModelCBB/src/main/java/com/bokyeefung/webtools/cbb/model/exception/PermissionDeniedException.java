/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.cbb.model.exception;

public class PermissionDeniedException extends Exception {
    private static final String ERROR_INFO = "Permission denied";

    public PermissionDeniedException() {
        super(ERROR_INFO);
    }

    public PermissionDeniedException(String message) {
        super(ERROR_INFO + ": " + message);
    }

    public PermissionDeniedException(Exception exception) {
        super(ERROR_INFO, exception);
    }
}
