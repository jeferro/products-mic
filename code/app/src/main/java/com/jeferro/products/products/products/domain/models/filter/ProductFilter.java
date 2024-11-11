package com.jeferro.products.products.products.domain.models.filter;

import static com.jeferro.products.products.products.domain.models.filter.ProductFilterOrder.NAME;

import com.jeferro.shared.ddd.domain.models.filter.Filter;
import lombok.Getter;

@Getter
public class ProductFilter extends Filter<ProductFilterOrder> {

  private String name;

  public ProductFilter(Integer pageNumber, Integer pageSize, ProductFilterOrder order, Boolean ascending, String name) {
	super(pageNumber, pageSize, order, ascending);

	setName(name);
  }

  public static ProductFilter createEmpty() {
	return new ProductFilter(null, null, NAME, null, null);
  }

  public static ProductFilter searchName(String name) {
	return new ProductFilter(null, null, NAME, null, name);
  }

  public boolean hasName() {
	return name != null && !name.isBlank();
  }

  private void setName(String name) {
	this.name = name;
  }
}
