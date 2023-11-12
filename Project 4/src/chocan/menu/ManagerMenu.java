package chocan.menu;
import java.util.HashMap;

import chocan.controller.AbstractController;
import chocan.controller.ManagerController;

public class ManagerMenu extends UserMenu {
    /**
     * Parameterized constructor to set controller.
     *
     * @param controller AbstractController, the controller for this menu. Has an subclass of AbstractController.
     */
    public ManagerMenu(AbstractController controller) {
        super(controller);
    }


    @Override
    protected HashMap<Integer, String> getOptions() {
        // Manager menu options HashMap
        HashMap<Integer, String> options = new HashMap<Integer, String>();

        options.put(1, "Request Provider Report");
        options.put(2, "Request Member Report");
        options.put(3, "Request Summary Report");
        //options.put(4, "Email Report");        
        
        // Exit option is automatically added. No need to add it here. 
        return options;
    }

    

    @Override
    public void chooseOption(int option) {
        // Override this method to execute the option chosen by the user. Use the controller to execute the option.
        switch(option) {
        	case 1: 
        		((ManagerController) controller).requestProviderReport();
        		break;
        	case 2:
        		((ManagerController) controller).requestMemberReport();
        		break;
        	case 3:
        		((ManagerController) controller).requestSummaryReport();
        		break;
        		
        }
        
    }


    
}
