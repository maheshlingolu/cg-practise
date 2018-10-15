package com.capgemini.bank.bean;

public class DemandDraft {
	private int transactionId, ddCommission;
	private long ddAmount;
	private String customerName, inFavorOf, phoneNo, date, ddDescription;
	//Default Con.
	public DemandDraft() {
		super();
	}
	//Parametric Con
	public DemandDraft(int ddCommission, long ddAmount, String customerName,
			String inFavorOf, String phoneNo, String date, String ddDescription) {
		super();
		this.ddCommission = ddCommission;
		this.ddAmount = ddAmount;
		this.customerName = customerName;
		this.inFavorOf = inFavorOf;
		this.phoneNo = phoneNo;
		this.date = date;
		this.ddDescription = ddDescription;
	}
	//toString
	@Override
	public String toString() {
		return "transactionId=" + transactionId
				+ ", ddCommission=" + ddCommission + ", ddAmount=" + ddAmount
				+ ", customerName=" + customerName + ", inFavorOf=" + inFavorOf
				+ ", phoneNo=" + phoneNo + ", date=" + date
				+ ", ddDescription=" + ddDescription;
	}
	//GetterSetters
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getDdCommission() {
		return ddCommission;
	}
	public void setDdCommission(int ddCommission) {
		this.ddCommission = ddCommission;
	}
	public long getDdAmount() {
		return ddAmount;
	}
	public void setDdAmount(long ddAmount) {
		this.ddAmount = ddAmount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getInFavorOf() {
		return inFavorOf;
	}
	public void setInFavorOf(String inFavorOf) {
		this.inFavorOf = inFavorOf;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDdDescription() {
		return ddDescription;
	}
	public void setDdDescription(String ddDescription) {
		this.ddDescription = ddDescription;
	}
}