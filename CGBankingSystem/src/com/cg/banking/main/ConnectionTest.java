package com.cg.banking.main;
import com.cg.banking.util.ConnectionProvider;
public class ConnectionTest {
	public static void main(String[] args) {
		if(ConnectionProvider.getDBConnection()!=null)
			System.out.println("I'm in!");
		else
			System.out.println("I can't get in.");
	}
}