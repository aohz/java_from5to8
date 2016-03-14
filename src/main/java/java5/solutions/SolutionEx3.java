package main.java.java5.solutions;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * - Create a new Annotation "@BookInfo" to the book class to keep the following information: revisionNumber, revisionDate and reviewers
 * - Print the information of the @BookInfo annotation
 * 
 * 
 * @author aohz
 *
 */
public class SolutionEx3 {

	public static void main(String... args) throws Exception {

		for (Annotation annotation : Book.class.getAnnotations()) {
			if (annotation instanceof BookInfo) {
				BookInfo bookInfo = (BookInfo) annotation;
				System.out.println(bookInfo.revisionNumber());
				System.out.println(bookInfo.revisionDate());
			}
		}

		Book myBook = new Book("alberto");
		printBookName(myBook);
		Book noonesBook = new Book(null);
		printBookName(noonesBook);
	}

	private static void printBookName(Book book) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		
		Method getAuthor = Book.class.getMethod("getAuthor");
		if (getAuthor.getAnnotation(Throwable.class) != null) {
			try {
				System.out.println(book.getAuthor());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}
}

@BookInfo(revisionNumber = 2.1, revisionDate = "11/03/2015", reviewers = { "aohz", "aohg" })
class Book {

	private final String author;

	public Book(String author) {
		this.author = author;
	}

	@Throwable
	public String getAuthor() {
		if (author == null) {
			throw new RuntimeException("The author is null");
		}
		return author;
	}
}

// Indicates that this annotation is to be documented by javadoc
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface BookInfo {
	
	double revisionNumber()
	default 1;
	String revisionDate();
	// Note use of array
	String[]reviewers();
}

@Retention(RetentionPolicy.RUNTIME)
@interface Throwable {
}