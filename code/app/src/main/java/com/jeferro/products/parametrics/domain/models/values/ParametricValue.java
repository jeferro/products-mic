package com.jeferro.products.parametrics.domain.models.values;

import com.jeferro.shared.ddd.domain.models.projection.Projection;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import lombok.Getter;

@Getter
public class ParametricValue extends Projection<ParametricValueId> {

    private final LocalizedField name;

    private final String value;

    private final ParametricValues values;

    public ParametricValue(
            ParametricValueId id,
            LocalizedField name,
            String value,
            ParametricValues values) {
        super(id);

        this.name = name;
        this.value = value;
        this.values = values;
    }

    public static ParametricValue createSimple(ParametricValueId id, LocalizedField name) {
        ValueValidationUtils.isNotNull(name, "name", ParametricValue.class);

        return new ParametricValue(id, name, null, null);
    }
}
