package com.jeferro.products.shared.domain.models.users;

import com.jeferro.products.shared.domain.models.identifiers.SimpleIdentifier;

public class Username extends SimpleIdentifier<String> {

    public Username(String value) {
        super(value);
    }
}
