package com.jeferro.products.products.application.params;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import org.apache.commons.lang3.StringUtils;

public class UpdateProductParams extends Params<Product> {

  private ProductId productId;

  private String name;

  public UpdateProductParams(ProductId productId, String name) {
	super();

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
