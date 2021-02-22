package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logic.ATM;
import Logic.Account;
import Logic.BankLogic;
import Logic.User;

public class UserMenu extends JFrame {
	
	JButton bTransactionHistory;
	JButton bWhitdraw;
	JButton bDeposit;
	JButton bTransfer;
	JButton bExit;
	JPanel menuPanel;
	
	JLabel welcomeMessage;
	
	BankLogic bank;
	ATM atm;
	User currentUser;
	
	public UserMenu(ATM atm, BankLogic bank, User currentUser){
	    super("Register");
	    this.bank = bank;
	    this.atm = atm;
	    this.currentUser = currentUser;
	    
	    bTransactionHistory = new JButton("Transaction History");
	    bWhitdraw = new JButton("Whitdraw");
	    bDeposit = new JButton("Deposit");
	    bTransfer = new JButton("Transfer");
	    bExit = new JButton("Exit");
	    menuPanel = new JPanel();
	    
	    welcomeMessage = new JLabel(String.format("Welcome %s!", currentUser.getFirstName()));
	    
	    setSize(300,220);
	    setLocation(500,280);
	    menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
	    
	    menuPanel.add(welcomeMessage);
	    welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
	    menuPanel.add(bTransactionHistory);
	    bTransactionHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
	    menuPanel.add(bWhitdraw);
	    bWhitdraw.setAlignmentX(Component.CENTER_ALIGNMENT);
	    menuPanel.add(bDeposit);
	    bDeposit.setAlignmentX(Component.CENTER_ALIGNMENT);
	    menuPanel.add(bTransfer);
	    bTransfer.setAlignmentX(Component.CENTER_ALIGNMENT);
	    menuPanel.add(bExit);
	    bExit.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    bExit.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  System.exit(0);
		    	  dispose();
		      }
	    });
	    
	    bDeposit.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  new Deposit(atm, bank, currentUser);
		    	  dispose();
		      }
	    });
	    
	    bWhitdraw.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  new Whitdraw(atm, bank, currentUser);
		    	  dispose();
		      }
	    });
	    
	    bTransactionHistory.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  new TransactionMenu(atm, bank, currentUser);
		    	  dispose();
		      }
	    });
	    
	    bTransfer.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  new Transfer(atm, bank, currentUser);
		    	  dispose();
		      }
	    });
	    getContentPane().add(menuPanel);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	}

}
