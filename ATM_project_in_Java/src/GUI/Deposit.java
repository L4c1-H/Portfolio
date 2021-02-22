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

public class Deposit extends JFrame {
	
	ArrayList<JButton> bAccountNums;
	ArrayList<JLabel> lAccountBalances;
	ArrayList<JLabel> lAccountNames;
	ArrayList<JPanel> insidePanels;
	JButton bBack;
	JButton bOk;
	JPanel depositPanel;
	JTextField jtAmount;
	
	static String selectedAccount;
	
	BankLogic bank;
	ATM atm;
	User currentUser;

	public Deposit(ATM atm, BankLogic bank, User currentUser){
	    super("Deposit");
	    this.bank = bank;
	    this.atm = atm;
	    this.currentUser = currentUser;
	    
	    depositPanel = new JPanel();
	    bBack = new JButton("Back");
	    bOk = new JButton("OK");
	    bAccountNums = new ArrayList<JButton>();
	    lAccountBalances = new ArrayList<JLabel>();
	    insidePanels = new ArrayList<JPanel>();
	    lAccountNames = new ArrayList<JLabel>();
	    jtAmount = new JTextField();
	
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
	    depositPanel.setLayout(new BoxLayout(depositPanel, BoxLayout.PAGE_AXIS));
	    
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
			    	  selectedAccount = sender.getText();
			      }
	    	});
	    	
	    	depositPanel.add(insidePanels.get(i));
	    }
	    
	    depositPanel.add(jtAmount);
	    jtAmount.setAlignmentX(Component.CENTER_ALIGNMENT);
	    depositPanel.add(bOk);
	    bOk.setAlignmentX(Component.CENTER_ALIGNMENT);
	    depositPanel.add(bBack);
	    bBack.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    bBack.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  new UserMenu(atm, bank, currentUser);
		    	  dispose();
		      }
	    });
	    
	    bOk.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  double amount = 0;
		    	  try {
		    		  amount = Double.valueOf(jtAmount.getText());
		    	  }catch(Exception ex) {ex.printStackTrace();}
		    	  
		    	  if(amount <= 0) {
		    		  JOptionPane.showMessageDialog(null,"Please give a positive money amount OR use a . for decimals.");
		    		  jtAmount.setText("");
		    		  jtAmount.requestFocus();
		    	  }else {
		    	  
		    	  Account depositTo = null;
		    	  
		    	  for(Account a : currentUser.getAccounts()) {
						if(a.getAccountId().contentEquals(selectedAccount)) {
							depositTo = a;
							break;
						}
					}
		    	  
		    	  if(depositTo == null) {
		    		  JOptionPane.showMessageDialog(null,"Please click on the account you wan to deposit to.");
		    	  }else {
		    	  
		    		  currentUser.addAccountTransaction(depositTo, amount, "Deposit");
		    		  
		    	  new UserMenu(atm, bank, currentUser);
		    	  dispose();
		    	  }
		    	  }
		      }
	    });
	    
	    getContentPane().add(depositPanel);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	
	}
	
}
