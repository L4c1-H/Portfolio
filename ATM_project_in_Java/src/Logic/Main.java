package Logic;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		BankLogic bank = new BankLogic();
		
		ATM atm = new ATM();
		
		User user = bank.addUser("Jane", "Doe", "4321");
		
		Account acc = new Account("Savings", user, bank);
		
		user.addAccount(acc);
		bank.addAccount(acc);
		
		//while(true) {
			
			atm.mainMenuPrompt(bank, scanner);
			
			//atm.printUserMenu(current, scanner);
			
		//}
	}

}
