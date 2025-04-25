package com.jeferro.products.parametrics.domain.models.values;

import com.jeferro.shared.ddd.domain.models.projection.ProjectionCollection;

import java.util.List;

public class ParametricValues extends ProjectionCollection<ParametricValueId, ParametricValue> {

    public ParametricValues() {
        super();
    }

    public ParametricValues(List<ParametricValue> entities) {
        super(entities);
    }

    public static ParametricValues createOf(List<ParametricValue> parametricValues) {
        return new ParametricValues(parametricValues);
    }
}
