package fr.eservices.drive.web;

import java.util.List;

import fr.eservices.drive.dao.DataException;
import fr.eservices.drive.dao.StatusHistory;
import fr.eservices.drive.model.StatusHistoryEntity;

public interface HistorySource {
	
	List<StatusHistory> orderHistory( int orderId );
	void addHistoryStatus( int orderId, StatusHistory statusHistory ) throws DataException;
	void addHistoryStatus(int orderId, List<StatusHistory> histories) throws DataException;
	List<StatusHistoryEntity> orderHistoryAll(int orderId) throws DataException;

}
