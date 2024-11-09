package com.jeferro.shared.ddd.domain.exceptions;

import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public abstract class ApplicationException extends RuntimeException {

    private String code;

    private String title;

    protected ApplicationException(String code, String title, String message) {
        super(message);

        setCode(code);
        setTitle(title);
    }

    private void setCode(String code) {
        ValueValidationUtils.isNotBlank(code, "code", this);
        this.code = code;
    }

    private void setTitle(String title) {
        ValueValidationUtils.isNotBlank(title, "title", this);
        this.title = title;
    }
}
