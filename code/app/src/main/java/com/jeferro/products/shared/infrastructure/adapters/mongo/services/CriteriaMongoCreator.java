package com.jeferro.products.shared.infrastructure.adapters.mongo.services;

import com.jeferro.products.shared.domain.models.criteria.Criteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;

public abstract class CriteriaMongoCreator<C extends Criteria> {

  public Query create(C criteria) {
	Query query = createWithoutPagination(criteria);

	if (criteria.isNotPageable()) {
	  return query;
	}

	int pageNumber = criteria.getPageNumber();
	int pageSize = criteria.getPageSize();

	PageRequest pageable = PageRequest.of(pageNumber, pageSize);

	query.with(pageable);

	return query;
  }

  protected abstract Query createWithoutPagination(C criteria);
}
