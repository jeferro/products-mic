package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.status.ProductStatus;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public class UpdateProductStatusParams extends Params<Product> {

  private ProductCode productCode;

  private ProductStatus status;

  public UpdateProductStatusParams(ProductCode productCode, ProductStatus status) {
	super();

	setProductCode(productCode);
	setStatus(status);
  }

  private void setProductCode(ProductCode productCode) {
	ValueValidationUtils.isNotNull(productCode, "productCode", this);
	this.productCode = productCode;
  }

  private void setStatus(ProductStatus status) {
	ValueValidationUtils.isNotNull(status, "status", this);
	this.status = status;
  }
}
