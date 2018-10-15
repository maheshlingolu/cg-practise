package com.capgemini.bank.dao;

import java.sql.SQLException;

import com.capgemini.bank.bean.DemandDraft;

public interface IDemandDraftDao {
	int addDemandDraftDetails(DemandDraft demandDraft) throws SQLException;
	DemandDraft getDemandDraftDetails(int transactionId) throws SQLException;
}
