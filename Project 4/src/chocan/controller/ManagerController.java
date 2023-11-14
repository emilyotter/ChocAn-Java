package chocan.controller;

import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;
import chocan.handler.InputHandler;

import java.util.List;
import java.util.ArrayList;

public class ManagerController extends AbstractController {

	// Input Handler for this ManagerController
	private final InputHandler inputHandler;

    public ManagerController(CredentialsDatabase userDatabase) {
        super(userDatabase);
		inputHandler = new InputHandler();
    }
    
    public void requestProviderReport() { 
    	//call generate report for specified provider
    	List<String> idList = new ArrayList<>();
    	String idNumber;
    	System.out.println("Enter All the Provider IDs that you wish to print a report for. Enter done once finished.");
    	
    	while(true) {
    		idNumber = this.inputHandler.unconstrainedPromptStr("Enter ID: ");
    		
    		if("done".equalsIgnoreCase(idNumber)) {
    			break;
    		}
    		
    		idList.add(idNumber);
    	}
    	// ProviderReportController providerReportController = new ProviderReportController(userDatabase); 
    	// providerReportController.generateProviderReports(idList);
    }
    
    public void requestMemberReport() {
    	//call generate report for specified member
    	List<String> idList = new ArrayList<>();
    	String idNumber;
    	System.out.println("Enter All the Member IDs that you wish to print a report for. Enter done once finished.");
    	
    	while(true) {
			idNumber = this.inputHandler.unconstrainedPromptStr("Enter ID: ");
    		
    		if("done".equalsIgnoreCase(idNumber)) {
    			break;
    		}
    		
    		idList.add(idNumber);
    	}
    	// MemberReportController memberReportController = new MemberReportController(userDatabase);
    	// memberReportController.generateProviderReports(idList);
    	
    }
    
    public void requestSummaryReport() {
    	//call generate summary report
    	// SummaryReportController summaryReportController = new SummaryReportController(userDatabase);
    	// summaryReportController.generateSummaryReport();
    	
    }
}
