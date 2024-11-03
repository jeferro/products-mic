package com.jeferro.products.products.products.infrastructure.mongo.services;

import com.jeferro.products.products.products.domain.models.ProductFilter;
import com.jeferro.shared.ddd.infrastructure.mongo.services.QueryMongoCreator;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ProductQueryMongoCreator extends QueryMongoCreator<ProductFilter> {
    @Override
    protected Query createWithoutPagination(ProductFilter filter) {
        Query query = new Query();

        if (filter.hasName()) {
            Criteria nameCriteria = Criteria.where("name")
                    .regex(filter.getName(), "i");

            query.addCriteria(nameCriteria);
        }

        return query;
    }
}
