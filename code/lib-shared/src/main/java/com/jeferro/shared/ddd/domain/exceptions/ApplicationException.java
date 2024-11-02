package com.jeferro.shared.ddd.domain.exceptions;

import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;

public abstract class ApplicationException extends RuntimeException {

    private String code;

    private String title;

    protected ApplicationException(String code, String title, String message) {
        super(message);

        setCode(code);
        setTitle(title);
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    private void setCode(String code) {
        ValueValidationUtils.isNotBlank(code, "Code");
        this.code = code;
    }

    private void setTitle(String title) {
        ValueValidationUtils.isNotBlank(title, "Title");
        this.title = title;
    }
}
