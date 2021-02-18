import java.util.Date;

public class Transaction {

	private Date timeOfTransaction;
	private double amount;
	private String memo;
	private Account fromAccount;
	private Account toAccount;
	
	
	public Transaction(double amount, Account fromAccount) {
		this.amount = amount;
		this.fromAccount = fromAccount;
		this.timeOfTransaction = new Date();
		this.memo = "";
	}
	
	public Transaction(double amount, Account fromAccount, String memo) {
		this(amount, fromAccount);
		
		this.memo = memo;
	}

	public double getAmount() {
		return amount;
	}

	public String getSummary() {
		return String.format("%s: %.02f, %s", timeOfTransaction.toString(), amount, memo);
	}
}
