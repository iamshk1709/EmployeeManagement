package mypack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Home extends JDialog implements ActionListener
{
	private JButton addemployeedetail;
	private JButton updateemployeedetail;
	private JButton delete;
	private JButton showallemployee;
	private JButton exit;
	private JButton attendance;
	private JButton showattendance;

	public Home() 
	{
		getContentPane().setLayout(null);
		
		addemployeedetail = new JButton("ADD EMPLOYEE DETAIL");
		addemployeedetail.setBounds(125, 74, 208, 36);
		getContentPane().add(addemployeedetail);
		
		JLabel lblNewLabel = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(53, 11, 343, 31);
		getContentPane().add(lblNewLabel);
		
		updateemployeedetail = new JButton("UPDATE EMPLOYEE DETAIL");
		updateemployeedetail.setBounds(125, 134, 208, 36);
		getContentPane().add(updateemployeedetail);
		
		delete = new JButton("DELETE EMPLOYEE DETAIL");
		delete.setBounds(125, 192, 208, 36);
		getContentPane().add(delete);
		
		showallemployee = new JButton("SHOW ALL EMPLOYEE\r\n");
		showallemployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		showallemployee.setBounds(125, 249, 208, 36);
		getContentPane().add(showallemployee);
		
		exit = new JButton("EXIT");
		exit.setBounds(125, 415, 208, 36);
		getContentPane().add(exit);
		
		attendance = new JButton("ATTENDANCE");
		attendance.setBounds(125, 307, 208, 36);
		getContentPane().add(attendance);
		
		showattendance = new JButton("SHOW ATTENDANCE");
		showattendance.setBounds(125, 357, 208, 36);
		getContentPane().add(showattendance);
		setSize(500, 500);
		addemployeedetail.addActionListener(this);
		updateemployeedetail.addActionListener(this);
		delete.addActionListener(this);
		showallemployee.addActionListener(this);
		attendance.addActionListener(this);
		showattendance.addActionListener(this);
		exit.addActionListener(this);
		
		setVisible(true);
	}
	public static void main(String[] args)
	{
		
		new Home();

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object ob=e.getSource();
		if(ob==addemployeedetail)
		{
			new Employeenew();
		}
		if(ob==updateemployeedetail)
		{
		  String st=JOptionPane.showInputDialog(this, "Employee id ? ");
		  try{
			  int re=Integer.parseInt(st);
			  new EmployeeUpdate(re);
		  }
		  catch(NullPointerException|NumberFormatException nf)
		  {
			  nf.printStackTrace();
			  System.out.println(nf.getMessage());
		  }
		}
		if(ob==delete)
		{
			 String st=JOptionPane.showInputDialog(this, "Employee id ? ");
			  try{
				  int re=Integer.parseInt(st);
				  delete(re);
			  }
			  catch(NullPointerException|NumberFormatException nf)
			  {
				  nf.printStackTrace();
				  System.out.println(nf.getMessage());
			  }
		}
		if(ob==showallemployee)
		{
			new Search();
		}
		if(ob==showattendance)
		{
			new ShowAttendance();
		}
		if(ob==attendance)
		{
			new Attendance();
		}
		if(ob==exit)
		{
			dispose();
		}
	}
	void delete(int x)
	{
		String st="delete from employee_detail where eid=?";
		Connection con=DBConnection.connect();
		try{
			PreparedStatement ps=con.prepareStatement(st);
			ps.setInt(1, x);
			int aa=JOptionPane.showConfirmDialog(this, "delete ? ");
			if(aa==0)
			{
				int w=ps.executeUpdate();
				if(w>0)
				
					JOptionPane.showMessageDialog(this, "Deleted");
					else
				    JOptionPane.showMessageDialog(this, "ID not found");
				
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
}
