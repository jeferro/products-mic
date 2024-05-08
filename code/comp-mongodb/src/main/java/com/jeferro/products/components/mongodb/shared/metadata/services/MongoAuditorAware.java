package com.jeferro.products.components.mongodb.shared.metadata.services;

import java.util.Optional;

import com.jeferro.products.components.mongodb.MongoProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Profile(MongoProfile.NAME)
public class MongoAuditorAware implements AuditorAware<String> {

	private static final String UNKNOWN = "unknown";

	@Override
	public Optional<String> getCurrentAuditor() {
		final Authentication authentication = SecurityContextHolder.getContext()
			.getAuthentication();

		var value = UNKNOWN;

		if (authentication != null) {
			final Object principal = authentication.getPrincipal();

			if (principal instanceof String) {
				value = (String) principal;
			}
		}

		return Optional.of(value);
	}
}
