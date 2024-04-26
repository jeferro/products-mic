package com.jeferro.products.products.application.commands;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.shared.application.commands.Command;
import com.jeferro.products.shared.application.commands.CommandValidationException;
import com.jeferro.products.shared.domain.models.auth.Auth;
import org.apache.commons.lang3.StringUtils;

public class CreateProductCommand extends Command<Product> {

    private final String name;

    public CreateProductCommand(Auth auth, String name) {
        super(auth);

        if (StringUtils.isBlank(name)) {
            throw CommandValidationException.ofMessage("Name is blank");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
