package main.java.java7.nio.exercises;

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
 * 1 - Improve the following code to be notified when files are edited or deleted
 * 
 * 2 - I don't want the thread to be block while it is waiting for changes in
 * the file system
 * 
 * @author aohz
 *
 */
public class Exercise2 {

	private static final String FOLDER_PATH = "watchservice";

	public static void main(String... args) throws IOException, URISyntaxException {

		// Create the WatchService
		WatchService watcher = FileSystems.getDefault().newWatchService();

		Path dir = Paths.get(Exercise2.class.getClassLoader().getResource(FOLDER_PATH).toURI());
		try {
			WatchKey key = dir.register(watcher, ENTRY_CREATE);

			while (true) {

				// wait for key to be signaled
				key = null;			

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

		} catch (IOException x) {
			System.err.println(x);
		}

	}

	private static void doSomethingInteresting() {
		System.out.println("Do something interesting while waiting");
	}
}
