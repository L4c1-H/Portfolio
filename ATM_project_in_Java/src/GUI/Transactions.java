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
import Logic.Transaction;
import Logic.User;

public class Transactions extends JFrame {
	
	ArrayList<JLabel> lDate;
	ArrayList<JLabel> lAmount;
	ArrayList<JLabel> lMemo;
	ArrayList<JPanel> insidePanels;
	JButton bBack;
	JPanel TransactionsPanel;
	
	static String selectedAccount;
	
	BankLogic bank;
	ATM atm;
	User currentUser;
	Account currentAccount;

	public Transactions(ATM atm, BankLogic bank, User currentUser, Account currentAccount){
	    super("Transactions");
	    this.bank = bank;
	    this.atm = atm;
	    this.currentUser = currentUser;
	    this.currentAccount = currentAccount;
	    
	    TransactionsPanel = new JPanel();
	    bBack = new JButton("Back");
	    lDate = new ArrayList<JLabel>();
	    lAmount = new ArrayList<JLabel>();
	    insidePanels = new ArrayList<JPanel>();
	    lMemo = new ArrayList<JLabel>();
	    
	    for(Transaction t: currentAccount.getTransactions()) {
	    	lDate.add(new JLabel(t.getDate().toString()));
	    	lAmount.add(new JLabel(String.valueOf(t.getAmount()) + "$"));
	    	lMemo.add(new JLabel(t.getMemo()));
	    	JPanel panel = new JPanel();
	    	panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
	    	insidePanels.add(panel);
	    }
	    
	    setSize(300,220);
	    setLocation(500,280);
	    TransactionsPanel.setLayout(new BoxLayout(TransactionsPanel, BoxLayout.PAGE_AXIS));
	    
	    for(int i = 0; i < insidePanels.size(); i++) {
	    	insidePanels.get(i).add(lDate.get(i));
	    	insidePanels.get(i).add(Box.createHorizontalGlue());
	    	insidePanels.get(i).add(lAmount.get(i));
	    	insidePanels.get(i).add(Box.createHorizontalGlue());
	    	insidePanels.get(i).add(lMemo.get(i));
	    	insidePanels.get(i).add(Box.createHorizontalGlue());
	    	TransactionsPanel.add(insidePanels.get(i));
	    }
	    
	    TransactionsPanel.add(bBack);
	    bBack.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    bBack.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  new TransactionMenu(atm, bank, currentUser);
		    	  dispose();
		      }
	    });
	    
	    getContentPane().add(TransactionsPanel);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	
	}
	
}

