package java5.exercises;

/**
 * - Create a new Annotation "@BookInfo" to the book class to keep the following information:
 * 
 * revisionNumber, revisionDate and reviewers 
 * 
 * - Print the information of the @BookInfo annotation
 * 
 * 
 * @author aohz
 *
 */
public class Exercise3 {

	public static void main(String... args) throws Exception {
		
	}
	
	class Book {

		private final String author;

		public Book(String author) {
			this.author = author;
		}

		public String getAuthor() {
			return author;
		}
	}
}