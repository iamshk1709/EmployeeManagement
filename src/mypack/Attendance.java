package mypack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class Attendance extends JDialog implements ActionListener {
	String batch;
	int count;
	java.sql.Date date;
	JLabel[]names,ids;
	JCheckBox[]attend;
	private JButton submit;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel lblDate;
	private JLabel tdate;
	private JButton cancel;

	public Attendance() {
		
		setTitle("Attendance Management");
		countStudents();
		getContentPane().setLayout(null);
		names=new JLabel[count];
		ids=new JLabel[count];
		attend=new JCheckBox[count];

		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBackground(new Color(135, 206, 235));
		panel1.setBounds(10, 11, 564, 45);
		getContentPane().add(panel1);

		lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(10, 11, 55, 23);
		panel1.add(lblDate);
		date=new java.sql.Date(new java.util.Date().getTime());
		tdate = new JLabel(date.toString());
		tdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tdate.setBounds(74, 11, 116, 23);
		panel1.add(tdate);

		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBackground(new Color(135, 206, 235));
		panel2.setBounds(10, 67, 564, 342);
		getContentPane().add(panel2);

		submit = new JButton("Submit");
		submit.setFont(new Font("Tahoma", Font.BOLD, 16));
		submit.setBounds(121, 427, 115, 23);
		getContentPane().add(submit);

		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		cancel.setBounds(346, 427, 115, 23);
		submit.addActionListener(this);
		cancel.addActionListener(this);
		showStudents();
		getContentPane().add(cancel);
		setSize(600, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Attendance();
	}

	void countStudents() {
		String st="select count(*) from employee_detail";
		Connection con=DBConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	}
	void showStudents() {
		Font f=new Font("Arial",Font.PLAIN,15);
		int left=5, top=5,w=80,h=30;
		String st="select * from employee_detail";
		int i=0;
		Connection con=DBConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ResultSet rs=ps.executeQuery();
			while(rs.next())  {
				attend[i]=new JCheckBox("<"+rs.getInt("eid")+">",true);
				attend[i].setOpaque(true);
				attend[i].setFont(f);
				attend[i].setForeground(Color.red);

				names[i]=new JLabel(rs.getString("ename"));

				attend[i].setBounds(left, top, w, h);
				names[i].setBounds(left+100, top, w+40, h);
				names[i].setFont(f);
				panel2.add(attend[i]);
				panel2.add(names[i]);
				top+=35;
				++i;
				if(i==9) {
					left=left+200;
					top=5;
				}
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object ob=arg0.getSource();
		if(ob==cancel) {
			dispose();
		}
		else if(ob==submit) {
			boolean w=checkAttendance();
			if(w) {
				JOptionPane.showMessageDialog(this, "Attendance already updated");
				return;
			}
			Connection con=DBConnection.connect();
			String st="insert into attendance(date,eid,name,status)values(?,?,?,?)";
			try {
				PreparedStatement ps=con.prepareStatement(st);
				for(int i=0;i<count;i++)
				{
					ps.setDate(1, date);
					ps.setInt(2, i+1);
					ps.setString(3, names[i].getText());
					if(!(attend[i].isSelected()))
						ps.setString(4,"A");
					else 
						ps.setString(4,"P");
					ps.addBatch();
				}
				ps.executeBatch();
				JOptionPane.showMessageDialog(this, "Attendance Updated");
				dispose();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	boolean checkAttendance() {
		boolean found=true;
		Connection con=DBConnection.connect();
		String st="select date from attendance where date=?";
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ps.setDate(1, date);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				found=true;
			else
				found=false;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		return found;
	}
}
