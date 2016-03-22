package main.java.java8.date.solutions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 *
 * 1- Calculate number of days between today and yesterday
 * 
 * 2- Calculate number of seconds between now and 2h ago
 * 
 * 3- Calculate number of days between today and the first day of this month
 * 
 */
public class SolutionEx1 {

	public static void main(String[] args) {
		System.out.println("New Date and Time API\n");

		ex1();
		ex2();
		ex3();
	}

	private static void ex1() {

		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);

		Period period = Period.between(yesterday, today);

		System.out.println("Days: " + period.getDays());
	}

	private static void ex2() {

		LocalTime now = LocalTime.now();
		LocalTime ago2h = LocalTime.now().minus(2, ChronoUnit.HOURS);

		Duration duration = Duration.between(now, ago2h);

		System.out.println("Seconds: " + duration.getSeconds());
	}
	
	private static void ex3() {

		LocalDate firstDayOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
		LocalDate now = LocalDate.now();

		Period period = Period.between(firstDayOfMonth, now);

		System.out.println("Days: " + period.getDays());
	}

}
