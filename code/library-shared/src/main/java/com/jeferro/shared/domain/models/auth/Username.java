package com.jeferro.shared.domain.models.auth;

import com.jeferro.shared.domain.models.aggregates.Identifier;

public class Username extends Identifier<String> {

    public Username(String value) {
        super(value);
    }
}
