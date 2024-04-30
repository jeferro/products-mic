package com.jeferro.products.products.application;

import com.jeferro.products.shared.application.Command;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.products.domain.models.Products;

public class ListProductsCommand extends Command<Products> {

    public ListProductsCommand(Auth auth) {
        super(auth);
    }
}
