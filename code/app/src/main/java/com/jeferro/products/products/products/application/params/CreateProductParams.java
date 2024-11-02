package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.product_types.ProductTypeId;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import lombok.Getter;

@Getter
public class CreateProductParams extends Params<Product> {

  private ProductCode productCode;

  private ProductTypeId typeId;

  private LocalizedField name;

  public CreateProductParams(ProductCode productCode,
	  ProductTypeId typeId,
	  LocalizedField name) {
	super();

	setProductCode(productCode);
	setTypeId(typeId);
	setName(name);
  }

  private void setProductCode(ProductCode productCode) {
	ValueValidationUtils.isNotNull(productCode, "Product code");
	this.productCode = productCode;
  }

  public void setTypeId(ProductTypeId typeId) {
	ValueValidationUtils.isNotNull(typeId, "Type id");
	this.typeId = typeId;
  }

  private void setName(LocalizedField name) {
	ValueValidationUtils.isNotNull(name, "Name");
	this.name = name;
  }
}
