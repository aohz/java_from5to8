package java7.nio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sample3_Callback {

	private Thread currentThread;

	private static final String FILE_PATH = "readfile.txt";

	public static void main(String[] args) throws Exception {
		new Sample3_Callback().readFile();
	}

	private void readFile() throws IOException, URISyntaxException {

		Path path = Paths.get(Sample3_Callback.class.getClassLoader().getResource(FILE_PATH).toURI());

		AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		currentThread = Thread.currentThread();
		System.out.println("Current Thread :" + currentThread.getName());

		channel.read(buffer, 0, "Read operation ALFA", new CompletionHandler<Integer, String>() {

			@Override
			public void completed(Integer result, String attachment) {
				System.out.println("Callback " + Thread.currentThread().getName());
				System.out.println(attachment + " completed and " + result + " bytes are read. ");
				currentThread.interrupt();
			}

			@Override
			public void failed(Throwable e, String attachment) {
				System.out.println(attachment + " failed with exception:");
				System.out.println(e);
				currentThread.interrupt();
			}
		});

		blockCurrentThreadUntilItisInterrupted();

		readContentFromBuffer(buffer);

		channel.close();
	}

	private void readContentFromBuffer(ByteBuffer buffer) {
		buffer.flip();

		System.out.print("Buffer contents: ");

		while (buffer.hasRemaining()) {
			System.out.print((char) buffer.get());
		}

		System.out.println(" ");

		buffer.clear();
	}

	private void blockCurrentThreadUntilItisInterrupted() {
		try {
			while (!currentThread.isInterrupted()) {
				System.out.println(Thread.currentThread().getName() + " Waiting for completion...");
				Thread.sleep(1);
			}
		} catch (InterruptedException e1) {
			System.out.println(e1);
		}
	}

}
