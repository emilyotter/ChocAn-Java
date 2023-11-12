package chocan.menu;
import java.util.HashMap;

import chocan.controller.AbstractController;

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

        // Add options to HashMap. Use Acending order for keys.
        // Get Report and Email Report options.
        options.put(1, "Generate Report");
        options.put(2, "Email Report");        
        
        // Exit option is automatically added. No need to add it here. 
        return options;
    }

    

    @Override
    public void chooseOption(int option) {
        // Override this method to execute the option chosen by the user. Use the controller to execute the option.
        // Get Input using UserMenu.NextInt() || UserMenu.NextString() helper functions
    }


    
}
