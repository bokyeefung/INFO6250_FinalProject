/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.cbb.util.check;

import com.bokyeefung.webtools.cbb.model.exception.ParamIllegalException;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import lombok.NoArgsConstructor;

import java.util.function.Predicate;

@NoArgsConstructor
public final class ParamCheckUtil {

    public static <T> void checkParam(T param, String paramName, Predicate<T> checker) throws ServiceException {
        if (!checker.test(param)) {
            throw new ParamIllegalException(paramName);
        }
    }
}
