package com.jeferro.shared.ddd.domain.models.filter;

import com.jeferro.shared.ddd.domain.models.value_objects.ValueObject;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public abstract class Filter<Order> extends ValueObject {

  private static final int DEFAULT_PAGE_NUMBER = 1;

  private static final int DEFAULT_PAGE_SIZE = 10;

  private Integer pageNumber;

  private Integer pageSize;

  private Order order;

  private boolean ascending;

  public Filter(Integer pageNumber, Integer pageSize, Order order, Boolean ascending) {
	setPageNumber(pageNumber);
	setPageSize(pageSize);
	setOrder(order);
	setAscending(ascending);
  }

  private void setPageNumber(Integer pageNumber) {
	if (pageNumber == null) {
	  this.pageNumber = DEFAULT_PAGE_NUMBER;
	  return;
	}

	ValueValidationUtils.isZeroOrPositive(pageNumber, "pageNumber", this);
	this.pageNumber = pageNumber;
  }

  private void setPageSize(Integer pageSize) {
	if (pageSize == null) {
	  this.pageSize = DEFAULT_PAGE_SIZE;
	  return;
	}

	ValueValidationUtils.isZeroOrPositive(pageSize, "pageSize", this);
	this.pageSize = pageSize;
  }

  private void setOrder(Order order) {
	this.order = order;
  }

  private boolean hasOrder() {
	return order != null;
  }

  private void setAscending(Boolean ascending) {
	this.ascending = ascending != null ? ascending : true;
  }
}
