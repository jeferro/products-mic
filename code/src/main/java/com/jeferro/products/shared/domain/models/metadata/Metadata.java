package com.jeferro.products.shared.domain.models.metadata;

import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.value_objects.ValueObject;
import com.jeferro.products.shared.domain.services.time.TimeService;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;

// TODO: Remove and audit only in infrastructure
public class Metadata extends ValueObject {

    private final Instant createdAt;

    private final String createdBy;

    private Instant updatedAt;

    private String updatedBy;

    public Metadata(Instant createdAt, String createdBy, Instant updatedAt, String updatedBy) {
        if (createdAt == null) {
            throw ValueValidationException.ofMessage("Created at is null");
        }

        if (StringUtils.isBlank(createdBy)) {
            throw ValueValidationException.ofMessage("Created by is blank");
        }

        if (updatedAt == null) {
            throw ValueValidationException.ofMessage("Updated at is null");
        }

        if (StringUtils.isBlank(updatedBy)) {
            throw ValueValidationException.ofMessage("Updated by is blank");
        }

        if (updatedAt.isBefore(createdAt)) {
            throw ValueValidationException.ofMessage("Updated at is before created at");
        }

        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public static Metadata of(Auth auth) {
        var now = TimeService.now();
        var who = auth.who();

        return new Metadata(now, who, now, who);
    }

    public void update(Auth auth) {
        this.updatedAt = TimeService.now();
        this.updatedBy = auth.who();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }
}
