package main.java.java5.exercises;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * - Create a new Annotation "@Mandatory" and mark the method getName with that
 * Annotation
 * 
 * - Using Java Reflection invoke the book methods
 * 
 * - Only invoke methods inside a try-catch block if the method has been mark as
 * "@Mandatory"
 * 
 * 
 * @author aohz
 *
 */
public class Exercise4 {

	public static void main(String... args) throws Exception {
		System.out.println("=== Book 1 ===");
		Book myBook = new Book("Clean code", "Pedro");
		printMethodResult(myBook, "getName");
		printMethodResult(myBook, "getAuthor");

		System.out.println("=== Book 2 ===");
		Book noonesBook = new Book(null, null);
		printMethodResult(noonesBook, "getName");
		printMethodResult(noonesBook, "getAuthor");
	}

	private static void printMethodResult(Object targetObject, String methodToInvoke) throws Exception {
		Object result = null;
		Method getMethod = null;
		
		System.out.println(getMethod.getName() + ": " + result);
	}

	static class Book {

		private final String name;
		private final String author;

		public Book(String name, String author) {
			this.name = name;
			this.author = author;
		}
		
		public String getName() {
			if (author == null) {
				throw new RuntimeException("The book name can't be null");
			}
			return name;
		}

		public String getAuthor() {
			return author;
		}
	}

}