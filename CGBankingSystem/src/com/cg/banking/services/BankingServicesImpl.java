package com.cg.banking.services;
import java.sql.SQLException;
import java.util.List;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.daoservices.AccountDAO;
import com.cg.banking.daoservices.AccountDAOImpl;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;

public class BankingServicesImpl implements BankingServices {

	AccountDAO accountDao = new AccountDAOImpl();
	@Override
	public long openAccount(String accountType, float initBalance)
			throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException {
		Account account = new Account();
		try{
			if(!accountType.equals("Savings") && !accountType.equals("Fixed"))
				throw new InvalidAccountTypeException("Account type is invalid");
			if(initBalance<=0)
				throw new InvalidAmountException("Amount should be positive");
			account.setAccountType(accountType);
			account.setAccountBalance(initBalance);
			account.setStatus("Active");
			return accountDao.save(account);
		}catch(InvalidAccountTypeException e){
			System.out.println("Invalid account type");
			throw new BankingServicesDownException("Service down. Try later");
		}catch(InvalidAmountException e){
			System.out.println("Invalid amount");
			throw new BankingServicesDownException("Service down. Try later");
		} catch (SQLException e) {
			System.out.println("SQL exception while opening");
			throw new BankingServicesDownException("Service down. Try later");
		}
	}

	@Override
	public float depositAmount(long accountNo, float amount)
			throws BankingServicesDownException {
		try {
			Account account = accountDao.findOne(accountNo);
			account.setAccountBalance(account.getAccountBalance()+amount);
			accountDao.update(account);
			accountDao.transactionUpdate(amount, "Deposit", accountNo);
			return account.getAccountBalance();
		} catch (SQLException e) {
			System.out.println("SQL Exception while deposit");
			throw new BankingServicesDownException("Service down. Try later.");
		}
		catch (AccountBlockedException e) {
			System.out.println("Account blocked while trying to deposit");
			throw new BankingServicesDownException("Service down. Try later.");
		}
		catch (AccountNotFoundException e) {
			System.out.println("Account not found while depositing");
			throw new BankingServicesDownException("Service down. Try later.");
		}
	}

	@Override
	public float withdrawAmount(long accountNo, float amount, int pinNumber) throws InsufficientAmountException,
	AccountNotFoundException, InvalidPinNumberException, BankingServicesDownException, AccountBlockedException {
		try {
			Account account = accountDao.findOne(accountNo);
			if(account.getPinNumber()!=pinNumber)
				throw new InvalidPinNumberException("Wrong pin entered. Try again");
			if(account.getAccountBalance()<amount)
				throw new InsufficientAmountException("Balance amount is low. Deposit and then try again");
			if(account.getStatus().equals("Fixed"))
				throw new BankingServicesDownException("Service down. Try later.");
			account.setAccountBalance(account.getAccountBalance()-amount);
			accountDao.update(account);
			accountDao.transactionUpdate(amount, "Withdraw", accountNo);
			return account.getAccountBalance();
		} catch (SQLException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}catch (InvalidPinNumberException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}catch (AccountBlockedException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}catch (InsufficientAmountException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}catch (AccountNotFoundException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}
	}

	@Override
	public boolean fundTransfer(long accountNoTo, long accountNoFrom, float transferAmount, int pinNumber)
			throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException,
			BankingServicesDownException, AccountBlockedException {
		try {
			Account accountFrom = accountDao.findOne(accountNoFrom);
			Account accountTo = accountDao.findOne(accountNoTo);
			if(accountFrom.getPinNumber()!=pinNumber)
				throw new InvalidPinNumberException("Wrong pin entered. Try again");
			if(accountFrom.getAccountBalance()<transferAmount)
				throw new InsufficientAmountException("Balance amount is low. Deposit and then try again");
			accountTo.setAccountBalance(accountTo.getAccountBalance()+transferAmount);
			accountFrom.setAccountBalance(accountFrom.getAccountBalance()-transferAmount);
			accountDao.update(accountTo);
			accountDao.update(accountFrom);
			accountDao.transactionUpdate(transferAmount, "Transfer", accountNoFrom);
			return true;
		} catch (SQLException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}catch (InvalidPinNumberException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}catch (AccountBlockedException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}catch (InsufficientAmountException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}catch (AccountNotFoundException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}
	}

	@Override
	public Account getAccountDetails(long accountNo) throws AccountNotFoundException, BankingServicesDownException {
		Account account;
		try {
			account = accountDao.findOne(accountNo);
		} catch (SQLException | AccountBlockedException | AccountNotFoundException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}
		return account;
	}

	@Override
	public List<Account> getAllAccountDetails() throws BankingServicesDownException {
		try {
			return accountDao.findAllAccounts();
		} catch (SQLException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}
	}

	@Override
	public List<Transaction> getAccountAllTransaction(long accountNo)
			throws BankingServicesDownException, AccountNotFoundException {
		try {
			return accountDao.findAllTransactions(accountNo);
		} catch (SQLException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}catch (AccountNotFoundException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		} catch (AccountBlockedException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}
	}

	@Override
	public String accountStatus(long accountNo)
			throws BankingServicesDownException, AccountNotFoundException, AccountBlockedException {
		try {
			Account account = accountDao.findOne(accountNo);
			return account.getStatus();
		} catch (SQLException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}catch (AccountNotFoundException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}catch (AccountBlockedException e) {
			throw new BankingServicesDownException("Service down. Try later.");
		}
	}
}