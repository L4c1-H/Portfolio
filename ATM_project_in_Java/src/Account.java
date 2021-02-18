import java.util.ArrayList;

public class Account {

	//A user can give a name to each of their accounts.
	private String name;
	//Balance of the account.
	private double balance;
	private String accountId;
	private User accountHolder;
	
	//The user is able to list all transactions from the account
	//so I need to store them.
	private ArrayList<Transaction> transactions;
	
	public Account(String name, User holder, BankLogic bank) {
		this.name = name;
		this.accountHolder = holder;
		
		this.transactions = new ArrayList<Transaction>();
		
		this.accountId = bank.generateNewAccountId();
	}

	public String getAccountId() {
		return accountId;
	}
	
	public double getBalance() {
		double balance = 0;
		for(Transaction t : transactions) {
			balance += t.getAmount();
		}
		return balance;
	}

	public String getSummary() {
		
		return String.format("Id: %s, name: %s : %.02f", accountId, name, getBalance());
	}

	public void printHistory() {
		for(Transaction t : transactions) {
			System.out.println(t.getSummary());
		}
		
	}

	public String getId() {
		return accountId;
	}

	public void addTransaction(double amount, String memo) {
		Transaction newT = new Transaction(amount, this, memo);
		this.addTransaction(newT);
		
	}

	private void addTransaction(Transaction transaction) {
		transactions.add(transaction);
		
	}
	
}
