package com.jeferro.plugins.avro_generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.compiler.specific.SpecificCompiler;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.tasks.compile.JavaCompile;

public class AvroGeneratorTask implements Action<Task> {

	private static final String TASK_ID = "avroGeneratorTask";

	private final File schemaDir;

	private final File targetDir;

	public AvroGeneratorTask(File schemaDir, File targetDir) {
		this.schemaDir = schemaDir;
		this.targetDir = new File(targetDir, "src/main/java");
	}

	public static void register(Project project, File schemasDir, File targetDir) {
		AvroGeneratorTask avroGeneratorTask = new AvroGeneratorTask(schemasDir, targetDir);
		project.task(TASK_ID, avroGeneratorTask);

		project.getTasks().withType(JavaCompile.class)
			.configureEach(javaCompile -> javaCompile.dependsOn(TASK_ID));
	}

	@Override
	public void execute(Task task) {
		task.doLast(this::doLast);
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
