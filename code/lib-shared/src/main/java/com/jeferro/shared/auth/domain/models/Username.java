package com.jeferro.shared.auth.domain.models;

import com.jeferro.shared.ddd.domain.models.aggregates.Identifier;

public class Username extends Identifier<String> {

    public Username(String value) {
        super(value);
    }
}
