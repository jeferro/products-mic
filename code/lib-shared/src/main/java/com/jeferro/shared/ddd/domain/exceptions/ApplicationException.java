package com.jeferro.shared.ddd.domain.exceptions;

import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;
import org.apache.commons.lang3.StringUtils;

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
        if(StringUtils.isEmpty(code)){
            throw ValueValidationException.createOfMessage("Code is null or empty");
        }

        this.code = code;
    }

    private void setTitle(String title) {
        if(StringUtils.isEmpty(title)){
            throw ValueValidationException.createOfMessage("Title is null or empty");
        }

        this.title = title;
    }
}
