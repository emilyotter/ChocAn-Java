package chocan.controller;

import chocan.database.CredentialsDatabase;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagerController extends AbstractController {

    public ManagerController(CredentialsDatabase userDatabase) {
        super(userDatabase);
    }
    
    public void requestProviderReport() { 
    	//call generate report for specified provider
    	List<String> idList = new ArrayList<>();
    	Scanner input = new Scanner(System.in);
    	String idNumber;
    	System.out.println("Enter All the Provider IDs that you wish to print a report for. Enter done once finished.");
    	
    	while(true) {
    		idNumber = input.nextLine();
    		
    		if("done".equalsIgnoreCase(idNumber)) {
    			break;
    		}
    		
    		idList.add(idNumber);
    	}
    	
    	ProviderReportController.generateProviderReports(idNumber);
    }
    
    public void requestMemberReport() {
    	//call generate report for specified member
    	List<String> idList = new ArrayList<>();
    	Scanner input = new Scanner(System.in);
    	String idNumber;
    	System.out.println("Enter All the Member IDs that you wish to print a report for. Enter done once finished.");
    	
    	while(true) {
    		idNumber = input.nextLine();
    		
    		if("done".equalsIgnoreCase(idNumber)) {
    			break;
    		}
    		
    		idList.add(idNumber);
    	}
    	
    	MemberReportController.generateProviderReports(idNumber);
    	
    }
    
    public void requestSummaryReport() {
    	//call generate summary report
    	SummaryReportController.generateSummaryReport();
    	
    }
}
