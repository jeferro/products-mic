package com.jeferro.products.components.rest.shared.filters;

import java.io.IOException;

import com.jeferro.products.components.shared.tenant.TenantContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TenantRestFilter extends OncePerRequestFilter {

  private static final String TENANT_ID_HEADER = "X-Tenant";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var tenantId = request.getHeader(TENANT_ID_HEADER);

    TenantContext.setTenantId(tenantId);

    filterChain.doFilter(request, response);
  }
}
