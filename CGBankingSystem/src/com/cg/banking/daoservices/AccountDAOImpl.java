package com.cg.banking.daoservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.util.ConnectionProvider;

public class AccountDAOImpl implements AccountDAO{

	private Connection conn = ConnectionProvider.getDBConnection();

	@Override
	public long save(Account account) throws SQLException,BankingServicesDownException {
		try{
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into Account(accountno,pinnumber,status,accountBalance,accounttype) "
					+ "values (accountno.nextval,pinnumber.nextval,?,?,?)");
			pstmt.setString(1, account.getStatus());
			pstmt.setFloat(2, account.getAccountBalance());
			pstmt.setString(3, account.getAccountType());
			pstmt.executeUpdate();
			PreparedStatement pstmt2 = conn.prepareStatement("select max(accountno) from Account");
			ResultSet rs  = pstmt2.executeQuery();
			rs.next();
			account.setAccountNo(rs.getInt(1));
			conn.commit();
			return account.getAccountNo();
		}catch(SQLException e){
			conn.rollback();
			throw e;
		}
		finally{
			conn.setAutoCommit(true);
		}
	}

	@Override
	public boolean update(Account account) throws SQLException,BankingServicesDownException, AccountNotFoundException , AccountBlockedException{
		try {
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement
					("update account set accountbalance="+account.getAccountBalance()+"where accountno="+account.getAccountNo());
			pstmt.executeUpdate();
			conn.commit();
			return true;
		} catch (SQLException e) {
			conn.rollback();
			System.out.println("Update account failed");
			throw e;
		}
		finally{
			conn.setAutoCommit(true);
		}		
	}

	@Override
	public Account findOne(long accountNo) throws SQLException, AccountNotFoundException,BankingServicesDownException, AccountBlockedException {
		try {
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("select * from account where accountno="+accountNo);
			ResultSet theOne = pstmt.executeQuery();
			if(theOne.next()){
				Account account = new Account(theOne.getInt("pinNumber"), 
						theOne.getString("accountType"), theOne.getString("status"), theOne.getInt("accountBalance"));
				account.setAccountNo(accountNo);
				if(account.getStatus().equals("Blocked"))
					throw new AccountBlockedException("Blocked account. Contact BM");
				conn.commit();
				return account;
			}
			else{
				conn.rollback();
				throw new AccountNotFoundException("Account not found. Check the account number and try again.");
			}
		} catch (SQLException e) {
			conn.rollback();
			System.out.println("Finding given account failed");
			throw e;
		} catch (AccountBlockedException e) {
			System.out.println("Account blocked");
			conn.rollback();
			throw e;
		} catch ( AccountNotFoundException e) {
			System.out.println("Account missing");
			conn.rollback();
			throw e;
		}finally{
			conn.setAutoCommit(true);
		}
	}

	@Override
	public List<Account> findAllAccounts() throws SQLException,BankingServicesDownException {
		List<Account> accounts = new ArrayList<Account>();

		PreparedStatement pstmt = conn.prepareStatement("select * from account");
		ResultSet all = pstmt.executeQuery();
		while(all.next()){
			Account account = new Account(all.getInt("pinNumber"), 
					all.getString("accountType"), all.getString("status"), all.getFloat("accountBalance"));
			account.setAccountNo(all.getLong("accountNo"));
			accounts.add(account);
		}
			return accounts;
	}

	@Override
	public List<Transaction> findAllTransactions(long accountNo) throws SQLException,BankingServicesDownException, AccountNotFoundException, AccountBlockedException {
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from transaction where accountno="+accountNo);
			ResultSet all = pstmt.executeQuery();
			if(findOne(accountNo)==null)
				throw new AccountNotFoundException("Account not found. Check account number");
			while(all.next()){
				Transaction transaction = new Transaction(all.getInt("transactionid"), all.getFloat("amount"), all.getString("transactiontype"));
				transactions.add(transaction);
			}
			return transactions;
		} catch (SQLException e) {
			System.out.println("Transaction updation failed");
			throw e;
		}catch(AccountNotFoundException e){
			System.out.println("Account missing");
			throw e;
		}
	}

	
	@Override
	public boolean transactionUpdate(float amount, String transactionType,
			long accountNo) throws SQLException {
		try{
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into transaction(transactionid, accountno, amount, transactiontype) values"
					+ "(transactionid.nextval, ?,?,?)");
			pstmt.setLong(1, accountNo);
			pstmt.setFloat(2, amount);
			pstmt.setString(3, transactionType);
			pstmt.executeUpdate();
			conn.commit();
			return false;
		}catch(SQLException e){
			conn.rollback();
			throw e;
		}finally{
			conn.setAutoCommit(true);
		}
	}
}