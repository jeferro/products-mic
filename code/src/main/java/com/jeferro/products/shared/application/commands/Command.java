package com.jeferro.products.shared.application.commands;

import com.jeferro.products.shared.domain.models.auth.Auth;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public abstract class Command<R> {

    private final Auth auth;

    protected Command(Auth auth) {
        if (auth == null) {
            throw CommandValidationException.ofMessage("Auth is null");
        }

        this.auth = auth;
    }

    public Auth getAuth() {
        return auth;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        return EqualsBuilder.reflectionEquals(
                this,
                other
        );
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
    }
}
