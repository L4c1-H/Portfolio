package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;

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

public class Register extends JFrame {
	
	JButton bregister;
	JPanel registerpanel;
	JTextField txfirstName;
	JTextField txlastName;
	JTextField pin1;
	JTextField pin2;
	
	JLabel firstName;
	JLabel lastName;
	JLabel password1;
	JLabel password2;
	
	BankLogic bank;
	ATM atm;
	
	public Register(ATM atm, BankLogic bank){
	    super("Register");
	    this.bank = bank;
	    this.atm = atm;

	    bregister = new JButton("Register");
	    registerpanel = new JPanel();
	    txfirstName = new JTextField(8);
	    txlastName = new JTextField(8);
	    pin1 = new JPasswordField(4);
	    PlainDocument pin1Document = (PlainDocument) pin1.getDocument();
	    pin1Document.setDocumentFilter(new DocumentFilter() {

            
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 4) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

        });
	    pin2 = new JPasswordField(4);
	    PlainDocument pin2Document = (PlainDocument) pin2.getDocument();
	    pin2Document.setDocumentFilter(new DocumentFilter() {

            
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 4) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

        });
	    
	    firstName = new JLabel("First Name:");
		lastName = new JLabel("Last Name:");
		password1 = new JLabel("Pin1   - ");
		password2 = new JLabel("Pin2   - ");

	    setSize(300,200);
	    setLocation(500,280);
	    registerpanel.setLayout (null); 


	    txfirstName.setBounds(100,10,150,20);
	    txlastName.setBounds(100,40,150,20);
	    
	    pin1.setBounds(100,70,150,20);
	    pin2.setBounds(100,100,150,20);
	    
	    bregister.setBounds(110,135,80,20);
	    firstName.setBounds(20,10,80,20);
	    lastName.setBounds(20,40,80,20);
	    
	    password1.setBounds(20,70,80,20);
	    password2.setBounds(20,100,80,20);

	    registerpanel.add(bregister);
	    registerpanel.add(firstName);
	    registerpanel.add(lastName);
	    registerpanel.add(password1);
	    registerpanel.add(password2);
	    registerpanel.add(pin1);
	    registerpanel.add(pin2);
	    registerpanel.add(txfirstName);
	    registerpanel.add(txlastName);

	    getContentPane().add(registerpanel);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);

	    bregister.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	          
	          String pfirstNamme = txfirstName.getText();
	          String plastName = txlastName.getText();
	          String ppin1 = pin1.getText();
	          String ppin2 = pin2.getText();
	          
	          if(ppin1.equals(ppin2)) {
	        	  
	        	  User newUser = bank.addUser(pfirstNamme, plastName, ppin1);
	        	  JOptionPane.showMessageDialog(null,String.format("Your ID: %s" , newUser.getuserId()));
	        	  new Login(atm, bank);
	        	  dispose();

	          }else {
	        	  JOptionPane.showMessageDialog(null,"Please insert the same pin twice!");
		          
	        	  pin1.setText("");
	        	  pin2.setText("");
	        	  pin1.requestFocus();
	          }

	      }
	    });
	  } 

}
