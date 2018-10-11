package com.cg.banking.daoservices;

import java.sql.SQLException;
import java.util.List;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;

public interface AccountDAO {
	
	long save(Account account) throws SQLException, BankingServicesDownException;
	boolean update(Account account) throws SQLException, BankingServicesDownException, AccountNotFoundException, AccountBlockedException;
	boolean transactionUpdate(float amount, String transactionType, long accountNo) throws  AccountNotFoundException,  AccountNotFoundException, SQLException;
	Account findOne(long accountNo) throws SQLException, AccountNotFoundException,BankingServicesDownException, AccountBlockedException;
	List<Account> findAllAccounts() throws SQLException,BankingServicesDownException;
	List<Transaction> findAllTransactions(long accountNo) throws SQLException,BankingServicesDownException, AccountNotFoundException, AccountBlockedException;
}