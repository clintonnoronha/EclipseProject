package dao;

import pojo.Complaint;

public interface ComplaintDao {
	
	void addComplaint(Complaint c);
	
	void displayComplaintsByYear(String year);
	
	void displayComplaintsByBank(String bank);
	
	void displayComplaintById(String id);
	
	void displayComplaintsByResponse(String resp);
	
	void displayComplaintsWithTimelyResponse();
	
}