package com.cg.banking.beans;

public class Account {
	private int pinNumber;
	private String accountType,status;
	private float accountBalance;
	private long accountNo;	
	//defcon
	public Account() {
		super();
	}
	//paracon
	public Account(int pinNumber, String accountType, String status,
			float accountBalance) {
		super();
		this.pinNumber = pinNumber;
		this.accountType = accountType;
		this.status = status;
		this.accountBalance = accountBalance;
	}
	//gns
	public int getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	//tostr
	@Override
	public String toString() {
		return "pinNumber=" + pinNumber + ", accountType="
				+ accountType + ", status=" + status + ", accountBalance="
				+ accountBalance + ", accountNo=" + accountNo;
	}
	
}