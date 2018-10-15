package com.capgemini.bank.ui;

import java.util.Scanner;

import com.capgemini.bank.bean.DemandDraft;
import com.capgemini.bank.exception.BankingServicesDownException;
import com.capgemini.bank.service.DemandDraftService;
import com.capgemini.bank.service.IDemandDraftService;

public class Client {

	public static void main(String[] args) {
		IDemandDraftService services = new DemandDraftService();
		int choice;
		do{
			System.out.println("1) Enter Demand Draft Details\n2) Exit");
			choice = new Scanner(System.in).nextInt();
			switch(choice){
			case 1:	DemandDraft demandDraft = new DemandDraft();	
							System.out.print("Enter the name of the customer: ");
							demandDraft.setCustomerName(new Scanner(System.in).next());
							System.out.print("Enter the customer phone number: ");
							demandDraft.setPhoneNo(new Scanner(System.in).next());
							System.out.print("In favor of: ");
							demandDraft.setInFavorOf(new Scanner(System.in).next());
							System.out.print("Enter Demand Draft amount (in Rs): ");
							demandDraft.setDdAmount(new Scanner(System.in).nextLong());
							System.out.print("Enter Remarks: ");
							demandDraft.setDdDescription(new Scanner(System.in).next());
							try {
								System.out.println("Your Demand Draft request has been succesfully registered along with the "+services.addDemandDraftDetails(demandDraft));
							} catch (BankingServicesDownException e) {
								System.out.println("Service down. Try again later");
							}
							break;
			case 2:	System.out.println("Exiting..."); break;
			default: System.out.println("Invalid choice! Try again.");
			}
		}while(choice<2);
	}
}