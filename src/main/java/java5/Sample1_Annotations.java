package main.java.java5;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Sample1_Annotations {

	public static void main(String... args) {

		Class<TestExample> obj = TestExample.class;

		// Process @TesterInfo
		if (obj.isAnnotationPresent(TesterInfo.class)) {

			Annotation annotation = obj.getAnnotation(TesterInfo.class);
			TesterInfo testerInfo = (TesterInfo) annotation;

			System.out.println("Priority: " + testerInfo.priority());
			System.out.println("CreatedBy: " + testerInfo.createdBy());
			System.out.print("Tags: ");

			for (String tag : testerInfo.tags()) {
				System.out.print(tag + " ");
			}
			System.out.println("");
			System.out.println("LastModified: " + testerInfo.lastModified());

		}
	}
}

enum Priority {
	LOW, MEDIUM, HIGH
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // on class level
@interface TesterInfo {

	Priority priority() default Priority.MEDIUM;

	String[]tags() default "";

	String createdBy();

	String lastModified();
}

@TesterInfo(priority = Priority.HIGH, createdBy = "aohz", lastModified = "01/04/2016", tags = { "Sales", "Test" })
class TestExample {

}