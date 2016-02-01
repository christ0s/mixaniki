import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Dimension;

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

public class AdminGui {

	
	private JFrame frmAdministratorPanel;
	@SuppressWarnings("unused")
	private JPanel AdminPanel;
	private JPanel InsertPanel;
	private static JTable LiveTable;
	 Statement pst = null;
	 Statement pst2= null;
	 Statement pst3= null;
	 static ResultSet ps;
	 boolean rs ;
	 static Connection conn=null;
	
	/**
	 * Launch the application.
	 */
	public static void Main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGui window = new AdminGui();
					window.frmAdministratorPanel.setVisible(true);
					UpdateJtable();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public String valueSelected() {
        String selectedData = null;

        int[] selectedRow = LiveTable.getSelectedRows();
        int[] selectedColumns = LiveTable.getSelectedColumns();

        for (int i = 0; i < selectedRow.length; i++) {
          for (int j = 0; j < selectedColumns.length; j++) {
            selectedData = (String) LiveTable.getValueAt(selectedRow[i], 0);// Se opoiodipote cell tou jtable patisei o xristis thelw na epistrefei tin timi tou prwtou collumn me to selected row
          }
        }
        return selectedData;
        
      }
	/**
	 * Create the application.
	 */
	public AdminGui() {
		initialize();
	}
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministratorPanel = new JFrame();
		
		frmAdministratorPanel.setTitle("Administrator panel");
		frmAdministratorPanel.setBounds(100, 100, 450, 300);
		frmAdministratorPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministratorPanel.getContentPane().setLayout(new CardLayout(0, 0));
		frmAdministratorPanel.setLocationRelativeTo(null);
		
		
		
		final JPanel AdminPanel = new JPanel();
		frmAdministratorPanel.getContentPane().add(AdminPanel, "name_24230894125256");
		AdminPanel.setLayout(null);
		AdminPanel.setVisible(true);
		
		
		final JPanel InsertPanel = new JPanel();
		frmAdministratorPanel.getContentPane().add(InsertPanel, "name_24230908830568");
		InsertPanel.setLayout(null);
		InsertPanel.setVisible(false);
		
		JButton insertbtn = new JButton("Insert New");
		insertbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertPanel.setVisible(true);
				AdminPanel.setVisible(false);
			}
		});
		
		insertbtn.setBounds(10, 214, 93, 23);
		AdminPanel.add(insertbtn);
		
		
		
		JButton exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitbtn.setBounds(216, 214, 93, 23);
		AdminPanel.add(exitbtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 400, 165);
		AdminPanel.add(scrollPane);
		
		LiveTable = new JTable();
		scrollPane.setViewportView(LiveTable);
		LiveTable.setCellSelectionEnabled(true);

		

		LiveTable.setModel(new DefaultTableModel 
				(
			new Object[][] {
				
			},
			new String[] {
				
			}
		));
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateJtable();
			}
		});
		btnRefresh.setBounds(319, 214, 91, 23);
		AdminPanel.add(btnRefresh);
		
		
		JButton deletebtn = new JButton("Delete");
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			 	String live = valueSelected();
				conn = (Connection) dbtest.connect();
				String sql="DELETE  FROM Sunavlies WHERE LiveName =? ";
				try {
					if(live != null){
					PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
					pst.setString(1,live);
					rs=pst.execute();
					UpdateJtable();
					}
				 
				else{
					JOptionPane.showMessageDialog(null, "You must select live to delete ");
				}
				}
				catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
			}//apopeira gia delete
			
		});
		deletebtn.setBounds(113, 214, 93, 23);
		AdminPanel.add(deletebtn);
		
		JLabel label = new JLabel("\u039F\u03BD\u03BF\u03BC\u03B1 \u03A3\u03C5\u03BD\u03B1\u03C5\u03BB\u03B9\u03B1\u03C2");
		label.setBounds(21, 35, 109, 14);
		InsertPanel.add(label);
		
		final JTextArea livename = new JTextArea();
		livename.setBounds(152, 35, 102, 16);
		InsertPanel.add(livename);
		
		final JTextArea seatnum = new JTextArea();
		seatnum.setBounds(152, 116, 102, 16);
		InsertPanel.add(seatnum);
		
		
		final JTextArea dater = new JTextArea();
		dater.setBounds(152, 143, 102, 16);
		InsertPanel.add(dater);
		
		final JTextArea pricer = new JTextArea();
		pricer.setBounds(152, 189, 102, 16);
		InsertPanel.add(pricer);
		JLabel label_3 = new JLabel("\u03A4\u03B9\u03BC\u03B7 \u0395\u03B9\u03C3\u03B9\u03C4\u03B7\u03C1\u03B9\u03BF\u03C5");
		label_3.setBounds(21, 189, 109, 14);
		InsertPanel.add(label_3);
		
		JLabel label_4 = new JLabel("\u03A7\u03C9\u03C1\u03BF\u03C2 \u03A3\u03C5\u03BD\u03B1\u03C5\u03BB\u03B9\u03B1\u03C2");
		label_4.setBounds(21, 66, 109, 14);
		InsertPanel.add(label_4);
		
		final JTextArea Loc = new JTextArea();
		Loc.setBounds(152, 66, 102, 16);
		InsertPanel.add(Loc);
		
		JLabel label_5 = new JLabel("\u0394\u03B9\u03B5\u03C5\u03B8\u03C5\u03BD\u03C3\u03B7");
		label_5.setBounds(21, 91, 109, 14);
		InsertPanel.add(label_5);
		
		final JTextArea Adress = new JTextArea();
		Adress.setBounds(152, 91, 102, 16);
		InsertPanel.add(Adress);
		
		JLabel label_6 = new JLabel("\u03A9\u03C1\u03B1");
		label_6.setBounds(21, 166, 82, 14);
		InsertPanel.add(label_6);
		
		final JTextArea timer = new JTextArea();
		timer.setBounds(152, 166, 102, 16);
		InsertPanel.add(timer);
		
		JButton btnConfirmLive = new JButton("Confirm Live");
		btnConfirmLive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = (Connection) dbtest.connect();
				
				
				String sql = "INSERT INTO Sunavlies	 (Livename,Location,Adress,Seats,Date,Time,TicketPrice)"
						+ "values(?,?,?,?,?,?,?)";
				
                 
				try{
					PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql); 
				
					pst.setString (1, livename.getText());
					pst.setString (2, Loc.getText());
					pst.setString   (3, Adress.getText());
					pst.setInt(4, Integer.parseInt(seatnum.getText()));
					pst.setString(5,dater.getText() );
					pst.setString(6,timer.getText() );
					pst.setString   (7, pricer.getText());
					rs=pst.execute();
					InsertPanel.setVisible(false);
					AdminPanel.setVisible(true);
					UpdateJtable();
					JOptionPane.showMessageDialog(null, "Your live is saved succesfully in our Database ");
					livename.setText(null);
					Loc.setText(null);
					Adress.setText(null);
					seatnum.setText(null);
					dater.setText(null);
					timer.setText(null);
					pricer.setText(null);
				}	
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Number of Seats must be integer"	);
				}
			
				
			}
		});
		btnConfirmLive.setBounds(281, 73, 130, 33);
		InsertPanel.add(btnConfirmLive);
		
	
		
		JLabel label_1 = new JLabel("\u0391\u03C1\u03B9\u03B8\u03BC\u03BF\u03C2 \u0398\u03B5\u03C3\u03B5\u03C9\u03BD");
		label_1.setBounds(21, 116, 109, 14);
		InsertPanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u0397\u03BC\u03B5\u03C1\u03BF\u03BC\u03B7\u03BD\u03B9\u03B1");
		label_2.setBounds(21, 141, 121, 14);
		InsertPanel.add(label_2);
	
		
		JButton button_4 = new JButton("Back");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertPanel.setVisible(false);
				AdminPanel.setVisible(true);
				UpdateJtable();
				livename.setText(null);
				Loc.setText(null);
				Adress.setText(null);
				seatnum.setText(null);
				dater.setText(null);
				timer.setText(null);
				pricer.setText(null);
				
			}
		});
		button_4.setBounds(281, 145, 130, 33);
		InsertPanel.add(button_4);
		
		

		
		
		
	}
}
