package main.java.java7.nio.exercises;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

/**
 * - Read the content from Transactions.txt File
 * 
 * -  Write the same content in a Temporal file
 * 
 * @author aohz
 *
 */
public class Exercise2 {

	private static final String FILE_PATH = "Transactions.txt";

	public static void main(String... args) throws URISyntaxException, IOException {
		
		Path path = null;
		//TODO read lines from Transactions.txt file
		
		//TODO write the same content in a Temporal file
		Path outputPath = null;
				
		System.out.println(outputPath.toAbsolutePath());
	}
}
