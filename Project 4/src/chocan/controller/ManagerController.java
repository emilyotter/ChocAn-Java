package chocan.controller;

import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;
import chocan.handler.InputHandler;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class ManagerController extends AbstractController {

	// Input Handler for this ManagerController
	private final InputHandler inputHandler;
	
	ServiceDatabase serviceDatabase;

    public ManagerController(CredentialsDatabase userDatabase, ServiceDatabase serviceDatabase) {
        super(userDatabase);
        this.serviceDatabase = serviceDatabase;
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
    	ProviderReportController providerReportController = new ProviderReportController(userDatabase, serviceDatabase); 
    	providerReportController.generateProviderReports(idList);
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
    	MemberReportController memberReportController = new MemberReportController(userDatabase, serviceDatabase);
		try {
			memberReportController.generateMemberReports(idList);
		} catch (IOException e) {
			System.out.println("Error, Stop throwing exceptions!");
		}

    }
    
    public void requestSummaryReport() {
    	//call generate summary report
    	SummaryReportController summaryReportController = new SummaryReportController(serviceDatabase);
    	summaryReportController.generateSummaryReport();
    	
    }
}
