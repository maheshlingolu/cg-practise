package com.capgemini.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.capgemini.bank.bean.DemandDraft;
import com.capgemini.bank.util.ConnectionProvider;

public class DemandDraftDAO implements IDemandDraftDao{
	private static final Logger logger = Logger.getLogger(DemandDraftDAO.class);
	private Connection conn = ConnectionProvider.getDBConnection();
	@Override
	public int addDemandDraftDetails(DemandDraft demandDraft) throws SQLException {//inserting records to sqlDB
		try
		{
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into demand_draft (transaction_id, customer_name, in_favor_of, phone_number, date_of_transaction, "
					+ "dd_amount, dd_commission, dd_description) values (transaction_id_seq.nextval,?,?,?,TO_DATE(?,'dd-MON-yy'),?,?,?)");
			pstmt.setString(1, demandDraft.getCustomerName());
			pstmt.setString(2, demandDraft.getInFavorOf());
			pstmt.setString(3, demandDraft.getPhoneNo());
			pstmt.setString(4, demandDraft.getDate());
			pstmt.setLong(5, demandDraft.getDdAmount());
			pstmt.setInt(6, demandDraft.getDdCommission());
			pstmt.setString(7, demandDraft.getDdDescription());
			pstmt.executeUpdate();

			PreparedStatement pstmt1 =conn.prepareStatement("select max(transaction_id) from demand_draft"); 
			ResultSet rs = pstmt1.executeQuery();
			rs.next();
			demandDraft.setTransactionId(rs.getInt(1));
			conn.commit();
			return demandDraft.getTransactionId();

		}catch(SQLException e){
			logger.error(e.getMessage()+" "+e.getErrorCode()+" ");
			System.out.println("Record not inserted");
			conn.rollback();
			throw e;
		}
		finally{
			conn.setAutoCommit(true);
		}
	}

	@Override
	public DemandDraft getDemandDraftDetails(int transactionId) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM DEMAND_DRAFT WHERE TRANSACTION_ID = "+transactionId);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			DemandDraft demandDraft = new DemandDraft(rs.getInt("DD_COMMISSION"), rs.getLong("DD_AMOUNT"), rs.getString("CUSTOMER_NAME"), 
					rs.getString("CUSTOMER_NAME"), rs.getString("PHONE_NUMBER"), rs.getString("DATE_OF_TRANSACTION"), rs.getString("DD_DESCRIPTION"));
			demandDraft.setTransactionId(rs.getInt("TRANSACTION_ID"));
			return demandDraft;
		}
		return null;
	}
}