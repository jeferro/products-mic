package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;

public class GetProductParams extends Params<Product> {

  private ProductCode productCode;

  public GetProductParams(ProductCode productCode) {
	super();

	setProductCode(productCode);
  }

  public ProductCode getProductCode() {
	return productCode;
  }

  private void setProductCode(ProductCode productCode) {
	ValueValidationUtils.isNotNull(productCode, "Product code");
	this.productCode = productCode;
  }
}
