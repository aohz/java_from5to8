package main.java.java7.languagechanges.exercises;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

/**
 * - Simplify and remove duplicate code regarding exception management
 * 
 * - Implement the method to read transactions from File
 * 
 * - Implement the method to return the transaction charge depending on the
 * transaction type
 * 
 * @author aohz
 *
 */
public class Exercise1 {

	private static final String FILE_PATH = "Transactions.txt";

	private static final String PAYMENT = "Payment";
	private static final String SALE = "Sale";
	private static final String DEBIT = "Debit";
	private static final String CREDIT = "Credit";

	private static final double PAYMENT_CHARGE = 0.1;
	private static final double SALE_CHARGE = 0.11;
	private static final double DEBIT_CHARGE = 0.11;
	private static final double CREDIT_CHARGE = 0.4;

	public static void main(String... args) {
		try {
			List<Transaction> transactions = readTransactionFromFile();

			for (Transaction t : transactions) {
				double charge = getTransactionCharge(t);
				System.out.println(t.getType() + ": " + calculateFinalAmount(t, charge));
			}
			// TODO: Simplify and remove duplicate code regarding exception
			// management
		} catch (IOException exp) {
			System.out.println("Exception reading Transactions from file " + exp.getMessage());
		} catch (URISyntaxException exp) {
			System.out.println("Exception reading Transactions from file " + exp.getMessage());
		} catch (RuntimeException exp) {
			System.out.println("Exception Parsing Transactions " + exp.getMessage());
		}
	}

	private static List<Transaction> readTransactionFromFile() throws URISyntaxException, IOException {
		List<Transaction> transactions = new ArrayList<>();
		// TODO: implement the code to read transaction.txt file and then call
		// the method parseLineToTransaction
		return transactions;
	}

	private static final Transaction parseLineToTransaction(String fileLine) {
		Object[] splitLine = fileLine.split(" ");
		String transType = (String) splitLine[0];
		double amount = Double.parseDouble((String) splitLine[1]);
		return new Transaction(transType, amount);
	}

	private static double getTransactionCharge(Transaction transaction) {
		double charge = 0;
		// TODO: implement the code to return the transaction charge depending
		// on the transaction type
		return charge;
	}

	private static final double calculateFinalAmount(Transaction t, double charge) {
		return t.getAmount() - (charge * t.getAmount());
	}

	static class Transaction {

		private final String type;
		private final double amount;

		public Transaction(String type, double amount) {
			this.type = type;
			this.amount = amount;
		}

		public double getAmount() {
			return amount;
		}

		public String getType() {
			return type;
		}

	}
}
