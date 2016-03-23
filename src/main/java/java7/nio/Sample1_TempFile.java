package main.java.java7.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * This code show 3 ways to ensure that a temporal file is deleted when the JVM
 * shuts-down
 * 
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

		listFiles();
	}

	private static void deleteOnExitUsingMethod() throws IOException {
		Path tempPath = Files.createTempFile("test2_", ".txt");
		tempPath.toFile().deleteOnExit();

		listFiles();
	}

	private static void deleteOnExitUsingShutdownHook() throws IOException {
		Path tempPath = Files.createTempFile("test3_", ".txt");

		Runnable r = () -> {
			try {
				Files.delete(tempPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		Runtime.getRuntime().addShutdownHook(new Thread(r));

		listFiles();
	}

	private static void listFiles() throws IOException {

		Path tmpDirectory = Paths.get(TEMP_FOLDER);

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(tmpDirectory)) {
			for (Path cPath : stream) {
				String pathName = cPath.toString();
				if (pathName.contains("test1_") || pathName.contains("test2_") || pathName.contains("test3_")) {
					System.out.println(cPath.toAbsolutePath());					
				}
			}
		}
	}
}
