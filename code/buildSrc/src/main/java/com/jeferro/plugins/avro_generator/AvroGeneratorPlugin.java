package com.jeferro.plugins.avro_generator;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.SourceSetContainer;

import java.io.File;

public class AvroGeneratorPlugin implements Plugin<Project> {

    @Override
    public void apply(Project target) {
        AvroGeneratorExtension extension = target.getExtensions()
                .create("avroGenerator", AvroGeneratorExtension.class);

        target.afterEvaluate(project -> {
            AvroGeneratorTask.register(project, extension.schemaDir, extension.targetDir);

            configureSourceSets(project, extension);
        });
    }

    private void configureSourceSets(Project project, AvroGeneratorExtension extension) {
        project.getExtensions()
                .getByType(SourceSetContainer.class).stream()
                .filter(sourceSet -> sourceSet.getName().equals("main"))
                .forEach(sourceSet -> {
                    sourceSet.getResources().srcDir(extension.schemaDir);

                    var targetDir = new File(extension.targetDir, "src/main/java");
                    sourceSet.getJava().srcDir(targetDir);
                });
    }
}
