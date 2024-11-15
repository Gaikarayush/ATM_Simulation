package com.atm_db_ayush;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class AtmSimulationCode{
	
	void checkBal(Scanner sc) throws ClassNotFoundException, SQLException {
		String pin,query="select balance from AtmSimulation where pin=?";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db_ayush" , "root" , "argaikar17052003@03");
		PreparedStatement ps = conn.prepareStatement(query);
		sc.nextLine();
		System.out.println("Enter your pin");
		pin = sc.nextLine();
		ps.setString(1,pin);
		ResultSet rs = ps.executeQuery();
		
		
		
		 
		if(rs.next()) {
			//if(pin.equals(rs.getString(1))) {
				System.out.println("Your available balance is:" + " " + rs.getString(1));
			//}else {
				//System.out.println("Incorrect pin");
			//}
		}else {
			System.out.println("Account does not exist");
		}
		
		rs.close();
		ps.close();
		

	}
	
	
	
	void withdrawAmt(Scanner sc) throws ClassNotFoundException, SQLException {
		String lpin,query="select balance from AtmSimulation where pin=?";
		int withd,bal;
		sc.nextLine();
		System.out.println("Enter your pin");
		lpin = sc.nextLine();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db_ayush" , "root" , "argaikar17052003@03");
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, lpin);
		ResultSet rs = ps.executeQuery();
		//sc.nextLine();
		
			if(rs.next()) {
				//if(lpin.equals(rs.getString(1))) {
					bal=rs.getInt(1);
					//System.out.println("Your available balance is:" + " " + rs.getInt(1));
					System.out.println("Enter amount to withdraw:");
					withd = sc.nextInt();
					//bal=rs.getInt(2);
					 if (withd > 0 && withd <= bal) {
			                // Calculate new balance after withdrawal
			                bal -= withd;

			                // Prepare the update query to adjust balance
			                String updateQuery = "update AtmSimulation set balance=? where pin=?";
			                PreparedStatement psUpdate = conn.prepareStatement(updateQuery);
			                    psUpdate.setInt(1, bal);
			                    psUpdate.setString(2, lpin);
			                    
			                    int i = psUpdate.executeUpdate();
			                    if (i > 0) {
			                        System.out.println("Withdrawal successful. Balance Updated.");
			                        System.out.println("Available Balance: " + bal);
			                    } else {
			                        System.out.println("Error: Could not update balance.");
			                    }
			                    
			                }else {
			                	System.out.println("Insufficient balance to withdraw.");
			            } 
			        } 
			
		rs.close();
		ps.close();
	}
	
	
	
	void depositAmt(Scanner sc) throws ClassNotFoundException, SQLException{
		String pin,query="select balance from AtmSimulation where pin=?";
		int depo,bal;
		sc.nextLine();
		System.out.println("Enter your pin");
		pin = sc.nextLine();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db_ayush" , "root" , "argaikar17052003@03");
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, pin);
		ResultSet rs = ps.executeQuery();
		//sc.nextLine();
		
			if(rs.next()) {
				//if(lpin.equals(rs.getString(1))) {
					bal=rs.getInt(1);
					//System.out.println("Your available balance is:" + " " + rs.getInt(1));
					System.out.println("Enter amount to Deposit:");
					depo = sc.nextInt();
					//bal=rs.getInt(2);
					 if (depo >= 0) {
			                // Calculate new balance after withdrawal
			                bal += depo;

			                // Prepare the update query to adjust balance
			                String updateQuery = "update AtmSimulation set balance=? where pin=?";
			                PreparedStatement psUpdate = conn.prepareStatement(updateQuery);
			                    psUpdate.setInt(1, bal);
			                    psUpdate.setString(2, pin);
			                    
			                    int i = psUpdate.executeUpdate();
			                    if (i > 0) {
			                        System.out.println("Withdrawal successful. Balance Updated.");
			                        System.out.println("Available Balance: " + bal);
			                    } else {
			                        System.out.println("Error: Could not update balance.");
			                    }
			                    
			                }
			            } 
			rs.close();
			ps.close();
			         
	}
	
	void changepin(Scanner sc) throws ClassNotFoundException, SQLException {
		String query="select pin from atmsimulation where pin=?",newpin,cnewpin;
		int i;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db_ayush" , "root" , "argaikar17052003@03");
		sc.nextLine();
		System.out.println("Enter your current pin");
		String currentpin=sc.nextLine();
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, currentpin);
		ResultSet rs = ps.executeQuery();
		
		
		
		if(rs.next()) {
		
			System.out.println("Enter New Pin");
			newpin = sc.nextLine();
			System.out.println("Confirm Pin:");
			cnewpin = sc.nextLine();
			
			if(cnewpin.equals(newpin)) {
				String query1 = "update atmsimulation set pin=? where pin=?";
				PreparedStatement psup = conn.prepareStatement(query1);
				psup.setString(1, cnewpin);
				psup.setString(2, currentpin);
				i=psup.executeUpdate();
				
				if(i>0){
					System.out.println("Pin updated Successfully");
				}else {
					System.out.println("could not update pin");
				}
			
		}else {
			System.out.println("Pin invalid");
		}
		
		
	}
		rs.close();
		ps.close();
		
		}
		
}
		


public class AtmSimulationMain {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		AtmSimulationCode a = new AtmSimulationCode();
		int i=1;
		while(i>0) {
			
		
		System.out.println("Your Options are:\n1.Check Balance\n2.Debit(Withdraw)\n3.Credit(Deposit)\n4.Change Pin\n5.Exit\nEnter your options:");
		int choice=sc.nextInt();
		
		
		switch (choice) {
		
		case 1:
			a.checkBal(sc);
			break;
		
		case 2:
			a.withdrawAmt(sc);
			
			break;
			
		case 3:
			a.depositAmt(sc);
			break;
			
		case 4:
			a.changepin(sc);
			break;
			
		case 5:
			System.out.println("Thankyou\nVisit Again...");
			i=0;
			
		default:
			System.out.println("Invalid choice...\nSelect Correct choice");
		}
		}
		
//		
		
	}
}
