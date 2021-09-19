package mypack;


import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class Login extends JDialog implements ActionListener 
{
	private JTextField u;
	private JPasswordField p;
	private JButton submit;

	public Login() {
		getContentPane().setBackground(SystemColor.controlHighlight);
		setTitle("LOGIN WINDOW ");
		getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(142, 31, 97, 33);
		getContentPane().add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(142, 105, 97, 33);
		getContentPane().add(lblNewLabel);
		
		u = new JTextField();
		u.setFont(new Font("Tahoma", Font.PLAIN, 19));
		u.setBounds(349, 30, 100, 30);
		getContentPane().add(u);
		u.setColumns(10);
		
		p = new JPasswordField();
		p.setBounds(349, 105, 100, 30);
		getContentPane().add(p);
		
		submit = new JButton("LOGIN");
		submit.setMnemonic('L');
		submit.setBounds(240, 178, 97, 25);
		getContentPane().add(submit);
		setSize(656,287);
		submit.addActionListener(this);
		
		getRootPane().setDefaultButton(submit);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Login();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submit) {
			Connection c=DBConnection.connect();
			String st="select * from login where username=? and password=?";
			String user=u.getText();
			String pass=new String(p.getPassword());
			try {
				PreparedStatement ps=c.prepareStatement(st);
				ps.setString(1, user);
				ps.setString(2, pass);
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
					new Home();
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "invalid");
					p.setText("");
					u.setText("");
					p.requestFocusInWindow();
					
				}
			}
			 catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
