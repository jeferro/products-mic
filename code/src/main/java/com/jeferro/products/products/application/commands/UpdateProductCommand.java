package com.jeferro.products.products.application.commands;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.application.commands.Command;
import com.jeferro.products.shared.application.commands.CommandValidationException;
import com.jeferro.products.shared.domain.models.auth.Auth;
import org.apache.commons.lang3.StringUtils;

public class UpdateProductCommand extends Command<Product> {

    private final ProductId productId;

    private final String name;

    public UpdateProductCommand(Auth auth, ProductId productId, String name) {
        super(auth);

        if (productId == null) {
            throw CommandValidationException.ofMessage("Product identifier is null");
        }

        if (StringUtils.isBlank(name)) {
            throw CommandValidationException.ofMessage("Name is blank");
        }

        this.productId = productId;
        this.name = name;
    }

    public ProductId getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }
}
