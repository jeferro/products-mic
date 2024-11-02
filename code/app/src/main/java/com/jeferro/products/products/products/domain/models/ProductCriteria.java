package com.jeferro.products.products.products.domain.models;

import com.jeferro.shared.ddd.domain.models.criteria.Criteria;

public class ProductCriteria extends Criteria {

    private String name;

    public ProductCriteria(Integer pageNumber, Integer pageSize, String name) {
        super(pageNumber, pageSize);

        setName(name);
    }

    public static ProductCriteria createEmpty() {
        return new ProductCriteria(null, null, null);
    }

    public static ProductCriteria createOfName(String name) {
        return new ProductCriteria(null, null, name);
    }

    public String getName() {
        return name;
    }

    public boolean hasName() {
        return !hasNotName();
    }

    public boolean hasNotName() {
        return name == null || name.isBlank();
    }

    private void setName(String name) {
        this.name = name;
    }
}
