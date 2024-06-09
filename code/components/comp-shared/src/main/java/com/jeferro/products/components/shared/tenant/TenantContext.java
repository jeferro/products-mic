package com.jeferro.products.components.shared.tenant;

import org.springframework.util.StringUtils;

public class TenantContext {

  protected static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

  public static void setTenantId(String tenantId) {
    if (!StringUtils.hasText(tenantId)) {
      throw TenantNotFoundException.createOfRequest();
    }

    threadLocal.set(tenantId);
  }

  public static String getTenantId() {
    return threadLocal.get();
  }

  public static void clear() {
    threadLocal.remove();
  }
}
