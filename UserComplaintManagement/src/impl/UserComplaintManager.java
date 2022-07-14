package impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

import dao.ComplaintDao;
import pojo.Complaint;

public class UserComplaintManager implements ComplaintDao {
	
	final private static String file = "C:\\Users\\Clinton\\Desktop\\LTI Training\\Mini Project\\complaints.csv";
	
	static List<Complaint> complaintList = null;
	
	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		
		UserComplaintManager ob = new UserComplaintManager();
		
		Scanner sc = new Scanner(System.in);
		int ch;
		
		Map<String, String> columnMappings = new HashMap<>();
        columnMappings.put("rd", "receiveDate");
        columnMappings.put("prod", "product");
        columnMappings.put("subprod", "subProduct");
        columnMappings.put("issue", "issue");
        columnMappings.put("subIssue", "subIssue");
        columnMappings.put("bank", "bankName");
        columnMappings.put("state", "state");
        columnMappings.put("zipcode", "zipcode");
        columnMappings.put("mode", "submitMode");
        columnMappings.put("sd", "sentDate");
        columnMappings.put("resp", "companyResponse");
        columnMappings.put("tresp", "timelyResponse");
        columnMappings.put("cd", "consumerDispute");
        columnMappings.put("cid", "complaintId");
		
		Reader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		
		@SuppressWarnings("rawtypes")
		HeaderColumnNameTranslateMappingStrategy mappingStrategy = new HeaderColumnNameTranslateMappingStrategy();
		mappingStrategy.setColumnMapping(columnMappings);
		mappingStrategy.setType(Complaint.class);
		
		CsvToBean<Complaint> csvReader = new CsvToBeanBuilder<Complaint>(reader)
				.withType(Complaint.class)
				.withSeparator(',')
				.withIgnoreLeadingWhiteSpace(true)
				.withIgnoreEmptyLine(true)
				.withMappingStrategy(mappingStrategy)
				.build();
		
		complaintList = csvReader.parse();
		
		do {
            menu();
            System.out.print("Enter your choice : ");
            ch = sc.nextInt();
             
            switch (ch) {
                case 0:
                    System.out.println("\nThank you for using this program!\n");
                    sc.close();
                    System.exit(0);
                case 1:
                	sc.nextLine();
                    System.out.print("\nEnter bank name : ");
                    String bkName = sc.nextLine();
                    System.out.print("Enter product name : ");
                    String pName = sc.nextLine();
                    System.out.print("Enter sub-product name : ");
                    String spName = sc.nextLine();
                    System.out.print("Enter issue topic : ");
                    String issue = sc.nextLine();
                    System.out.print("Enter issue description : ");
                    String issueDesc = sc.nextLine();
                    System.out.print("Enter state name : ");
                    String state = sc.nextLine();
                    System.out.print("Enter state zipcode : ");
                    String zipcode = sc.nextLine();
                    System.out.print("Enter complaint submission mode : ");
                    String submissionMode = sc.nextLine();
                    System.out.print("Enter Company Response : ");
                    String resp = sc.nextLine();
                    System.out.print("Enter whether Response was timely (Yes/No) : ");
                    String tresp = sc.nextLine();
                    System.out.print("Enter whether there was consumer dispute (Yes/No) : ");
                    String cd = sc.nextLine();
                    System.out.print("Enter complaint id : ");
                    String cid = sc.nextLine();
                    String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    Complaint c = new Complaint();
                    c.receiveDate = date;
                    c.sentDate = date;
                    c.bankName = bkName;
                    c.product = pName;
                    c.subProduct = spName;
                    c.complaintId = cid;
                    c.consumerDispute = cd;
                    c.issue = issue;
                    c.subIssue = issueDesc;
                    c.state = state;
                    c.zipcode = zipcode;
                    c.submitMode = submissionMode;
                    c.companyResponse = resp;
                    c.timelyResponse = tresp;
                    ob.addComplaint(c);
                    break;
                case 2:
                	sc.nextLine();
                    System.out.print("\nEnter year (YYYY) : ");
                    String year = sc.nextLine();
                    ob.displayComplaintsByYear(year);
                    break;
                case 3:
                	sc.nextLine();
                    System.out.print("\nEnter bank name : ");
                    String bank = sc.nextLine();
                    ob.displayComplaintsByBank(bank);
                    break;
                case 4:
                	sc.nextLine();
                    System.out.print("\nEnter complaint id : ");
                    String id = sc.nextLine();
                    ob.displayComplaintById(id);
                    break;
                case 5:
                	sc.nextLine();
                    System.out.print("\n1: Closed\n2: Closed with explanation\n");
                    System.out.print("\nEnter your choice : ");
                    String choice = sc.nextLine();
                    if (choice.equals("1")) {
                    	ob.displayComplaintsByResponse("Closed");
                    } else if (choice.equals("2")) {
                    	ob.displayComplaintsByResponse("Closed with explanation");
                    } else {
                    	System.out.println("\nInvalid choice!\n");
                    }
                    break;
                case 6:
                    ob.displayComplaintsWithTimelyResponse();
                default:
                    System.out.println("\nInvalid Choice. Select again...\n");
                    break;
            }
        } while (ch != 0);
	}
	
	public static void menu() {
        System.out.println("1: New Complaint");
        System.out.println("2: Display Complaints by year");
        System.out.println("3: Display Complaints by Bank Name");
        System.out.println("4: Display Complaint by Complaint ID");
        System.out.println("5: Display Complaints by Response");
        System.out.println("6: Display Complaints with Timely Responses");
        System.out.println("0: Exit Program");
        System.out.println();
    }

	@Override
	public void addComplaint(Complaint c) {
		System.out.println("\nNew Complaint Added!\n");
		complaintList.add(c);
	}

	@Override
	public void displayComplaintsByYear(String year) {
		System.out.println();
    	complaintList.stream().filter(s -> s.receiveDate.endsWith(year))
		.forEach(System.out::println);
    	System.out.println();
	}

	@Override
	public void displayComplaintsByBank(String bank) {
		System.out.println();
    	complaintList.stream().filter(s -> s.bankName.equals(bank))
		.forEach(System.out::println);
    	System.out.println();
	}

	@Override
	public void displayComplaintById(String id) {
		System.out.println();
    	complaintList.stream().filter(s -> s.complaintId.equals(id))
		.forEach(System.out::println);
    	System.out.println();
	}

	@Override
	public void displayComplaintsByResponse(String resp) {
		System.out.println();
    	complaintList.stream().filter(s -> s.companyResponse.equals(resp))
		.forEach(System.out::println);
    	System.out.println();
	}

	@Override
	public void displayComplaintsWithTimelyResponse() {
		System.out.println();
    	complaintList.stream().filter(s -> s.timelyResponse.trim().equals("Yes"))
		.forEach(System.out::println);
    	System.out.println();
	}
}