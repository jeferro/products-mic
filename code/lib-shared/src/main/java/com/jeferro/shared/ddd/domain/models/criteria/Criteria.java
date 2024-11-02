package com.jeferro.shared.ddd.domain.models.criteria;

import com.jeferro.shared.ddd.domain.models.value_objects.ValueObject;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public abstract class Criteria extends ValueObject {

  private Integer pageNumber;

  private Integer pageSize;

  public Criteria(Integer pageNumber, Integer pageSize) {
	setPageNumber(pageNumber);
	setPageSize(pageSize);
  }

  public boolean isNotPageable() {
	return pageNumber == null
		|| pageSize == null;
  }

  private void setPageNumber(Integer pageNumber) {
	if (pageNumber == null) {
	  return;
	}

	ValueValidationUtils.isZeroOrPositive(pageNumber, "Page number");
	this.pageNumber = pageNumber;
  }

  private void setPageSize(Integer pageSize) {
	if (pageSize == null) {
	  return;
	}

	ValueValidationUtils.isZeroOrPositive(pageSize, "Page size");
	this.pageSize = pageSize;
  }
}
