package mypack;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Employeenew extends JDialog implements ActionListener
{
	int id;
	//java.sql.Date date=new java.sql.Date(new java.util.Date().getDate());
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

	public Employeenew() 
	{
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
		
		JLabel lblDob = new JLabel("DOB");
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
		
		eid = new JTextField();
		eid.setBackground(Color.WHITE);
		eid.setText("0");
		eid.setEditable(false);
		eid.setBounds(152, 15, 175, 23);
		getContentPane().add(eid);
		eid.setColumns(10);
		
		ename = new JTextField();
		ename.setColumns(10);
		ename.setBounds(152, 53, 175, 23);
		getContentPane().add(ename);
		
		egender = new JTextField();
		egender.setColumns(10);
		egender.setBounds(152, 91, 175, 23);
		getContentPane().add(egender);
		
		edob = new JTextField();
		edob.setColumns(10);
		edob.setBounds(152, 129, 175, 23);
		getContentPane().add(edob);
		
		esalary = new JTextField();
		esalary.setColumns(10);
		esalary.setBounds(152, 163, 175, 23);
		getContentPane().add(esalary);
		
		eaddress = new JTextField();
		eaddress.setColumns(10);
		eaddress.setBounds(152, 205, 175, 23);
		getContentPane().add(eaddress);
		
		emobile = new JTextField();
		emobile.setColumns(10);
		emobile.setBounds(152, 243, 175, 23);
		getContentPane().add(emobile);
		
		eemail = new JTextField();
		eemail.setColumns(10);
		eemail.setBounds(152, 281, 175, 23);
		getContentPane().add(eemail);
		
		edesignation = new JTextField();
		edesignation.setColumns(10);
		edesignation.setBounds(152, 319, 175, 23);
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
		getID();
		setVisible(true);
	}

	public static void main(String[] args) 
	{
		new Employeenew();

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
			String date1=edob.getText();
			String mobile=emobile.getText();
			String email=eemail.getText();
			String designation=edesignation.getText();
			String st="insert into employee_detail(eid,ename,egender,address,esalary,dob,mobile_no,email,designation) values(?,?,?,?,?,?,?,?,?)	";
			Connection con=DBConnection.connect();
			try{
				PreparedStatement ps=con.prepareStatement(st);
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, gender);
				ps.setString(4, address);
				ps.setDouble(5, salary);
				ps.setString(6, date1);
				ps.setString(7, mobile);
				ps.setString(8, email);
				ps.setString(9, designation);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(this, "Info Added");
				dispose();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
	}
	void getID()
	{
		String st="select max(eid) from employee_detail";
		Connection con=DBConnection.connect();
		try{
			PreparedStatement ps=con.prepareStatement(st);
			ResultSet rs=ps.executeQuery();
			rs.next();
			id=rs.getInt(1);
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		++id;
		eid.setText(String.valueOf(id));
	}
}
