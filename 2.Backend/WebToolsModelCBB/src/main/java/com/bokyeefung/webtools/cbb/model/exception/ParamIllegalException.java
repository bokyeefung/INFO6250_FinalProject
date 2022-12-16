/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.cbb.model.exception;

public class ParamIllegalException extends ServiceException {
    public static final String ERROR_INFO = "Param Illegal: ";

    public ParamIllegalException(String paramName) {
        super(ERROR_INFO + paramName);
    }
}
