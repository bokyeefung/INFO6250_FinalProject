/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.cbb.model.exception;

public class UserNotLoginException extends PermissionDeniedException {
    private static final String ERROR_INFO = "User not logged in";

    public UserNotLoginException() {
        super(ERROR_INFO);
    }
}
