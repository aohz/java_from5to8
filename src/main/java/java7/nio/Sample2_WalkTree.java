package main.java.java7.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * Suppose we want to delete a file tree. In that case, each directory should be deleted after the entries in the directory are deleted.
 * 
 * @author aohz
 *
 */
public class Sample2_WalkTree {

	public static void main(String... args) throws IOException {
		Path root = createTempFolder();
		removeFolders(root);
		validateFolderIsEmpty(root);
	}

	private static void removeFolders(Path root) throws IOException {		
		Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
				if (e == null) {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				} else {
					// directory iteration failed
					throw e;
				}
			}
		});

	}

	private static void validateFolderIsEmpty(Path root) throws IOException {		
		System.out.println("Temp Folder exist?  " + Files.exists(root));
	}
	
	private static Path createTempFolder() throws IOException {
//		Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
//		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);

		Path root = Files.createTempDirectory("Test");		
		Path child1 = Files.createDirectories(Paths.get(root.toString(), "test_child1"));
		Files.createFile(Paths.get(child1.toString(), "test.txt"));
		Files.createDirectories(Paths.get(root.toString(), "test_child2"));
		
		return root;
	}

}
