package com.jeferro.products.products.application.params;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.auth.Auth;
import org.apache.commons.lang3.StringUtils;

public class CreateProductParams extends Params<Product> {

  private String name;

  public CreateProductParams(Auth auth, String name) {
	super(auth);

	setName(name);
  }

  public String getName() {
	return name;
  }

  private void setName(String name) {
	if (StringUtils.isBlank(name)) {
	  throw ValueValidationException.createOfMessage("Name is blank");
	}

	this.name = name;
  }
}
