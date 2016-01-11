import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class EmpGui {

	private JFrame frame;
	Statement pst = null;
	ResultSet ps;
	Connection conn=null;
	private JComboBox comboBoxLive;
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpGui window = new EmpGui();
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
	public EmpGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 516, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "name_11652493827729");
		panel.setLayout(null);
		
		
		fillComboBox();
		JComboBox comboBoxLive = new JComboBox();
		comboBoxLive.setBounds(34, 25, 179, 33);
		panel.add(comboBoxLive);
	}

	public void fillComboBox()
	{
		
		conn = (Connection) dbtest2.connect();
		String sql="Show tables  ";
		try{
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql); 
			ps=pst.executeQuery();
			while(ps.next()){
				
				
			}
		
				
		}	
		catch(Exception e1){
			JOptionPane.showMessageDialog(null, e1	);
		}
	}
}
