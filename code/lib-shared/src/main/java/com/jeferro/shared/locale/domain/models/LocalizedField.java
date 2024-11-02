package com.jeferro.shared.locale.domain.models;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import com.jeferro.shared.ddd.domain.models.value_objects.ValueObject;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;

public class LocalizedField extends ValueObject {

  private Map<String, String> values;

  public LocalizedField(Map<String, String> values) {
	setValues(values);
  }

  public static LocalizedField createOf() {
	var values = new HashMap<String, String>();

	return new LocalizedField(values);
  }

  public static LocalizedField createOf(String k1, String v1) {
	var values = new HashMap<String, String>();
	values.put(k1, v1);

	return new LocalizedField(values);
  }

  public static LocalizedField createOf(String k1, String v1,
	  String k2, String v2) {
	var values = new HashMap<String, String>();
	values.put(k1, v1);
	values.put(k2, v2);

	return new LocalizedField(values);
  }

  private void setValues(Map<String, String> values) {
	ValueValidationUtils.isNotNull(values, "Values");
	this.values = values;
  }

  public Map<String, String> getValues() {
	return values;
  }

  public String getValue(Locale locale) {
	var key = locale.toLanguageTag();

	return values.getOrDefault(key, "");
  }

  public Map<String, String> getValues(Locale locale) {
	if (locale == null
		|| Locale.ROOT.equals(locale)) {
	  return values;
	}

	var key = locale.toLanguageTag();
	var value = values.getOrDefault(key, "");

	return Map.of(key, value);
  }

  public boolean containsValue(String name) {
	var target = name.toLowerCase();

	return values.values().stream()
		.map(String::toLowerCase)
		.anyMatch(translation -> translation.contains(target));
  }

  public Stream<Entry<String, String>> entrySetStream() {
	return values.entrySet().stream();
  }
}
