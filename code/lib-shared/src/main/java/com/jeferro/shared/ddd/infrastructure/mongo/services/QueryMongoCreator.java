package com.jeferro.shared.ddd.infrastructure.mongo.services;

import com.jeferro.shared.ddd.domain.models.filter.Filter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;

public abstract class QueryMongoCreator<F extends Filter> {

  public Query create(F filter) {
	Query query = createWithoutPagination(filter);

	if (filter.isNotPageable()) {
	  return query;
	}

	int pageNumber = filter.getPageNumber();
	int pageSize = filter.getPageSize();

	PageRequest pageable = PageRequest.of(pageNumber, pageSize);

	query.with(pageable);

	return query;
  }

  protected abstract Query createWithoutPagination(F filter);
}
