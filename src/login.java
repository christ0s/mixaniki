import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.*;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class login extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String Admin = null;
	 Statement pst = null;
	 ResultSet rs = null;
	 Connection conn=null;
	
	private JFrame frame;

	private JTextField Usertext;
	private JPasswordField Passtext;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application. 
	 */
	
	
	public login() {
		initialize();
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		  
		    
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(80, 55, 104, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(80, 86, 104, 20);
		frame.getContentPane().add(lblPassword);
		
		Usertext = new JTextField();
		Usertext.setToolTipText("Username");
		Usertext.setBounds(166, 55, 86, 20);
		frame.getContentPane().add(Usertext);
		Usertext.setColumns(10);
		
		JButton loginbtn = new JButton("Log In ");
		loginbtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				conn=dbtest.connect();
				String sql="SELECT * FROM  accounts WHERE username=? and password=? ";
				
				
				String type1 = "admin";
				String type2="employ";
				
				try{
					PreparedStatement pst = conn.prepareStatement(sql); 
					
					pst.setString(1, Usertext.getText() );
					pst.setString(2, Passtext.getText() );
					
										
					rs=pst.executeQuery();
									
						if( rs.next() & type1.equals(rs.getString("type"))){
						 
						
						 AdminGui w =new AdminGui();
						 AdminGui.Main();
						 frame.setVisible(false);
					
					 }
						else if  ( type2.equals(rs.getString("type"))) 
						{
				
							 EmpGui w =new EmpGui();
							 EmpGui.main();
							 frame.setVisible(false);
							 				
						}
												
					 else {
						 
						 JOptionPane.showMessageDialog(null,"Username or Password is not correct ");
						 
					 }
					 rs.close();
					 pst.close();
					
				}	
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Username or Password is not correct"	);
				}
			}

		
		});
		loginbtn.setBounds(289, 55, 104, 51);
		frame.getContentPane().add(loginbtn);
		
		Passtext = new JPasswordField();
		Passtext.setBounds(166, 86, 86, 20);
		frame.getContentPane().add(Passtext);
	}


}
