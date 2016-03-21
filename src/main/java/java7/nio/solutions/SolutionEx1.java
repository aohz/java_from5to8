package main.java.java7.nio.solutions;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;

import static java.nio.file.FileVisitResult.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;


/**
 * Suppose we want to copy a file tree to a target location. In that case,
 * symbolic links should be followed and the target directory should be created
 * before the entries in the directory are copied.
 * 
 * @author aohz
 *
 */
public class SolutionEx1 {

	public static void main(String... args) throws IOException {
		Path root = createTempFolder();
		copyTreeToTargetLocation(root, FileSystems.getDefault().getPath("/TEMP"));
	}

	public static void copyTreeToTargetLocation(Path source, Path target) throws IOException {

		Files.walkFileTree(source, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE,
				new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
						Path targetdir = target.resolve(source.relativize(dir));
						try {
							Files.copy(dir, targetdir);
						} catch (FileAlreadyExistsException e) {
							if (!Files.isDirectory(targetdir))
								throw e;
						}
						return CONTINUE;
					}

					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						Files.copy(file, target.resolve(source.relativize(file)));
						return CONTINUE;
					}
				});
	}

	private static Path createTempFolder() throws IOException {

		Path root = Files.createTempDirectory("Test");
		Path child1 = Files.createDirectories(Paths.get(root.toString(), "test_child1"));
		Files.createFile(Paths.get(child1.toString(), "test.txt"));
		Files.createDirectories(Paths.get(root.toString(), "test_child2"));

		return root;
	}
}
