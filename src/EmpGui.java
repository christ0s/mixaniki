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
import javax.swing.JTextField;
import javax.swing.Spring;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Dialog.ModalExclusionType;
public class EmpGui {

	private JFrame frame;
	Statement pst = null;
	static int ps;
	static ResultSet rs;
	static java.sql.Connection conn=null;
	private static JTable LiveTable;
	private JTextField name;
	
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
	public static void PrintInfo(){
		conn = (Connection) dbtest.connect();
		String sql="Select LiveName,Location,Adress,Date,Time,TicketPrice from Sunavlies where Livename=?";	
		try{
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql); 
			rs=pst.executeQuery();
			LiveTable.setModel(DbUtils.resultSetToTableModel(rs));
				
		}	
		catch(Exception e1){
			JOptionPane.showMessageDialog(null, e1	);
		}
	}
	public static void UpdateJtable()
	{
		conn = (Connection) dbtest.connect();
		String sql="Select LiveName,TicketPrice,Location,Seats from Sunavlies";
		try{
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql); 
			rs=pst.executeQuery();
			LiveTable.setModel(DbUtils.resultSetToTableModel(rs));
				
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
            selectedData = (String) LiveTable.getValueAt(selectedRow[i], 0); // Se opoiodipote cell tou jtable patisei o xristis thelw na epistrefei tin timi tou prwtou collumn me to selected row
          }
        }
        return selectedData;
        
      }
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 516, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setLocationRelativeTo(null);
		
		final JPanel Employpanel = new JPanel();
		frame.getContentPane().add(Employpanel, "name_11652493827729");
		Employpanel.setLayout(null);
		
		JScrollPane Tablepane = new JScrollPane();
		Tablepane.setBounds(24, 21, 431, 155);
		Employpanel.add(Tablepane);
		

		final JPanel BookingPanel = new JPanel();
		frame.getContentPane().add(BookingPanel, "name_98610045283939");
		BookingPanel.setLayout(null);
		
		final JPanel Printpanel = new JPanel();
		frame.getContentPane().add(Printpanel, "name_105956849922497");
		Printpanel.setLayout(null);
		
		name = new JTextField();
		name.setBounds(245, 74, 169, 20);
		BookingPanel.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u038C\u03BD\u03BF\u03BC\u03B1-\u0395\u03C0\u03CE\u03BD\u03B7\u03BC\u03BF");
		lblNewLabel.setBounds(61, 77, 125, 14);
		BookingPanel.add(lblNewLabel);
		final JLabel Livelbl = new JLabel("New label");
		Livelbl.setHorizontalAlignment(SwingConstants.CENTER);
		Livelbl.setFont(new Font("Stencil Std", Font.PLAIN, 16));
		Livelbl.setBounds(139, 11, 192, 39);
		BookingPanel.add(Livelbl);
		
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateJtable();
				BookingPanel.setVisible(false);
				Employpanel.setVisible(true);
			}
		});
		btnBack.setBounds(122, 193, 228, 40);
		BookingPanel.add(btnBack);
		
		
		
		LiveTable = new JTable();
		Tablepane.setViewportView(LiveTable);
		

		final JLabel ticketsleftlbl = new JLabel("");
		ticketsleftlbl.setBounds(90, 52, 46, 14);
		BookingPanel.add(ticketsleftlbl);
		
		JButton BookNow = new JButton("Book Now");
		BookNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				conn=dbtest.connect();
				String sql="SELECT Seats FROM  Sunavlies WHERE LiveName=?";
				String live;
				live =valueSelected();
				
				try{
					
					
				if (live != null){
					java.sql.PreparedStatement pst = conn.prepareStatement(sql); 
					pst.setString(1, live );
					rs=pst.executeQuery();
					rs.next();
					int seat= rs.getInt(1);
					System.out.println("row: " +seat);
					if(seat != 0){
						
				
				Livelbl.setText(String.valueOf(live));
				ticketsleftlbl.setText(String.valueOf(seat));
				name.setText(null);
				BookingPanel.setVisible(true);
				Employpanel.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null, "The live is has no tickets");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "You must select live to book ");
				}
				}
				catch (Exception e3){
					JOptionPane.showMessageDialog(null, e3	);
				}
			}
		});

		final JLabel livelbl = new JLabel("New label");
		livelbl.setHorizontalAlignment(SwingConstants.CENTER);
		livelbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		livelbl.setBounds(145, 11, 187, 51);
		Printpanel.add(livelbl);
		final JLabel namelbl = new JLabel("New label");
		namelbl.setHorizontalAlignment(SwingConstants.LEFT);
		namelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		namelbl.setBounds(184, 73, 285, 51);
		Printpanel.add(namelbl);
		
		final JLabel locationlbl = new JLabel("New label");
		locationlbl.setHorizontalAlignment(SwingConstants.LEFT);
		locationlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		locationlbl.setBounds(184, 135, 148, 51);
		Printpanel.add(locationlbl);
		
		final JLabel datelbl = new JLabel("New label");
		datelbl.setHorizontalAlignment(SwingConstants.LEFT);
		datelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		datelbl.setBounds(184, 197, 260, 51);
		Printpanel.add(datelbl);
		
		final JLabel timelbl = new JLabel("New label");
		timelbl.setHorizontalAlignment(SwingConstants.LEFT);
		timelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		timelbl.setBounds(184, 259, 260, 51);
		Printpanel.add(timelbl);
		
		JLabel label_4 = new JLabel("\u0394\u03B9\u03AD\u03B8\u03C5\u03BD\u03C3\u03B7");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(22, 135, 116, 51);
		Printpanel.add(label_4);
		
		JLabel label_6 = new JLabel("\u0397\u03BC\u03B5\u03C1\u03BF\u03BC\u03B7\u03BD\u03AF\u03B1");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_6.setBounds(22, 197, 129, 51);
		Printpanel.add(label_6);
		
		JLabel label_8 = new JLabel("\u038F\u03C1\u03B1");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_8.setBounds(10, 259, 129, 51);
		Printpanel.add(label_8);
		
		JLabel label_7 = new JLabel("\u039F\u03BD\u03BF\u03BC\u03B1/\u03BD\u03C5\u03BC\u03BF");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_7.setBounds(10, 73, 148, 51);
		Printpanel.add(label_7);
		
		final JLabel adresslbl = new JLabel("New label");
		adresslbl.setHorizontalAlignment(SwingConstants.LEFT);
		adresslbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		adresslbl.setBounds(342, 135, 148, 51);
		Printpanel.add(adresslbl);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Thank you for Booking , Greetings");
				UpdateJtable();
				Printpanel.setVisible(false);	
				Employpanel.setVisible(true);
			}
		});
		btnPrint.setBounds(307, 307, 183, 39);
		Printpanel.add(btnPrint);
		
		JLabel label = new JLabel("\u03A4\u03B9\u03BC\u03AE");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(22, 307, 97, 51);
		Printpanel.add(label);
		
		final JLabel TicketPricelbl = new JLabel("New label");
		TicketPricelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TicketPricelbl.setHorizontalAlignment(SwingConstants.LEFT);
		TicketPricelbl.setBounds(184, 313, 66, 39);
		Printpanel.add(TicketPricelbl);
		
		JLabel lblEuro = new JLabel("Euro");
		lblEuro.setBounds(228, 332, 46, 14);
		Printpanel.add(lblEuro);
		
		
		BookNow.setBounds(60, 255, 148, 50);
		Employpanel.add(BookNow);
		JButton btnBook = new JButton("Book Now");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				conn=dbtest.connect();
				String sql="Update Sunavlies set Seats=Seats-1  WHERE LiveName=?";
				String live = Livelbl.getText();
			
				try {
					PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
					pst.setString(1,live);
					ps=pst.executeUpdate();
					
					BookingPanel.setVisible(false);
					Printpanel.setVisible(true);
					livelbl.setText(Livelbl.getText());
					conn = (Connection) dbtest.connect();
					String sql1="Select LiveName,Location,Adress,Date,Time,TicketPrice from Sunavlies where Livename=?";	
					try{
						PreparedStatement pst1 = (PreparedStatement) conn.prepareStatement(sql1); 
						pst1.setString(1, Livelbl.getText() );
						rs=pst1.executeQuery();
						rs.next();
						namelbl.setText(name.getText() );
						locationlbl.setText(rs.getString("Location"));
						adresslbl.setText(rs.getString("Adress"));
						datelbl.setText(rs.getString("Date"));
						timelbl.setText(rs.getString("Time"));
						TicketPricelbl.setText(rs.getString("TicketPrice"));
						
					
					}	
					catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1	);
					}
					
					
					
					
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
			}
		});
		btnBook.setBounds(122, 129, 228, 40);
		BookingPanel.add(btnBook);
		
		JLabel lblNewLabel_1 = new JLabel("Tickets left:");
		lblNewLabel_1.setBounds(10, 52, 76, 14);
		BookingPanel.add(lblNewLabel_1);
		
		JButton Exit = new JButton("Exit");
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Exit.setBounds(278, 255, 148, 50);
		Employpanel.add(Exit);
		
		
		
		
		
		
		
	}
}
