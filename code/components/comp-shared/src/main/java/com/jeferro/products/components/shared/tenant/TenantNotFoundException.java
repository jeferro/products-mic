package com.jeferro.products.components.shared.tenant;

public class TenantNotFoundException extends RuntimeException {

  public TenantNotFoundException(String message) {
    super(message);
  }

  public static TenantNotFoundException createOfRequest() {
    throw new TenantNotFoundException("Tenant not found in request");
  }
}
