package main.java.java7.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * This code show 3 ways to ensure that a temporal file is deleted when the JVM shuts-down
 * @author aohz
 *
 */
public class Sample1_TempFile {

	private static final String TEMP_FOLDER = System.getProperty("java.io.tmpdir");

	public static void main(String... args) throws IOException {
		deleteOnExitUsingOptions();
		deleteOnExitUsingMethod();
		deleteOnExitUsingShutdownHook();
	}

	private static void deleteOnExitUsingOptions() throws IOException {
		Path tempPath = Files.createTempFile("test1_", ".txt");
		OutputStream os = Files.newOutputStream(tempPath, StandardOpenOption.CREATE, StandardOpenOption.APPEND,
				StandardOpenOption.DELETE_ON_CLOSE);
		
		listFiles(tempPath);
	}

	private static void deleteOnExitUsingMethod() throws IOException {
		Path tempPath = Files.createTempFile("test2", ".txt");
		tempPath.toFile().deleteOnExit();

		listFiles(tempPath);
	}

	private static void deleteOnExitUsingShutdownHook() throws IOException {
		Path tempPath = Files.createTempFile("test3", ".txt");

		Runnable r = () -> {
			try {
				Files.delete(tempPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		Runtime.getRuntime().addShutdownHook(new Thread(r));

		listFiles(tempPath);
	}

	private static void listFiles(Path path) {

		Path currentDirectory = Paths.get(TEMP_FOLDER);
		currentDirectory.forEach((Path cPath) -> {
			System.out.println(cPath.toString().contains("one"));

		});

	}
}
