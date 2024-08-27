package com.jeferro.products.products.application.commands;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.shared.application.commands.Command;
import com.jeferro.products.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.auth.Auth;
import org.apache.commons.lang3.StringUtils;

public class CreateProductCommand extends Command<Product> {

  private String name;

  public CreateProductCommand(Auth auth, String name) {
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
