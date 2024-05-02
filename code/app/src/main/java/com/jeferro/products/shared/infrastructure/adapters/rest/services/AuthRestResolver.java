package com.jeferro.products.shared.infrastructure.adapters.rest.services;

import java.util.Set;

import com.jeferro.products.components.rest.shared.RestProfile;
import com.jeferro.products.shared.domain.models.auth.AnonymousAuth;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.auth.UserAuth;
import com.jeferro.products.shared.domain.models.users.Username;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Profile(RestProfile.NAME)
public class AuthRestResolver {

    public Auth resolve() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null){
			return AnonymousAuth.create();
		}

		var username = new Username(authentication.getPrincipal().toString());
		var roles = (Set<String>) authentication.getCredentials();

		return UserAuth.create(username, roles);
    }
}
