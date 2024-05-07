package com.jeferro.products.shared.domain.models.metadata;

import com.jeferro.products.shared.domain.services.time.TimeService;

public abstract class MetadataMother {

	public static Metadata createOfUser() {
		var now = TimeService.now();
		return new Metadata(now, "user", now, "user");
	}
}