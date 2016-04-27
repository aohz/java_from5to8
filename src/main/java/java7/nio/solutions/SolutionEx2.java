package java7.nio.solutions;

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
public class SolutionEx2 {

	public static void main(String... args) throws IOException {
		Path root = createTempFolder();
		listFilesInFolder(root, FileSystems.getDefault().getPath("/TEMP"));
	}

	public static void listFilesInFolder(Path source, Path target) throws IOException {

		Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				System.out.println("==PostVisitDirectory==");
				System.out.println(dir.getFileName());
				return FileVisitResult.CONTINUE;
			}

			// called before a directory visit.
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				System.out.println("===preVisitDirectory===");
				System.out.println(dir.getFileName());
				return FileVisitResult.CONTINUE;
			}

			// This method is called for each file visited. The basic attributes
			// of the files are also available.
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println("===visitFile()===");
				System.out.println(file.getFileName());
				return FileVisitResult.CONTINUE;
			}

			// if the file visit fails for any reason, the visitFileFailed
			// method is called.
			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				System.out.println("===visitFileFailed===");
				System.out.println(file.getFileName());
				return FileVisitResult.CONTINUE;
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
