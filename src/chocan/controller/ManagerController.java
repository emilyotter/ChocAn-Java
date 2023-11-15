package chocan.controller;

import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;
import chocan.handler.InputHandler;

import java.util.List;
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

			// Check if the ID is valid before adding it to the list.
			if (!userDatabase.entryExists(idNumber)) {
				System.out.println("Invalid ID. Please try again.");
			}
			else {
				if (!userDatabase.getRole(idNumber).equals("provider")){
					System.out.println("Invalid Role. Must be a provider. Please try again.");
					continue;	
				}
				idList.add(idNumber);
			}
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

			// Check if the ID is valid before adding it to the list.
			if (!userDatabase.entryExists(idNumber)) {
				System.out.println("Invalid ID. Please try again.");
			}
			else {
				if (!userDatabase.getRole(idNumber).equals("member")){
					System.out.println("Invalid Role. Must be a member. Please try again.");
					continue;	
				}
				idList.add(idNumber);
			}
    	}
		
    	MemberReportController memberReportController = new MemberReportController(userDatabase, serviceDatabase);
		memberReportController.generateMemberReports(idList);
    }
    
    public void requestSummaryReport() {
    	//call generate summary report
    	SummaryReportController summaryReportController = new SummaryReportController(userDatabase, serviceDatabase);
    	summaryReportController.generateSummaryReport();
    }
}
