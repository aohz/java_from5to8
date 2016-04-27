package java7.languagechanges.exercises;

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
public class Exercise1 {

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

	private static List<Transaction> getTransactions() throws URISyntaxException, IOException {
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(new Transaction("Payment", 1000000));
		transactions.add(new Transaction("Sale", 5000000));
		transactions.add(new Transaction("Debit", 2000000));
		transactions.add(new Transaction("Credit", 1000000));
		return transactions;
	}

	private static double getTransactionCharge(Transaction transaction) {
		// TODO: Simplify code using switch statement		
		double charge = 0;
		String type = transaction.getType();
		if (PAYMENT.equals(type)) {
			charge = PAYMENT_CHARGE;
		} else if (SALE.equals(type)) {
			charge = SALE_CHARGE;
		} else if (DEBIT.equals(type)) {
			charge = DEBIT_CHARGE;
		} else if (CREDIT.equals(type)) {
			charge = CREDIT_CHARGE;
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
