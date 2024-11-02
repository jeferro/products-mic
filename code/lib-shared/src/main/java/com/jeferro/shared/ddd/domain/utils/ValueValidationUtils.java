package com.jeferro.shared.ddd.domain.utils;

import java.util.Collection;

import com.jeferro.shared.ddd.domain.exceptions.ValueValidationException;

public class ValueValidationUtils {

  public static void isNotBlank(String value, String attributeName) {
	if (value == null || value.isBlank()) {
	  throw ValueValidationException.createOfMessage(attributeName + " is null or blank");
	}
  }

  public static void isNotNull(Object value, String attributeName) {
	if (value == null) {
	  throw ValueValidationException.createOfMessage(attributeName + " is null");
	}
  }

  public static void isNotEmpty(Collection<?> value, String attributeName) {
	if (value == null || value.isEmpty()) {
	  throw ValueValidationException.createOfMessage(attributeName + " is null or empty");
	}
  }

  public static void isZeroOrPositive(Integer value, String attributeName) {
	if (value == null || value < 0) {
	  throw ValueValidationException.createOfMessage(attributeName + " is null or negative");
	}
  }
}
