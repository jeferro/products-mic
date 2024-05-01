package com.jeferro.plugins;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.compiler.specific.SpecificCompiler;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.compile.JavaCompile;

public class AvroGeneratorTask implements Action<Task> {

	private static final String TASK_ID = "avroGeneratorTask";

	private final File schemaDir;

	private final File targetDir;

	public AvroGeneratorTask(Project project, File schemaDir) {
		this.schemaDir = schemaDir;

		targetDir = project.getLayout()
			.getBuildDirectory()
			.file("generated/sources/avro/main/java")
			.get()
			.getAsFile();
	}

	public static void register(Project project, File schemasDir) {
		AvroGeneratorTask avroGeneratorTask = new AvroGeneratorTask(project, schemasDir);
		project.task(TASK_ID, avroGeneratorTask);

		project.getTasks().withType(JavaCompile.class)
			.configureEach(javaCompile -> javaCompile.dependsOn(TASK_ID));
	}

	@Override
	public void execute(Task task) {
		configureSourceSets(task);

		task.doLast(this::doLast);
	}

	private void configureSourceSets(Task task) {
		SourceSetContainer sourceSets = task.getProject().getExtensions()
			.getByType(SourceSetContainer.class);

		sourceSets.stream()
			.filter(sourceSet -> sourceSet.getName().equals("main"))
			.forEach(sourceSet -> sourceSet.getJava().srcDir(targetDir));
	}

	private void doLast(Task task) {
		try {
			File[] avroFiles = getAvroFiles(schemaDir);

			SpecificCompiler.compileSchema(avroFiles, targetDir);
		} catch (IOException cause) {
			throw new RuntimeException(cause);
		}
	}

	private File[] getAvroFiles(File schemaDir) {
		List<File> avroFiles = getAvroFilesRecursively(schemaDir);

		File[] avroFilesArray = new File[avroFiles.size()];
		return avroFiles.toArray(avroFilesArray);
	}

	private List<File> getAvroFilesRecursively(File directory) {
		List<File> schemaFiles = new ArrayList<>();

		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				schemaFiles.addAll(getAvroFilesRecursively(file));
			} else {
				schemaFiles.add(file);
			}
		}

		return schemaFiles;
	}
}
