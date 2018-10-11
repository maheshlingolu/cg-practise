package com.cg.banking.main;

import java.util.List;
import java.util.Scanner;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;
import com.cg.banking.services.BankingServices;
import com.cg.banking.services.BankingServicesImpl;

public class MainClass {
	public static void main(String[] args) {
		int choice;
		BankingServices services = new BankingServicesImpl();
		do{
			System.out.println("1.Open new account\n2.Deposit\n3.Withdraw\n4.Transfer funds"
					+ "\n5.Get account details\n6.Get transaction details\n7.Display all accounts\n8.Get account status\n9.Exit\nEnter your choice: ");
			choice = new Scanner(System.in).nextInt();
			switch(choice){
			case 1:  System.out.println("Enter initial balance: ");
							float initbalance = new Scanner(System.in).nextFloat();
							System.out.println("Enter account type (Fixed/Savings): ");
							String accountType= new Scanner(System.in).next();
							try {
								long accNo = services.openAccount(accountType, initbalance);
								System.out.println("Your account number: "+accNo+"\nYour pin number: "+services.getAccountDetails(accNo).getPinNumber());
							} catch (InvalidAmountException | InvalidAccountTypeException
									| BankingServicesDownException | AccountNotFoundException e) {
								System.out.println("Services down. Try Later");
							}
							break;
			case 2:	System.out.println("Enter account number: ");
							long accountNo = new Scanner(System.in).nextLong();
							System.out.println("Enter amount: ");
							float amount= new Scanner(System.in).nextFloat();
							try {
								services.depositAmount(accountNo, amount);
							} catch (AccountNotFoundException
									| BankingServicesDownException
									| AccountBlockedException e) {
								System.out.println("Services down. Try Later");
							}
							break;
			case 3:	System.out.println("Enter account number: ");
							accountNo = new Scanner(System.in).nextLong();
							System.out.println("Enter amount: ");
							amount= new Scanner(System.in).nextFloat();
							System.out.println("Enter pin number: ");
							int pinNumber= new Scanner(System.in).nextInt();
							try {
								services.withdrawAmount(accountNo, amount, pinNumber);
							}catch (AccountNotFoundException| BankingServicesDownException| AccountBlockedException | InsufficientAmountException
									| InvalidPinNumberException e) {
								System.out.println("Services down. Try Later");
							}
							break;
			case 4:	System.out.println("Enter sender account number: ");
							long accountNoFrom = new Scanner(System.in).nextLong();
							System.out.println("Enter reciever account number: ");
							long accountNoTo = new Scanner(System.in).nextLong();
							System.out.println("Enter amount: ");
							amount= new Scanner(System.in).nextFloat();
							System.out.println("Enter pin number: ");
							pinNumber= new Scanner(System.in).nextInt();
							try {
								services.fundTransfer(accountNoTo, accountNoFrom, amount, pinNumber);
							} catch (InsufficientAmountException | AccountNotFoundException| InvalidPinNumberException| BankingServicesDownException
									| AccountBlockedException e) {
								System.out.println("Services down. Try Later");
							}
							break;
			case 5:	System.out.println("Enter account number: ");
							accountNo = new Scanner(System.in).nextLong();
							try {
								System.out.println(services.getAccountDetails(accountNo).toString());
							} catch (AccountNotFoundException | BankingServicesDownException e) {
								System.out.println("Services down. Try Later");
							}
							break;
			case 6:	System.out.println("Enter account number: ");
							accountNo = new Scanner(System.in).nextLong();
							try {
								List<Transaction> transactions= services.getAccountAllTransaction(accountNo);
								transactions.forEach(transaction->System.out.println(transaction.toString()));
							} catch (BankingServicesDownException| AccountNotFoundException e) {
								System.out.println("Services down. Try Later");
							}
							break;
			case 7:	try {
										List<Account> accounts= services.getAllAccountDetails();
										accounts.forEach(account->System.out.println(account.toString()));
									} catch (BankingServicesDownException e) {
										System.out.println("Services down. Try Later");
									}
							break;
			case 8:	System.out.println("Enter account number: ");
							accountNo = new Scanner(System.in).nextLong();
							try {
								System.out.println("Account status: "+services.getAccountDetails(accountNo).getStatus());
							} catch (AccountNotFoundException| BankingServicesDownException e) {
								System.out.println("Services down. Try Later");
							}
							break;
			case 9:	System.out.println("Exiting..."); break;
			default: System.out.println("Invalid choice!");break;
			}
		}while(choice!=9);
	}
}
