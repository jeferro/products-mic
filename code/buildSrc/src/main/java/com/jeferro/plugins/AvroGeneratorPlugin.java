package com.jeferro.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class AvroGeneratorPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		AvroGeneratorExtension extension = project.getExtensions()
			.create("avroGenerator", AvroGeneratorExtension.class);

		project.afterEvaluate(project1 -> {
			AvroGeneratorTask.register(project, extension.schemaDir);
		});
	}
}
