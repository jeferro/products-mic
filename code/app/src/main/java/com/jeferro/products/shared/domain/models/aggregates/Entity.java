package com.jeferro.products.shared.domain.models.aggregates;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class Entity<ID extends Identifier> {

    protected final ID id;

    public Entity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public boolean hasSameId(ID otherId) {
        return id.equals(otherId);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

		if (other == null || getClass() != other.getClass()) {
			return false;
		}

        return EqualsBuilder.reflectionEquals(
                this,
                other
        );
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
    }
}
