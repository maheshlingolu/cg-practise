package com.capgemini.bank.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.capgemini.bank.bean.DemandDraft;
import com.capgemini.bank.dao.DemandDraftDAO;
import com.capgemini.bank.dao.IDemandDraftDao;
import com.capgemini.bank.exception.BankingServicesDownException;
import com.capgemini.bank.exception.InvalidDDAmountException;
import com.capgemini.bank.exception.InvalidPhoneNumberException;

public class DemandDraftService implements IDemandDraftService{

	private static final Logger logger = Logger.getLogger(DemandDraftService.class);
	IDemandDraftDao demandDraftDAO = new DemandDraftDAO(); 
	@Override
	public int addDemandDraftDetails(DemandDraft demandDraft) throws BankingServicesDownException {
		try {
			
			//dd amount check and set
			if(demandDraft.getDdAmount()<0)
				throw new InvalidDDAmountException("Negative DD Amount entered");
			if(demandDraft.getDdAmount()<=5000)
				demandDraft.setDdCommission(10);
			else if(demandDraft.getDdAmount()>5000 && demandDraft.getDdAmount()<=10000)
				demandDraft.setDdCommission(41);
			else if(demandDraft.getDdAmount()>10000 && demandDraft.getDdAmount()<=100000)
				demandDraft.setDdCommission(51);
			else if(demandDraft.getDdAmount()>100000 && demandDraft.getDdAmount()<=500000)
				demandDraft.setDdCommission(306);
			else
				throw new InvalidDDAmountException("DD Amount above 5 lakhs");
			
			//phone number check
			if(demandDraft.getPhoneNo().length()>10)
				throw new InvalidPhoneNumberException("Phone number is longer than 10 digits");
			
			//SettingDate
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
			demandDraft.setDate(format.format(new Date()));
			return demandDraftDAO.addDemandDraftDetails(demandDraft);

		} catch (SQLException e) {
			logger.error(e.getMessage()+" "+e.getErrorCode()+" ");
			throw new BankingServicesDownException();
		} catch (InvalidDDAmountException e) {
			logger.error(e.getMessage());
			System.out.println("DD Amount should be positive and below 5 lakhs");
			throw new BankingServicesDownException();
		}catch(InvalidPhoneNumberException e){
			logger.error(e.getMessage());
			System.out.println("Phone Number is invalid");
			throw new BankingServicesDownException();
		}
	}

	@Override
	public DemandDraft getDemandDraftDetails(int transactionId) throws BankingServicesDownException{
		try {
			return demandDraftDAO.getDemandDraftDetails(transactionId);
		} catch (SQLException e) {
			throw new BankingServicesDownException();
		}
	}
}