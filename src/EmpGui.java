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
	static ResultSet ps;
	static Connection conn=null;
	private static JTable LiveTable;
	
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpGui window = new EmpGui();
					window.frame.setVisible(true);
					UpdateJtable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public static void UpdateJtable()
	{
		conn = (Connection) dbtest.connect();
		String sql="Select LiveName,TicketPrice,Location,Seats from Sunavlies";
		try{
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql); 
			ps=pst.executeQuery();
			LiveTable.setModel(DbUtils.resultSetToTableModel(ps));
				
		}	
		catch(Exception e1){
			JOptionPane.showMessageDialog(null, e1	);
		}
	}
	public EmpGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public String valueSelected() {
        String selectedData = null;

        int[] selectedRow = LiveTable.getSelectedRows();
        int[] selectedColumns = LiveTable.getSelectedColumns();

        for (int i = 0; i < selectedRow.length; i++) {
          for (int j = 0; j < selectedColumns.length; j++) {
            selectedData = (String) LiveTable.getValueAt(selectedRow[i], selectedColumns[j]);
          }
        }
        return selectedData;
        
      }
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 516, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "name_11652493827729");
		panel.setLayout(null);
		
		JScrollPane Tablepane = new JScrollPane();
		Tablepane.setBounds(24, 21, 431, 155);
		panel.add(Tablepane);
		
		LiveTable = new JTable();
		Tablepane.setViewportView(LiveTable);
		
		JButton BookNow = new JButton("Book Now");
		BookNow.setBounds(60, 255, 148, 50);
		panel.add(BookNow);
		
		JButton Exit = new JButton("Exit");
		Exit.setBounds(278, 255, 148, 50);
		panel.add(Exit);
		
		
		
	}
}
