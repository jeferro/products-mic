package com.jeferro.shared.auth.domain.models;

import com.jeferro.shared.ddd.domain.models.aggregates.StringIdentifier;

public class Username extends StringIdentifier {

    public Username(String value) {
        super(value);
    }
}
