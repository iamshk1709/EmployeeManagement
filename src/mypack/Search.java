package mypack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.DataBufferUShort;

import javax.swing.JFrame;

import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class Search extends JFrame implements ActionListener {
	boolean found;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton back;
	private JLabel img;

	public Search() {
		getContentPane().setBackground(new Color(255, 255, 224));
		setBackground(new Color(0, 255, 0));
		setForeground(new Color(139, 0, 0));
		setFont(new Font("Arial Black", Font.BOLD, 18));
		setTitle("ALL MODELS");
		setResizable(false);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane.setBounds(0, 0, 1494, 390);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.BOLD, 16));
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBackground(new Color(240, 230, 140));
		table.setForeground(new Color(0, 0, 139));
		table.setBorder(null);
		scrollPane.setViewportView(table);
		
		back = new JButton("BACK");
		back.addActionListener(this);
		back.setBackground(new Color(240, 230, 140));
		back.setFont(new Font("Times New Roman", Font.BOLD, 16));
		back.setBounds(10, 390, 111, 25);
		getContentPane().add(back);
		
		
		// TODO Auto-generated constructor stub
		
		setVisible(true);
		setSize(1500,450);
		setLocation(300,300);
		disp();
	}

	public static void main(String[] args) {
		new Search();
      }

	@Override
	public void actionPerformed(ActionEvent ar) {
		// TODO Auto-generated method stub
		Object ob=ar.getSource();
		if(ob==back){
			new Home();
			dispose();
		}
		
	}
	void disp(){
		Connection con=DBConnection.connect();
		try{
			String st="select * from employee_detail";
			PreparedStatement ps=con.prepareStatement(st);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				found=true;
				rs.previous();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
			}
			else {
				JOptionPane.showMessageDialog(this,"NO MODELS FOUND");
				return;
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	}
}
