package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import Logic.ATM;
import Logic.BankLogic;
import Logic.User;


public class Login extends JFrame {
	JButton blogin;
	  JPanel loginpanel;
	  JTextField txuser;
	  JTextField pin;
	  JButton newUSer;
	  JLabel username;
	  JLabel password;
	  ATM atm;
	  BankLogic bank;
	  


	  public Login(ATM atm, BankLogic bank){
	    super("Login Autentification");
	    this.atm = atm;
	    this.bank = bank;

	    blogin = new JButton("Login");
	    loginpanel = new JPanel();
	    txuser = new JTextField(8);
	    PlainDocument userDocument = (PlainDocument) txuser.getDocument();
	    userDocument.setDocumentFilter(new DocumentFilter() {

            
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 8) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

        });
	    pin = new JPasswordField(4);
	    PlainDocument pinDocument = (PlainDocument) pin.getDocument();
	    pinDocument.setDocumentFilter(new DocumentFilter() {

            
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 4) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

        });
	    newUSer = new JButton("New User?");
	    username = new JLabel("Id   - ");
	    password = new JLabel("Pin  - ");

	    setSize(300,200);
	    setLocation(500,280);
	    loginpanel.setLayout (null); 


	    txuser.setBounds(70,30,150,20);
	    pin.setBounds(70,65,150,20);
	    blogin.setBounds(110,100,80,20);
	    newUSer.setBounds(110,135,80,20);
	    username.setBounds(20,28,80,20);
	    password.setBounds(20,63,80,20);

	    loginpanel.add(blogin);
	    loginpanel.add(txuser);
	    loginpanel.add(pin);
	    loginpanel.add(newUSer);
	    loginpanel.add(username);
	    loginpanel.add(password);

	    getContentPane().add(loginpanel);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);

	    blogin.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	          
	          String puname = txuser.getText();
	          String ppaswd = pin.getText();
	          
	          User authUser = atm.correctLogin(puname, ppaswd, bank);
	          
	          if(authUser == null) {
	        	  JOptionPane.showMessageDialog(null,"Please insert correct Username and Password");
	          
	        	  txuser.setText("");
	              pin.setText("");
	              txuser.requestFocus();
	          }else {
	        	  new UserMenu(atm, bank, authUser);
	        	  dispose();
	          }

	      }
	    });

	    newUSer.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		      
		    	  new Register(atm, bank);
		    	  dispose();
		      }
		      });
	  } 

}