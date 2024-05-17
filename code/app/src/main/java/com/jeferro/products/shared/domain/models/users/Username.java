package com.jeferro.products.shared.domain.models.users;

import com.jeferro.products.shared.domain.models.aggregates.Identifier;

public class Username extends Identifier<String> {

    public Username(String value) {
        super(value);
    }
}
