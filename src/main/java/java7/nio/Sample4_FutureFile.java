package java7.nio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Sample4_FutureFile {
	
	private static final String FILE_PATH = "readfile.txt";
	
	public static void main(String[] args) throws URISyntaxException {
		try {
			Path file = Paths.get(Sample4_FutureFile.class.getClassLoader().getResource(FILE_PATH).toURI());
			// Open file asynchronously
			AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);
			/*
			 * A long operation: Start to read 100,000 bytes in the above file
			 */
			ByteBuffer buffer = ByteBuffer.allocate(100_000);
			Future<Integer> result = channel.read(buffer, 0);
			/*
			 * In the sample artificially we made sure the result would be
			 * finished (by using isDone() ). Normally the result would either
			 * be finished (and the main thread would continue), or it would
			 * wait until the background I/O is complete.
			 */
			while (!result.isDone()) {
				// Execute other useful logic in the MAIN thread while the
				// reading is not done
				doSomethingInteresting();
			}
			// Get the result from the async operation
			Integer bytesRead = result.get();
			System.out.println("Bytes read [" + bytesRead + "]");
			
			readContentFromBuffer(buffer);
			
		} catch (IOException | ExecutionException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
    }
	
	private static void doSomethingInteresting() {
		System.out.println("Do something Interesting");
	}
	

	private static void readContentFromBuffer(ByteBuffer buffer){
		buffer.flip();

		System.out.print("Buffer contents: ");

		while (buffer.hasRemaining()) {
			System.out.print((char) buffer.get());
		}

		System.out.println(" ");

		buffer.clear();
	}
}
