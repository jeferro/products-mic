package com.jeferro.shared.ddd.domain.exceptions;

import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public abstract class ApplicationException extends RuntimeException {

    private final String code;

    private final String title;

    protected ApplicationException(String code, String title, String message) {
        super(message);

        ValueValidationUtils.isNotBlank(code, "code", this);
        ValueValidationUtils.isNotBlank(title, "title", this);

        this.code = code;
        this.title = title;
    }
}
