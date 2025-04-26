package com.jeferro.products.products.products.domain.models.filter;

import com.jeferro.shared.ddd.domain.models.filter.Filter;
import lombok.Getter;

import static com.jeferro.products.products.products.domain.models.filter.ProductFilterOrder.NAME;

@Getter
public class ProductFilter extends Filter<ProductFilterOrder> {

    private final String name;

    public ProductFilter(Integer pageNumber, Integer pageSize, ProductFilterOrder order, Boolean ascending, String name) {
        super(pageNumber, pageSize, order, ascending);

        this.name = name;
    }

    public static ProductFilter createEmpty() {
        return new ProductFilter(null, null, NAME, null, null);
    }

    public static ProductFilter searchName(String name) {
        return new ProductFilter(null, null, NAME, null, name);
    }

    public boolean hasName() {
        return name != null && !name.isBlank();
    }
}
