package java7.nio;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * 
 * @author aohz
 *
 */
public class Sample2_WatchService {

	private static final String FOLDER_PATH = "watchservice";

	public static void main(String... args) throws IOException, URISyntaxException {

		// Create the WatchService
		WatchService watcher = FileSystems.getDefault().newWatchService();

		Path dir = Paths.get(Sample2_WatchService.class.getClassLoader().getResource(FOLDER_PATH).toURI());
		System.out.println("Path: " + dir.toAbsolutePath());

		try {
			WatchKey key = dir.register(watcher, ENTRY_CREATE);

			while (true) {

				// wait for key to be signaled
				// key = watcher.take();
				key = watcher.poll();
				if (key == null) {
					doSomethingInteresting();
					continue;
				}

				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();

					// This key is registered only
					// for ENTRY_CREATE events,
					// but an OVERFLOW event can
					// occur regardless if events
					// are lost or discarded.
					if (kind == OVERFLOW) {
						continue;
					}

					// The filename is the
					// context of the event.
					WatchEvent<Path> ev = (WatchEvent<Path>) event;
					Path filename = ev.context();
					System.out.println("Filename:" + filename + " Event: " + kind);
				}

				// Reset the key -- this step is critical if you want to
				// receive further watch events. If the key is no longer valid,
				// the directory is inaccessible so exit the loop.
				boolean valid = key.reset();
				if (!valid) {
					break;
				}
			}

		} catch (InterruptedException | IOException x) {
			System.err.println(x);
		}
	}

	private static void doSomethingInteresting() throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("Do something interesting while waiting");
	}
}
