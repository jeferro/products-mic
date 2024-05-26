package com.jeferro.products.products.application.commands;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.application.commands.Command;
import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.auth.Auth;
import org.apache.commons.lang3.StringUtils;

public class UpdateProductCommand extends Command<Product> {

  private ProductId productId;

  private String name;

  public UpdateProductCommand(Auth auth, ProductId productId, String name) {
	super(auth);

	setProductId(productId);
	setName(name);
  }

  public ProductId getProductId() {
	return productId;
  }

  public String getName() {
	return name;
  }

  private void setProductId(ProductId productId) {
	if (productId == null) {
	  throw ValueValidationException.createOfMessage("Product identifier is null");
	}

	this.productId = productId;
  }

  private void setName(String name) {
	if (StringUtils.isBlank(name)) {
	  throw ValueValidationException.createOfMessage("Name is blank");
	}

	this.name = name;
  }
}
