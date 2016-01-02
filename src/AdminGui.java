import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.JScrollPane;

public class AdminGui {

	
	private JFrame frmAdministratorPanel;
	@SuppressWarnings("unused")
	private JPanel AdminPanel;
	private JPanel InsertPanel;
	private JTable LiveTable;
	 Statement pst = null;
	 Statement pst2= null;
	 Statement pst3= null;
	 ResultSet ps;
	 boolean rs ;
	 Connection conn=null;
	
	/**
	 * Launch the application.
	 */
	public static void Main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGui window = new AdminGui();
					window.frmAdministratorPanel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministratorPanel = new JFrame();
		
		frmAdministratorPanel.setTitle("Administrator panel");
		frmAdministratorPanel.setBounds(100, 100, 450, 300);
		frmAdministratorPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministratorPanel.getContentPane().setLayout(new CardLayout(0, 0));
		
		final JPanel AdminPanel = new JPanel();
		frmAdministratorPanel.getContentPane().add(AdminPanel, "name_24230894125256");
		AdminPanel.setLayout(null);
		AdminPanel.setVisible(true);
		
		final JPanel InsertPanel = new JPanel();
		frmAdministratorPanel.getContentPane().add(InsertPanel, "name_24230908830568");
		InsertPanel.setLayout(null);
		InsertPanel.setVisible(false);
		
		JButton insertbtn = new JButton("Insert");
		insertbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertPanel.setVisible(true);
				AdminPanel.setVisible(false);
			}
		});
		
		insertbtn.setBounds(25, 214, 89, 23);
		AdminPanel.add(insertbtn);
		
		JButton deletebtn = new JButton("Delete");
		deletebtn.setBounds(124, 214, 89, 23);
		AdminPanel.add(deletebtn);
		
		JButton exitbtn = new JButton("Exit");
		exitbtn.setBounds(223, 214, 89, 23);
		AdminPanel.add(exitbtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 24, 287, 165);
		AdminPanel.add(scrollPane);
		
		LiveTable = new JTable();
		scrollPane.setViewportView(LiveTable);
		LiveTable.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"Lives"
			}
		));
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conn = (Connection) dbtest2.connect();
				String sql="show tables";
				try{
					PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql); 
					ps=pst.executeQuery();
					LiveTable.setModel(DbUtils.resultSetToTableModel(ps));
						
				}	
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1	);
				}
				
				
			}
		});
		btnRefresh.setBounds(322, 24, 89, 23);
		AdminPanel.add(btnRefresh);
	
		
		JLabel label = new JLabel("\u039F\u03BD\u03BF\u03BC\u03B1 \u03A3\u03C5\u03BD\u03B1\u03C5\u03BB\u03B9\u03B1\u03C2");
		label.setBounds(21, 56, 109, 14);
		InsertPanel.add(label);
		
		final JTextArea livename = new JTextArea();
		livename.setBounds(152, 56, 102, 16);
		InsertPanel.add(livename);
		
		final JTextArea seatnum = new JTextArea();
		seatnum.setBounds(152, 92, 102, 16);
		InsertPanel.add(seatnum);
		
		
		final JTextArea timer = new JTextArea();
		timer.setBounds(152, 134, 102, 16);
		InsertPanel.add(timer);
		
		final JTextArea pricer = new JTextArea();
		pricer.setBounds(152, 176, 102, 16);
		InsertPanel.add(pricer);
		
		JButton button_3 = new JButton("Confirm The Live");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = (Connection) dbtest2.connect();
				String tablename=livename.getText();
				String seat=seatnum.getText();
				String time=timer.getText();
				String price=pricer.getText();
				
				String tableDropQuery = "DROP TABLE IF EXISTS "+tablename+"";
				String sql = "CREATE TABLE "+tablename+" ("
                 +" id bigint(255) NOT NULL AUTO_INCREMENT PRIMARY KEY,"+""
                 		+ "seats bigint(255),"
                 		+ "Times varchar(15),"	
                 		+ "Active bigint(255),"
                 		+ "Price Double)";
				String sqlupdate="INSERT INTO "+tablename+"  ( seats, Times, Active, Price) "+
                   "VALUES ("+seat+",'" +time+"',"+seat+", "+price+")";
				try{
					PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql); 
					PreparedStatement pst2 = (PreparedStatement) conn.prepareStatement(tableDropQuery); 
					PreparedStatement pst3 = (PreparedStatement) conn.prepareStatement(sqlupdate); 
					System.out.println("row: " + sqlupdate);
					System.out.println("row: " + sql);
					System.out.println("row: " +  tableDropQuery);
					rs=pst2.execute();
					rs=pst.execute();
					rs=pst3.execute();
					
					
						
				}	
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1	);
				}
			
				
			}
		});
		button_3.setBounds(281, 73, 130, 33);
		InsertPanel.add(button_3);
		
	
		
		JLabel label_1 = new JLabel("\u0391\u03C1\u03B9\u03B8\u03BC\u03BF\u03C2 \u0398\u03B5\u03C3\u03B5\u03C9\u03BD");
		label_1.setBounds(21, 92, 82, 14);
		InsertPanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u03A9\u03C1\u03B1 \u03B5\u03BD\u03B1\u03C1\u03BE\u03B7\u03C2");
		label_2.setBounds(21, 134, 82, 14);
		InsertPanel.add(label_2);
	
		
		JButton button_4 = new JButton("Back");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertPanel.setVisible(false);
				AdminPanel.setVisible(true);
				
			}
		});
		button_4.setBounds(281, 145, 130, 33);
		InsertPanel.add(button_4);
		
		
		JLabel label_3 = new JLabel("\u03A4\u03B9\u03BC\u03B7 \u0395\u03B9\u03C3\u03B9\u03C4\u03B7\u03C1\u03B9\u03BF\u03C5");
		label_3.setBounds(21, 176, 82, 14);
		InsertPanel.add(label_3);
	}
}
