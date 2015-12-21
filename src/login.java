import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.*;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("static-access")
public class login {
	
	
	private JFrame frame;

	Connection conn=dbtest.connect();

	private JTextField Usertext;
	private JTextField Passtext;
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
		conn=dbtest.connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		
		Passtext = new JTextField();
		Passtext.setToolTipText("Password");
		Passtext.setColumns(10);
		Passtext.setBounds(166, 86, 86, 20);
		frame.getContentPane().add(Passtext);
		
		JButton loginbtn = new JButton("Log In ");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement stmt = null;
				 ResultSet rs = null;
				String sql="SELECT * FROM  accounts WHERE username=? and password=? ";
				try{
					stmt = conn.prepareStatement(sql);
					
					((PreparedStatement) stmt).setString(1, Usertext.getText() );
					((PreparedStatement) stmt).setString(2, Passtext.getText() );
					rs=stmt.executeQuery(sql);
				 
			      
					 if(rs.next()){
						 JOptionPane.showMessageDialog(null,"the account is correct");
						 close();
					 }
					
					 else {
						 JOptionPane.showMessageDialog(null,"Username or Password is not correct ");
					 }
					 rs.close();
					 stmt.close();
			      
				}	
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}

			private void close() {
				// TODO Auto-generated method stub
				
			} 
		});
		loginbtn.setBounds(289, 55, 104, 51);
		frame.getContentPane().add(loginbtn);
	}

}
