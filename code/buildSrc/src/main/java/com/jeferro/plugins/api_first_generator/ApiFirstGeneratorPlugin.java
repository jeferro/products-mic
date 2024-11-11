package com.jeferro.plugins.api_first_generator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.compile.JavaCompile;
import org.openapitools.generator.gradle.plugin.OpenApiGeneratorPlugin;
import org.openapitools.generator.gradle.plugin.extensions.OpenApiGeneratorGenerateExtension;

public class ApiFirstGeneratorPlugin implements Plugin<Project> {

	@Override
	public void apply(Project target) {
		ApiFirstGeneratorExtension extension = target.getExtensions()
			.create("apiFirstGenerator", ApiFirstGeneratorExtension.class);

		target.getPlugins().apply(OpenApiGeneratorPlugin.class);

		target.afterEvaluate(project -> {

			configureSourceSets(project, extension);

			configureOpenapiTask(project, extension);
		});
	}

	private void configureSourceSets(Project project, ApiFirstGeneratorExtension extension) {
		project.getExtensions()
			.getByType(SourceSetContainer.class).stream()
			.filter(sourceSet -> sourceSet.getName().equals("main"))
			.forEach(sourceSet -> {
				sourceSet.getResources().srcDir(extension.specFile.getParentFile());

				var targetDir = new File(extension.targetDir, "src/main/java");
				sourceSet.getJava().srcDir(targetDir);
			});
	}

	private void configureOpenapiTask(Project project, ApiFirstGeneratorExtension extension) {
		project.getTasks().withType(JavaCompile.class)
			.configureEach(javaCompile -> javaCompile.dependsOn("openApiGenerate"));

		project.getExtensions().configure(
			OpenApiGeneratorGenerateExtension.class,
			generatorExtension -> {
				generatorExtension.getGeneratorName().set("spring");
				generatorExtension.getInputSpec().set(extension.specFile.getAbsolutePath());
				generatorExtension.getOutputDir().set(extension.targetDir.getAbsolutePath());

				generatorExtension.getPackageName().set(extension.basePackage);
				generatorExtension.getApiPackage().set(extension.basePackage + ".apis");
				generatorExtension.getModelPackage().set(extension.basePackage + ".dtos");
				generatorExtension.getModelNameSuffix().set("RestDTO");
				generatorExtension.getApiNameSuffix().set("Api");

				generatorExtension.getGenerateApiTests().set(false);

				Map<String, String> options = new HashMap<>();
				options.put("useTags", "true");
				options.put("gradleBuildFile", "false");
				options.put("useSpringBoot3", "true");
				options.put("documentationProvider", "none");
				options.put("interfaceOnly", "true");
				options.put("useResponseEntity", "false");
				generatorExtension.getConfigOptions().set(options);
			});
	}
}
