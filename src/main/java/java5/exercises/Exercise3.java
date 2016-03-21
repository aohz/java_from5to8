package main.java.java5.exercises;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * - Create a new Annotation "@BookInfo" to the book class to keep the following
 * information: revisionNumber, revisionDate and reviewers - Print the
 * information of the @BookInfo annotation
 * 
 * 
 * @author aohz
 *
 */
public class Exercise3 {

	public static void main(String... args) throws Exception {

		for (Annotation annotation : Book.class.getAnnotations()) {
			if (annotation instanceof BookInfo) {
				BookInfo bookInfo = (BookInfo) annotation;
				System.out.println("Revision Number: " + bookInfo.revisionNumber());
				System.out.println("Revision Date :" + bookInfo.revisionDate());
				for (String reviewer : bookInfo.reviewers()) {
					System.out.println("Reviewer: " + reviewer);
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

		public String getAuthor() {
			return author;
		}
	}

	// Indicates that this annotation is to be documented by javadoc
	@Documented
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@interface BookInfo {

		double revisionNumber() default 1;

		String revisionDate();

		// Note use of array
		String[]reviewers();
	}
}