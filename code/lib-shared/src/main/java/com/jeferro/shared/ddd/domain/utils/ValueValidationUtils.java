package com.jeferro.shared.ddd.domain.utils;

import java.util.Collection;

import com.jeferro.shared.ddd.domain.exceptions.ValueValidationException;

public class ValueValidationUtils {

  public static void isNotBlank(String value, String attributeName, Object obj) {
	if (value == null || value.isBlank()) {
	  String message = formatAttributeName(attributeName, obj) + " is null or blank";
	  throw ValueValidationException.createOfMessage(message);
	}
  }

  public static void isNotNull(Object value, String attributeName, Object obj) {
	if (value == null) {
	  String message = formatAttributeName(attributeName, obj) + " is null";
	  throw ValueValidationException.createOfMessage(message);
	}
  }

  public static void isNotEmpty(Collection<?> value, String attributeName, Object obj) {
	if (value == null || value.isEmpty()) {
	  String message = formatAttributeName(attributeName, obj) + " is null or empty";
	  throw ValueValidationException.createOfMessage(message);
	}
  }

  public static void isZeroOrPositive(Integer value, String attributeName, Object obj) {
	if (value == null || value < 0) {
	  String message = formatAttributeName(attributeName, obj) + " is null or negative";
	  throw ValueValidationException.createOfMessage(message);
	}
  }

  private static String formatAttributeName(String attributeName, Object obj) {
	return obj.getClass().getSimpleName() + "." + attributeName;
  }
}
