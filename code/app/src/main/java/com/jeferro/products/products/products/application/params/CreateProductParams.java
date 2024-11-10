package com.jeferro.products.products.products.application.params;

import com.jeferro.products.parametrics.domain.models.values.ParametricValueId;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import lombok.Getter;

@Getter
public class CreateProductParams extends Params<Product> {

  private ProductCode productCode;

  private ParametricValueId typeId;

  private LocalizedField name;

  public CreateProductParams(ProductCode productCode,
	  ParametricValueId typeId,
	  LocalizedField name) {
	super();

	setProductCode(productCode);
	setTypeId(typeId);
	setName(name);
  }

  private void setProductCode(ProductCode productCode) {
	ValueValidationUtils.isNotNull(productCode, "productCode", this);
	this.productCode = productCode;
  }

  public void setTypeId(ParametricValueId typeId) {
	ValueValidationUtils.isNotNull(typeId, "typeId", this);
	this.typeId = typeId;
  }

  private void setName(LocalizedField name) {
	ValueValidationUtils.isNotNull(name, "name", this);
	this.name = name;
  }
}
