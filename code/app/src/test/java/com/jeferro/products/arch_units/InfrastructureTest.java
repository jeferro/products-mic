package com.jeferro.products.arch_units;

import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class InfrastructureTest extends BaseArchUnit {

  private static final String DTO_SUFFIX = "DTO";

  private static final String DTO_PACKAGE = "..dtos..";

  @Test
  public void dtos_should_be_inside_dtos_package() {
	classes()
		.that().resideInAPackage(INFRASTRUCTURE_LAYER)
		.and().haveSimpleNameEndingWith(DTO_SUFFIX)
		.should().resideInAnyPackage(DTO_PACKAGE)
		.check(importedClasses);

	classes()
		.that().resideInAPackage(INFRASTRUCTURE_LAYER)
		.and().resideInAnyPackage(DTO_PACKAGE)
		.should().haveSimpleNameEndingWith(DTO_SUFFIX)
		.check(importedClasses);
  }

  @Test
  public void mappers_should_be_inside_mappers_package() {
	classes()
		.that().resideInAPackage(INFRASTRUCTURE_LAYER)
		.and().haveSimpleNameEndingWith(DTO_SUFFIX)
		.should().resideInAnyPackage(DTO_PACKAGE)
		.check(importedClasses);

	classes()
		.that().resideInAPackage(INFRASTRUCTURE_LAYER)
		.and().resideInAnyPackage(DTO_PACKAGE)
		.should().haveSimpleNameEndingWith(DTO_SUFFIX)
		.check(importedClasses);
  }
}
