package com.jeferro.shared.auth.infrastructure;

import com.jeferro.shared.ddd.domain.models.auth.AnonymousAuth;
import com.jeferro.shared.ddd.domain.models.auth.Auth;
import com.jeferro.shared.ddd.domain.models.auth.SystemAuth;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.util.Locale;

public abstract class ContextManager {

    public static void signInFromWeb(Auth auth, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken.authenticated(
                auth,
                null,
                null);

        WebAuthenticationDetailsSource details = new WebAuthenticationDetailsSource();
        details.buildDetails(request);

        authentication.setDetails(details);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static void signInSystem() {
        var auth = SystemAuth.create();

        UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken.authenticated(
                auth,
                null,
                null);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static void signOut() {
        SecurityContextHolder.clearContext();
    }

    public static Auth getAuth() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication != null
                ? (Auth) authentication.getPrincipal()
                : AnonymousAuth.create();
    }

    public static Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}
