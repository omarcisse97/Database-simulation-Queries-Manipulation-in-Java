import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;  
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.lang.String;
import java.io.FileWriter;
import java.util.Random;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.sql.*;
public class Main implements ActionListener {
	public static void main(String[] args) {
		
	    final JFrame newApp = new JFrame("Project 3 - SQL Client App - (MJL - CNT 4714 - Fall 2021");
	    
	    Border blackline = BorderFactory.createLineBorder(Color.black);
	    newApp.setDefaultCloseOperation(3);
	    newApp.setBounds(500,500,1000,1200);
	    final JPanel mainP = new JPanel();
	    mainP.setBackground(Color.white);
	    newApp.setContentPane(mainP);
	    mainP.setLayout((LayoutManager)null);
	    JLabel entDI = new JLabel("Enter Database Information");
	    entDI.setForeground(Color.blue);
	    entDI.setHorizontalAlignment(4);
	    entDI.setBounds(-97,0,266,14);
	    mainP.add(entDI);
	    JLabel jd8C = new JLabel("JDBC Driver");
	    jd8C.setOpaque(true);
	    jd8C.setForeground(Color.black);
	    jd8C.setBackground(Color.lightGray);
	    jd8C.setHorizontalAlignment(4);
	    jd8C.setBounds(-180, 16, 266, 14);
	    mainP.add(jd8C);
	    JLabel dataUrl = new JLabel("Database URL");
	    dataUrl.setOpaque(true);
	    dataUrl.setForeground(Color.black);
	    dataUrl.setBackground(Color.lightGray);
	    dataUrl.setHorizontalAlignment(4);
	    dataUrl.setBounds(-180, 65, 266, 14);
	    mainP.add(dataUrl);
	    JLabel UserName = new JLabel("Username");
	    UserName.setOpaque(true);
	    UserName.setForeground(Color.black);
	    UserName.setBackground(Color.lightGray);
	    UserName.setHorizontalAlignment(4);
	    UserName.setBounds(-180, 116, 266, 14);
	    mainP.add(UserName);
	    JLabel passWord = new JLabel("Password");
	    passWord.setOpaque(true);
	    passWord.setForeground(Color.black);
	    passWord.setBackground(Color.lightGray);
	    passWord.setHorizontalAlignment(4);
	    passWord.setBounds(-180, 172, 266, 14);
	    mainP.add(passWord);
	    
	    JComboBox<String> driver, dbURL;
	    driver = new JComboBox<String>();
	    dbURL = new JComboBox<String>();
	    driver.addItem("com.mysql.cj.jdbc.Driver");
	    driver.addItem("oracle.jdbc.driver.OracleDriver");
	    driver.addItem("com.ibm.db2.jdbc.netDB2Driver");
	    driver.addItem("com.jdbc.odbc.jdbcOdbcDriver");
	    driver.setBounds(96,16,350,20);
	    mainP.add(driver);
	    dbURL.addItem("jdbc:mysql://localhost:3306/project3?useTimezone=true&serverTimezone=UTC");
	    dbURL.addItem("jdbc:mysql://localhost:3306/bikedb?useTimezone=true&serverTimezone=UTC");
	    dbURL.addItem("jdbc:mysql://localhost:3306/test?useTimezone=true&serverTimezone=UTC");
	    dbURL.setBounds(96,65,350,20);
	    mainP.add(dbURL);
	    
	    final JTextField nOi = new JTextField();
	    final JTextField iD = new JTextField();
	    final JTextField usr = new JTextField();
	    usr.setBounds(96, 116, 350, 20);
	    mainP.add(usr);
	    usr.setColumns(10);
	    final JPasswordField psd = new JPasswordField();
	    psd.setBounds(96, 172, 350, 20);
	    mainP.add(psd);
	    psd.setColumns(10);
	    
	    JLabel sqlC = new JLabel("Enter An SQL Command");
	    sqlC.setForeground(Color.blue);
	    sqlC.setHorizontalAlignment(4);
	    sqlC.setBounds(350,0,266,15);
	    mainP.add(sqlC);
	    JTextArea cmnd = new JTextArea();
	    cmnd.setBorder(blackline);
	    cmnd.setBounds(480,16,500,200);
	    mainP.add(cmnd);
	    final JButton clearC = new JButton("Clear SQL Command");
	    clearC.setForeground(Color.red);
	    clearC.setBounds(520,220,180,25);
	    clearC.setBorderPainted(false);
	    clearC.setBackground(Color.white);
	    mainP.add(clearC);
	    final JButton executeQ = new JButton("Execute SQL Command");
	    executeQ.setBorderPainted(false);
	    executeQ.setBackground(Color.green);
	    executeQ.setBounds(760,220,180,25);
	    mainP.add(executeQ);
	    final JButton connectDB = new JButton("Connect to Database");
	    connectDB.setBackground(Color.blue);
	    connectDB.setForeground(Color.white);
	    connectDB.setBounds(15,250,180,30);
	    mainP.add(connectDB);
	    JTextField connectStatus = new JTextField("No Connection Now");
	    connectStatus.setBackground(Color.black);
	    connectStatus.setForeground(Color.red);
	    connectStatus.setBounds(210,250,750,28);
	    mainP.add(connectStatus);
	    JLabel execAr = new JLabel("SQL Execution Result Window");
	    execAr.setForeground(Color.blue);
	    execAr.setBounds(50,300,266,14);
	    mainP.add(execAr);
	    JPanel tt = new JPanel();
	    tt.setBackground(Color.white);
	    tt.setBounds(50,320,900,430);
	    tt.setBorder(blackline);
	    mainP.add(tt);
	    JTable test = new JTable();
	    
	    JScrollPane scroll = new JScrollPane(test);
	    test.setVisible(true);
		scroll.setVisible(true);
	    tt.add(scroll);
	    final JButton clrRsl = new JButton("Clear Result Window");
	    clrRsl.setBackground(Color.yellow);
	    clrRsl.setBounds(20,760,180,25);
	    mainP.add(clrRsl);
	    JButton newOrder = new JButton("New Order");
	    JButton exit = new JButton("Exit");
	    executeQ.setEnabled(false);
	    clearC.setEnabled(false);
	    tt.setEnabled(false);
	    clrRsl.setEnabled(false);
	    cmnd.setEnabled(false);
	    
	    
	    
	   
	    
	   
	    connectDB.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  java.util.Date  date = new java.util.Date();
	        	  String temp = (String) driver.getSelectedItem();
	        	  String temp1 = (String) dbURL.getSelectedItem();
	        	  try {
					Class.forName(temp);
					try {
						global.connection = DriverManager.getConnection(temp1,usr.getText(),psd.getText());
						connectStatus.setText("Connected to " + temp1);
						connectDB.setEnabled(false);
						executeQ.setEnabled(true);
						cmnd.setEnabled(true);
						tt.setEnabled(true);
						cmnd.setEditable(true);
						driver.setEditable(false);
						dbURL.setEditable(false);
						usr.setEditable(false);
						psd.setEditable(false);
						driver.setEnabled(false);
						dbURL.setEnabled(false);
						psd.setEnabled(false);
						usr.setEnabled(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(mainP,"Password or User Name is incorrect.Please try again","Login Error",JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(mainP,"Could not find the Driver. Please select a working driver.","Driver Error",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
	        	   
	          }
	        });
	    executeQ.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearC.setEnabled(true);
				clrRsl.setEnabled(true);
				if(usr.getText().equals("client")) {
					int gh = 0;
					String[] temp = cmnd.getText().split(" ");
					for(int i=0;i<temp.length;i++) {
						if(temp[i].equals("set")|| temp[i].equals("insert") || temp[i].equals("delete") || temp[i].equals("update")) {
							gh++;
							if(temp[i].equals("update")) {
								String opg = "";
								opg += temp[i] + " command denied to user 'client@localhost' for table" + temp[i+1];
								JOptionPane.showMessageDialog(mainP,opg,"Database Error",JOptionPane.ERROR_MESSAGE);
							} else if(temp[i].equals("insert")) {
								String opg = "";
								opg += temp[i] + " command denied to user 'client@localhost' for table" + temp[i+2];
								JOptionPane.showMessageDialog(mainP,opg,"Database Error",JOptionPane.ERROR_MESSAGE);
							} else if(temp[i].equals("delete")) {
								String opg = "";
								opg += temp[i] + " command denied to user 'client@localhost' for table" + temp[i+2];
								JOptionPane.showMessageDialog(mainP,opg,"Database Error",JOptionPane.ERROR_MESSAGE);
							} else if(temp[i].equals("set")) {
								String opg = "";
								opg += temp[i] + " command denied to user 'client@localhost' for table" + temp[i+2];
								JOptionPane.showMessageDialog(mainP,opg,"Database Error",JOptionPane.ERROR_MESSAGE);
							}
							
						}
						if(gh ==0) {
							try 
							{
								StringBuilder firstLetter = new StringBuilder();
							    firstLetter.append(cmnd.getText().charAt(0));
							    if(firstLetter.toString().equalsIgnoreCase("s"))
								{
									test.setModel(new ResultSetTableModel(cmnd.getText()));
									if(test.getRowCount() == 0)
									{
										throw new SQLException("No Results Found!");
									}
									test.setVisible(true);
									scroll.setVisible(true);
									System.out.println("Printing");
									
									
									
								}
								else
								{
									new ResultSetTableModel(cmnd.getText());
								}
							} 
							catch (ClassNotFoundException | SQLException | StringIndexOutOfBoundsException e1)
							{
								if(e1.getMessage().contains("String index out of range"))
									JOptionPane.showMessageDialog(mainP,"Please Enter a Query","Error",JOptionPane.ERROR_MESSAGE);
									
								else
									JOptionPane.showMessageDialog(mainP,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
									
							}
						}
					}
					
				} else  {
					try 
					{
						StringBuilder firstLetter = new StringBuilder();
					    firstLetter.append(cmnd.getText().charAt(0));
					    if(firstLetter.toString().equalsIgnoreCase("s"))
						{
							test.setModel(new ResultSetTableModel(cmnd.getText()));
							if(test.getRowCount() == 0)
							{
								throw new SQLException("No Results Found!");
							}
							test.setVisible(true);
							scroll.setVisible(true);
							System.out.println("Printing");
							
							
							
						}
						else
						{
							new ResultSetTableModel(cmnd.getText());
						}
					} 
					catch (ClassNotFoundException | SQLException | StringIndexOutOfBoundsException e1)
					{
						if(e1.getMessage().contains("String index out of range"))
							JOptionPane.showMessageDialog(mainP,"Please Enter a Query","Error",JOptionPane.ERROR_MESSAGE);
							
						else
							JOptionPane.showMessageDialog(mainP,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
							
					}
				}
				
			
				
			}
	    	
	    });
	    
	    
	    
	    
	    clearC.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	cmnd.setText("");
	            clearC.setEnabled(false);
	          }
	        });
	    clrRsl.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	            test.setVisible(false);
	            //scroll.setVisible(false);
	            clrRsl.setEnabled(false);
	          }
	        });
	   
	    mainP.add(newOrder);
	    mainP.add(exit);
	    newApp.setVisible(true);
	  }
	  
	  public void actionPerformed(ActionEvent e) {}
	}


