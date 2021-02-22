package Logic;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import PwdAuth.PasswordAuthentication;

public class User {
	
	//These are the ... of the user.
	private String firstName;
	private String lastName;
	private Date dateOfVBirth;
	private String userId;
	
	//The pin needs to remain a secret so I only store its hash value.
	private String pinHash;
	
	
	//A user can have more accounts.
	private ArrayList<Account> accounts;

	
	public User(String firstName, String lastName, String pin, BankLogic usersBank) {
		this.firstName = firstName;
		this.lastName = lastName;
		try {
			PasswordAuthentication auth = new PasswordAuthentication();
			pinHash = auth.hash(pin.toCharArray());
		}
		catch(Exception e) {
			System.err.println("Error occured while hashing the user pin.");
			e.printStackTrace();
			System.exit(1);
		}
		
		this.accounts = new ArrayList<Account>();
		
		this.userId = usersBank.generateNewUserId();
		
		System.out.printf("User created: %s %s with ID: %s.\n", this.firstName, this.lastName, this.userId);
	}


	public void addAccount(Account account) {
		accounts.add(account);
	}


	public String getuserId() {
		return userId;
	}


	public boolean validatePin(String pin) {
		try {
			PasswordAuthentication auth = new PasswordAuthentication();
			return auth.authenticate(pin.toCharArray(), pinHash);
			}
			catch(Exception e) {
				System.err.println("Error occured while hashing the user pin.");
				e.printStackTrace();
				System.exit(1);
			}
		return false;
	}


	public Object getFirstName() {
		return firstName;
	}


	public void printAccountSummary() {
		System.out.println("Your account summary");
		for(Account a : accounts) {
			System.out.println(a.getSummary());
		}
		
	}


	public ArrayList<Account> getAccounts() {
		return accounts;
	}


	public void addAccountTransaction(Account from, double d, String memo) {
		from.addTransaction(d, memo);
		
	}
}
