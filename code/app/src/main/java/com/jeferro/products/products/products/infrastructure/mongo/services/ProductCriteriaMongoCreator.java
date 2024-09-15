package com.jeferro.products.products.products.infrastructure.mongo.services;

import com.jeferro.products.products.products.domain.models.ProductCriteria;
import com.jeferro.shared.ddd.infrastructure.adapters.mongo.services.CriteriaMongoCreator;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ProductCriteriaMongoCreator extends CriteriaMongoCreator<ProductCriteria> {
    @Override
    protected Query createWithoutPagination(ProductCriteria criteria) {
        Query query = new Query();

        if (criteria.hasName()) {
            Criteria nameCriteria = Criteria.where("name")
                    .regex(criteria.getName(), "i");

            query.addCriteria(nameCriteria);
        }

        return query;
    }
}
