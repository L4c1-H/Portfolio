import java.util.Scanner;

public class ATM {

	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		BankLogic bank = new BankLogic();
		
		User user = bank.addUser("Jane", "Doe", "4321");
		
		Account acc = new Account("", user, bank);
		
		user.addAccount(acc);
		bank.addAccount(acc);
		
		while(true) {
			
			User current = ATM.mainMenuPrompt(bank, scanner);
			
			ATM.printUserMenu(current, scanner);
			
		}
	}

	private static void printUserMenu(User current, Scanner scanner) {
		
		current.printAccountSummary();
		
		int choice;
		
		do {
			System.out.printf("Welcome %s\n", current.getFirstName());
			System.out.println(" 1) Show accunt transaction history.");
			System.out.println(" 2) Whitdraw.");
			System.out.println(" 3) Deposit.");
			System.out.println(" 4) Transfer.");
			System.out.println(" 5) Quit.");
			
			System.out.println(" Please enter your choice:");
			
			choice = scanner.nextInt();
			
			if(choice < 1 || choice > 5) 
				System.out.println("Invalid input");
			
		}while(choice < 1 || choice > 5);
		
		switch(choice) {
		case 1:ATM.showTransactionHistory(current, scanner);
				break;
		case 2:ATM.whitdraw(current, scanner);
			break;
		case 3:ATM.deposit(current, scanner);
			break;
		case 4:ATM.transfer(current, scanner);
			break;
		case 5:System.exit(1);
			break;
		}
		
		if(choice != 5) {
			ATM.printUserMenu(current, scanner);
		}
		
	}

	private static void transfer(User current, Scanner scanner) {

		String accountFrom;
		String AccountTo;
		Account from = null;
		Account to = null;
		boolean foundF = false;
		boolean foundT = false;
		do {
			
			System.out.println("Which account do you want to transfer from?");
			accountFrom = scanner.nextLine();
			
			System.out.println("Which account do you want to transfer to?");
			AccountTo = scanner.nextLine();
			
			for(Account a : current.getAccounts()) {
				if(a.getAccountId().contentEquals(accountFrom)) {
					from = a;
					foundF = true;
					
				}
				if(a.getAccountId().contentEquals(AccountTo)) {
					to = a;
					foundT = true;
					
				}
			}
			
			if(!foundF && !foundT) {
				System.out.println("Wrong account ID");
			}
			
		}while(!foundF && !foundT);
		
		double accountBalance = from.getBalance();
		double transferAmount;
		do {
			System.out.println("How much do you want to transfer?");
			transferAmount = scanner.nextDouble();
			if(transferAmount <= 0)
				System.out.println("Amount must be grater than 0");
				
		}while(transferAmount <= 0);
		
		if(accountBalance < transferAmount) {
			System.out.println("Insufficient funds!");
		}else {
			current.addAccountTransaction(from, -1 * transferAmount, String.format("Transfer to account %s.", to.getId()));
			current.addAccountTransaction(to, transferAmount, String.format("Transfer from account %s.", from.getId()));
		}
		
		ATM.printUserMenu(current, scanner);
		
	}

	private static void deposit(User current, Scanner scanner) {
		String accountFrom;
		Account from = null;
		boolean foundF = false;
		do {
			
			System.out.println("Which account do you want to transfer from?");
			accountFrom = scanner.nextLine();
			
			for(Account a : current.getAccounts()) {
				if(a.getAccountId().contentEquals(accountFrom)) {
					from = a;
					foundF = true;
					break;
				}
			}
			
			if(!foundF) {
				System.out.println("Wrong account ID");
			}
			
		}while(!foundF);
		
		double depositAmount;
		do {
			System.out.println("How much do you want to transfer?");
			depositAmount = scanner.nextDouble();
			if(depositAmount <= 0)
				System.out.println("Amount must be grater than 0");
				
		}while(depositAmount <= 0);
		
		current.addAccountTransaction(from, depositAmount, "Deposit");
		
		ATM.printUserMenu(current, scanner);
		
	}

	private static void whitdraw(User current, Scanner scanner) {
		String accountFrom;
		Account from = null;
		boolean foundF = false;
		do {
			
			System.out.println("Which account do you want to transfer from?");
			accountFrom = scanner.nextLine();
			
			for(Account a : current.getAccounts()) {
				if(a.getAccountId().contentEquals(accountFrom)) {
					from = a;
					foundF = true;
					break;
				}
			}
			
			if(!foundF) {
				System.out.println("Wrong account ID");
			}
			
		}while(!foundF);
		
		double accountBalance = from.getBalance();
		double whitdrawAmount;
		do {
			System.out.println("How much do you want to transfer?");
			whitdrawAmount = scanner.nextDouble();
			if(whitdrawAmount <= 0)
				System.out.println("Amount must be grater than 0");
				
		}while(whitdrawAmount <= 0);
		
		if(accountBalance < whitdrawAmount) {
			System.out.println("Insufficient funds!");
		}else {
			current.addAccountTransaction(from, -1 * whitdrawAmount, "Whitdraw");
		}
		
		ATM.printUserMenu(current, scanner);
		
	}

	private static void showTransactionHistory(User current, Scanner scanner) {
		
		String accountId;
		Account selected = null;
		boolean found = false;
		do {
			
			System.out.println("Which account do you want to examine?");
			accountId = scanner.nextLine();
			
			for(Account a : current.getAccounts()) {
				if(a.getAccountId().contentEquals(accountId)) {
					selected = a;
					found = true;
					break;
				}
			}
			
			if(!found) {
				System.out.println("Wrong account ID");
			}
			
		}while(!found);
		selected.printHistory();
		ATM.printUserMenu(current, scanner);
	}

	private static User mainMenuPrompt(BankLogic bank, Scanner scanner) {
		String Id;
		String pin;
		User auth;
		
		do {
			
			System.out.println("Type in the Id: ");
			Id = scanner.nextLine();
			
			System.out.println("Type in the pin: ");
			pin = scanner.nextLine();
			
			auth = bank.userLogin(Id, pin);
			
			if(auth == null)
				System.out.println("Incorrect validation data.");
				
		}while(auth == null);
		
		return auth;
	}
	
}
