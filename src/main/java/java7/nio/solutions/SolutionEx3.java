package main.java.java7.nio.solutions;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * - Read the content from transactions.txt File
 * 
 * -  Write the same content in a Temporal file
 * 
 * @author aohz
 *
 */
public class SolutionEx3 {

	private static final String FILE_PATH = "Transactions.txt";

	public static void main(String... args) throws URISyntaxException, IOException {
		
		Path path = Paths.get(SolutionEx3.class.getClassLoader().getResource(FILE_PATH).toURI());
		List<String> lines = Files.readAllLines(path);
		// Have a look to the implementation of readAllLines method
		for (String line : lines) {
			System.out.println(line);
		}
		
		Path outputPath = Files.createTempFile("test", ".txt");
		Files.write(outputPath, lines, StandardOpenOption.CREATE);
		
		System.out.println(outputPath.toAbsolutePath());
	}
}
