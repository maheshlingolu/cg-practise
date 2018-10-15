package com.capgemini.bank.service;

import com.capgemini.bank.bean.DemandDraft;
import com.capgemini.bank.exception.BankingServicesDownException;

public interface IDemandDraftService {
	int addDemandDraftDetails(DemandDraft demandDraft) throws BankingServicesDownException;
	DemandDraft getDemandDraftDetails(int transactionId) throws BankingServicesDownException;
}