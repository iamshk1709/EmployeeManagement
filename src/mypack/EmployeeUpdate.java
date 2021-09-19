package mypack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class EmployeeUpdate extends JDialog implements ActionListener
{
	int id;
	boolean found;
	java.sql.Date date=new java.sql.Date(new java.util.Date().getTime());
	private JTextField eid;
	private JTextField ename;
	private JTextField egender;
	private JTextField edob;
	private JTextField esalary;
	private JTextField eaddress;
	private JTextField emobile;
	private JTextField eemail;
	private JTextField edesignation;
	private JButton cancel;
	private JButton submit;
	private JLabel lblDob;

	public EmployeeUpdate(int id) 
	{
		this.id=id;
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(24, 11, 107, 27);
		getContentPane().add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(24, 49, 107, 27);
		getContentPane().add(lblName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGender.setBounds(34, 87, 97, 27);
		getContentPane().add(lblGender);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(24, 201, 97, 27);
		getContentPane().add(lblAddress);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalary.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSalary.setBounds(24, 163, 97, 27);
		getContentPane().add(lblSalary);
		
		lblDob = new JLabel("DOB");
		lblDob.setHorizontalAlignment(SwingConstants.CENTER);
		lblDob.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDob.setBounds(24, 125, 107, 27);
		getContentPane().add(lblDob);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setHorizontalAlignment(SwingConstants.CENTER);
		lblMobile.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMobile.setBounds(24, 239, 97, 27);
		getContentPane().add(lblMobile);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(24, 277, 97, 27);
		getContentPane().add(lblEmail);
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesignation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDesignation.setBounds(24, 315, 97, 27);
		getContentPane().add(lblDesignation);
		
		eid = new JTextField(String.valueOf(id));
		eid.setBackground(Color.WHITE);
		
		eid.setEditable(false);
		eid.setBounds(152, 15, 179, 23);
		getContentPane().add(eid);
		eid.setColumns(10);
		
		ename = new JTextField();
		ename.setColumns(10);
		ename.setBounds(152, 53, 179, 23);
		getContentPane().add(ename);
		
		egender = new JTextField();
		egender.setColumns(10);
		egender.setBounds(152, 91, 179, 23);
		getContentPane().add(egender);
		
		edob = new JTextField(date.toString());
		edob.setColumns(10);
		edob.setBounds(152, 129, 179, 23);
		getContentPane().add(edob);
		
		esalary = new JTextField();
		esalary.setColumns(10);
		esalary.setBounds(152, 163, 179, 23);
		getContentPane().add(esalary);
		
		eaddress = new JTextField();
		eaddress.setColumns(10);
		eaddress.setBounds(152, 205, 179, 23);
		getContentPane().add(eaddress);
		
		emobile = new JTextField();
		emobile.setColumns(10);
		emobile.setBounds(152, 243, 179, 23);
		getContentPane().add(emobile);
		
		eemail = new JTextField();
		eemail.setColumns(10);
		eemail.setBounds(152, 281, 179, 23);
		getContentPane().add(eemail);
		
		edesignation = new JTextField();
		edesignation.setColumns(10);
		edesignation.setBounds(152, 319, 179, 23);
		getContentPane().add(edesignation);
		
		submit = new JButton("Submit");
		submit.setFont(new Font("Tahoma", Font.BOLD, 15));
		submit.setBounds(348, 111, 89, 55);
		getContentPane().add(submit);
		
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		cancel.setBounds(348, 173, 89, 55);
		getContentPane().add(cancel);
		setSize(500, 500);
		
		submit.addActionListener(this);
		cancel.addActionListener(this);
		fillInfo();
		if(!found)
		{
			JOptionPane.showMessageDialog(this, "ID not found");
			dispose();
			return;
		}
		setVisible(true);
	}

	public static void main(String[] args) 
	{
		new EmployeeUpdate(2);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob=e.getSource();
		if(ob==cancel)
		{
			dispose();
			
		}
		if(ob==submit)
		{
			String name=ename.getText();
			String gender=egender.getText();
			String address=eaddress.getText();
			double salary=Double.parseDouble(esalary.getText());
			String mobile=emobile.getText();
			String email=eemail.getText();
			String designation=edesignation.getText();
			String st="update employee_detail set ename=?,egender=?,address=?,esalary=?,dob=?,mobile_no=?,email=?,designation=? where eid=?	";
			Connection con=DBConnection.connect();
			try{
				PreparedStatement ps=con.prepareStatement(st);
				
				ps.setString(1, name);
				ps.setString(2, gender);
				ps.setString(3, address);
				ps.setDouble(4, salary);
				ps.setDate(5, date);
				ps.setString(6, mobile);
				ps.setString(7, email);
				ps.setString(8, designation);
				ps.setInt(9, id);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(this, "Updated");
				dispose();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
	}
	void fillInfo()
	{
		String st="select * from employee_detail where eid=?";
		Connection con=DBConnection.connect();
		try{
			PreparedStatement ps=con.prepareStatement(st);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				found=true;
				ename.setText(rs.getString("ename"));
				egender.setText(rs.getString("egender"));
				eaddress.setText(rs.getString("address"));
				esalary.setText(""+rs.getDouble("esalary"));
				emobile.setText(rs.getString("mobile_no"));
				eemail.setText(rs.getString("email"));
				edesignation.setText(rs.getString("designation"));
				
				
				
				
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
}
