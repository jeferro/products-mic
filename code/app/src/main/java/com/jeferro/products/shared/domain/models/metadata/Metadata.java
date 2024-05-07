package com.jeferro.products.shared.domain.models.metadata;

import java.time.Instant;

import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.value_objects.ValueObject;
import com.jeferro.products.shared.domain.services.time.TimeService;

public class Metadata extends ValueObject {

	private final Instant createdAt;

	private final String createdBy;

	private Instant updatedAt;

	private String updatedBy;

	public Metadata(Instant createdAt, String createdBy, Instant updatedAt, String updatedBy) {
		validateCreatedAt(createdAt);
		validateCreatedBy(createdBy);
		validateUpdatedAt(createdAt, updatedAt);
		validateUpdatedBy(updatedBy);

		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}

	public static Metadata createOf(Auth auth) {
		var now = TimeService.now();
		var who = auth.who();

		return new Metadata(now, who, now, who);
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

	public void markAsModified(Auth auth){
		this.updatedBy = auth.who();
		this.updatedAt = TimeService.now();
	}

	private void validateCreatedAt(Instant createdAt) {
		if (createdAt == null) {
			throw ValueValidationException.createOfMessage("Created date is null");
		}
	}

	private void validateCreatedBy(String createdBy) {
		if (createdBy == null) {
			throw ValueValidationException.createOfMessage("Created by is null");
		}
	}

	private void validateUpdatedAt(Instant createdAt, Instant updatedAt) {
		if (updatedAt == null) {
			throw ValueValidationException.createOfMessage("Updated date is null");
		}

		if (updatedAt.isBefore(createdAt)) {
			throw ValueValidationException.createOfMessage("Updated date is before created date");
		}
	}

	private void validateUpdatedBy(String updatedBy) {
		if (updatedBy == null) {
			throw ValueValidationException.createOfMessage("Updated by is null");
		}
	}
}
