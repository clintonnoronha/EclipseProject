package pojo;

import lombok.Data;

@Data
public class Complaint {
	
	public String receiveDate;

	public String product;
	
	public String subProduct;
	
	public String issue;
	
	public String subIssue;
	
	public String bankName;
	
	public String state;
	
	public String zipcode;
	
	public String submitMode;
	
	public String sentDate;

	public String companyResponse;
	
	public String timelyResponse;
	
	public String consumerDispute;
	
	public String complaintId;
	
	@Override
	public String toString() {
		return "Complaint [receiveDate=" + receiveDate + ", product=" + product + ", subProduct=" + subProduct
				+ ", issue=" + issue + ", subIssue=" + subIssue + ", bankName=" + bankName + ", state=" + state
				+ ", zipcode=" + zipcode + ", submitMode=" + submitMode + ", sentDate=" + sentDate
				+ ", companyResponse=" + companyResponse + ", timelyResponse=" + timelyResponse + ", consumerDispute="
				+ consumerDispute + ", complaintId=" + complaintId + "]";
	}
		
}