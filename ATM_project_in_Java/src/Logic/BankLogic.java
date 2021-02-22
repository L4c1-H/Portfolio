package Logic;
import java.util.ArrayList;

import SecureStringGen.RandomString;

public class BankLogic {

	private ArrayList<User> users;
	
	private ArrayList<Account> accounts;
	
	public BankLogic() {
		users = new ArrayList<User>();
		accounts = new ArrayList<Account>();
	}

	public String generateNewUserId() {
		String Id;
		
		RandomString gen = new RandomString(8);
		
		boolean notUnique = false;
		
		do {
			
			Id = gen.nextString();
			
			for(User u : this.users) {
				if(Id.compareTo(u.getuserId())== 0) {
					notUnique = true;
					break;
				}
			}
			
		}while(notUnique);
		
		return Id;
	}

	public String generateNewAccountId() {
		String Id;
		
		RandomString gen = new RandomString(12);
		
		boolean notUnique = false;
		
		do {
			
			Id = gen.nextString();
			
			for(Account a : this.accounts) {
				if(Id.compareTo(a.getAccountId())== 0) {
					notUnique = true;
					break;
				}
			}
			
		}while(notUnique);
		
		return Id;
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	
	public User addUser(String firstName, String lastName, String pin) {
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		Account newAccount = new Account("Main Account", newUser, this);
		
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);
		
		return newUser;
	}
	
	public User userLogin(String userId, String pin) {
		for(User u : this.users) {
			if(u.getuserId().compareTo(userId) == 0 && u.validatePin(pin)) {
				return u;
			}
		}
		return null;
	}
}
