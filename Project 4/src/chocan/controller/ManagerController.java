package chocan.controller;

import chocan.database.CredentialsDatabase;

public class ManagerController extends AbstractController {

    public ManagerController(CredentialsDatabase userDatabase) {
        super(userDatabase);
    }
    
    public void requestProviderReport() { 
    	//call generate report for specified provider
    }
    
    public void requestMemberReport() {
    	//call generate report for specified member
    }
    
    public void requestSummaryReport() {
    	//call generate summary report
    }
    
    public void logOut() {
    	// return to main menu
    }
}
