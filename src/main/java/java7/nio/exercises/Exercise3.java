package main.java.java7.nio.exercises;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;


/**
 * List The files in the Temp folder created using the Walk File Tree
 * 
 * @author aohz
 *
 */
public class Exercise3 {

	public static void main(String... args) throws IOException {
		Path root = createTempFolder();
		listFilesInFolder(root, FileSystems.getDefault().getPath("/TEMP"));
	}

	public static void listFilesInFolder(Path source, Path target) throws IOException {

		
	}

	private static Path createTempFolder() throws IOException {

		Path root = Files.createTempDirectory("Test");
		Path child1 = Files.createDirectories(Paths.get(root.toString(), "test_child1"));
		Files.createFile(Paths.get(child1.toString(), "test.txt"));
		Files.createDirectories(Paths.get(root.toString(), "test_child2"));

		return root;
	}
}
