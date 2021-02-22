package GUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logic.ATM;
import Logic.Account;
import Logic.BankLogic;
import Logic.User;

public class TransactionMenu extends JFrame {
	
	ArrayList<JButton> bAccountNums;
	ArrayList<JLabel> lAccountBalances;
	ArrayList<JLabel> lAccountNames;
	ArrayList<JPanel> insidePanels;
	JButton bBack;
	JPanel TransactionMenuPanel;
	
	static String selectedAccount;
	
	BankLogic bank;
	ATM atm;
	User currentUser;

	public TransactionMenu(ATM atm, BankLogic bank, User currentUser){
	    super("TransactionMenu");
	    this.bank = bank;
	    this.atm = atm;
	    this.currentUser = currentUser;
	    
	    TransactionMenuPanel = new JPanel();
	    bBack = new JButton("Back");
	    bAccountNums = new ArrayList<JButton>();
	    lAccountBalances = new ArrayList<JLabel>();
	    insidePanels = new ArrayList<JPanel>();
	    lAccountNames = new ArrayList<JLabel>();
	
	    ArrayList<Account> accounts = currentUser.getAccounts();
	    
	    for(Account a: accounts) {
	    	bAccountNums.add(new JButton(a.getAccountId()));
	    	lAccountBalances.add(new JLabel(String.valueOf(a.getBalance() + "$")));
	    	lAccountNames.add(new JLabel(a.getName()));
	    	JPanel panel = new JPanel();
	    	panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
	    	insidePanels.add(panel);
	    }
	    
	    setSize(300,220);
	    setLocation(500,280);
	    TransactionMenuPanel.setLayout(new BoxLayout(TransactionMenuPanel, BoxLayout.PAGE_AXIS));
	    
	    for(int i = 0; i < insidePanels.size(); i++) {
	    	insidePanels.get(i).add(bAccountNums.get(i));
	    	insidePanels.get(i).add(Box.createHorizontalGlue());
	    	insidePanels.get(i).add(lAccountNames.get(i));
	    	insidePanels.get(i).add(Box.createHorizontalGlue());
	    	insidePanels.get(i).add(lAccountBalances.get(i));
	    	insidePanels.get(i).add(Box.createHorizontalGlue());
	    	
	    	bAccountNums.get(i).addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			    	  JButton sender = (JButton)e.getSource();
			    	  
			    	  Account selectedAccount = null;
			    	  
			    	  for(Account a : currentUser.getAccounts()) {
							if(a.getAccountId().contentEquals(sender.getText())) {
								selectedAccount = a;
								break;
							}
						}
			    	  
			    	  new Transactions(atm, bank, currentUser, selectedAccount);
			    	  dispose();
			      }
	    	});
	    	
	    	TransactionMenuPanel.add(insidePanels.get(i));
	    }
	    
	    TransactionMenuPanel.add(bBack);
	    bBack.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    bBack.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  new UserMenu(atm, bank, currentUser);
		    	  dispose();
		      }
	    });
	    
	    getContentPane().add(TransactionMenuPanel);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	
	}
	
}

