package java7.languagechanges.solutions;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * - Simplify and remove duplicate code regarding exception management
 *
 * - Implement the method to return the transaction charge depending on the
 * transaction type
 * 
 * @author aohz
 *
 */
public class SolutionEx1 {

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
			List<Transaction> transactions = getTransactions();

			for (Transaction t : transactions) {
				double charge = getTransactionCharge(t);
				System.out.println(t.getType() + ": " + calculateFinalAmount(t, charge));
			}
		} catch (URISyntaxException | IOException exp) {
			System.out.println("Exception getting Transactions from file " + exp.getMessage());
		} catch (RuntimeException exp) {
			System.out.println("Exception Parsing Transactions " + exp.getMessage());
		}
	}

	private static List<Transaction> getTransactions() throws URISyntaxException, IOException {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction("Payment", 1_000_000));
		transactions.add(new Transaction("Sale", 5_000_000));
		transactions.add(new Transaction("Debit", 2_000_000));
		transactions.add(new Transaction("Credit", 1_000_000));		
		return transactions;
	}

	private static double getTransactionCharge(Transaction transaction) {
		double charge = 0;
		switch (transaction.getType()) {
		case PAYMENT:
			charge = PAYMENT_CHARGE;
			break;
		case SALE:
			charge = SALE_CHARGE;
			break;
		case DEBIT:
			charge = DEBIT_CHARGE;
			break;
		case CREDIT:
			charge = CREDIT_CHARGE;
			break;
		}
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
