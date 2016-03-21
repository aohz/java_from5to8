package main.java.java7.nio.exercises;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Copy the generated file tree to a target location using walkFileTree
 * 
 * @author aohz
 *
 */
public class Exercise1 {

	public static void main(String... args) throws IOException {
		Path root = createTempFolder();
		copyTreeToTargetLocation(root, FileSystems.getDefault().getPath("/TEMP"));
	}

	public static void copyTreeToTargetLocation(Path source, Path target) throws IOException {
	
	}

	private static Path createTempFolder() throws IOException {

		Path root = Files.createTempDirectory("Test");
		Path child1 = Files.createDirectories(Paths.get(root.toString(), "test_child1"));
		Files.createFile(Paths.get(child1.toString(), "test.txt"));
		Files.createDirectories(Paths.get(root.toString(), "test_child2"));

		return root;
	}
}
