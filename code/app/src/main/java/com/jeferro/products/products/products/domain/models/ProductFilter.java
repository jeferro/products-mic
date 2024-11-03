package com.jeferro.products.products.products.domain.models;

import com.jeferro.shared.ddd.domain.models.filter.Filter;
import lombok.Getter;

@Getter
public class ProductFilter extends Filter {

    private String name;

    public ProductFilter(Integer pageNumber, Integer pageSize, String name) {
        super(pageNumber, pageSize);

        setName(name);
    }

    public static ProductFilter createEmpty() {
        return new ProductFilter(null, null, null);
    }

    public static ProductFilter createOfName(String name) {
        return new ProductFilter(null, null, name);
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
