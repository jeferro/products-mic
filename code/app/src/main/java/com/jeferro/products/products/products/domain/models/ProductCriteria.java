package com.jeferro.products.products.products.domain.models;

import com.jeferro.shared.ddd.domain.models.criteria.Criteria;
import org.apache.commons.lang3.StringUtils;

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
        return StringUtils.isEmpty(name);
    }

    private void setName(String name) {
        this.name = name;
    }
}
