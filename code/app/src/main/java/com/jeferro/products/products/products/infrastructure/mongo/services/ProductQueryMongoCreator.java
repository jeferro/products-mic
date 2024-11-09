package com.jeferro.products.products.products.infrastructure.mongo.services;

import java.util.ArrayList;
import java.util.List;

import com.jeferro.products.products.products.domain.models.filter.ProductFilter;
import com.jeferro.shared.ddd.infrastructure.mongo.services.QueryMongoCreator;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ProductQueryMongoCreator extends QueryMongoCreator<ProductFilter> {

    @Override
    protected List<Criteria> mapFilter(ProductFilter filter) {
        var criteria = new ArrayList<Criteria>();

        if (filter.hasName()) {
            Criteria nameCriteria = Criteria.where("name")
                .regex(filter.getName(), "i");

            criteria.add(nameCriteria);
        }

        return criteria;
    }

    @Override
    protected String mapOrder(ProductFilter filter) {
        return switch (filter.getOrder()) {
            case TYPE_ID -> "typeId";
            default -> "name";
        };
    }
}
