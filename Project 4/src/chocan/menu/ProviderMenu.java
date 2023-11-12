package chocan.menu;

import chocan.controller.AbstractController;
import java.util.HashMap;

public class ProviderMenu extends UserMenu {
    /**
     * Parameterized constructor to set controller.
     *
     * @param controller AbstractController, the controller for this menu. Has an subclass of AbstractController.
     */
    public ProviderMenu(AbstractController controller) {
        super(controller);
    }

    @Override
    protected HashMap<Integer, String> getOptions() {
        // Manager menu options HashMap
        HashMap<Integer, String> options = new HashMap<Integer, String>();

        // Add options to HashMap. Use Acending order for keys.
        // Get Report and Email Report options.
        options.put(1, "Validate Member");
        options.put(2, "Add Service");
        options.put(3, "Update Service");
        options.put(4, "Delete Service");
        options.put(5, "Generate Provider Report");
        options.put(6, "Email Provider Report");
        
        // Exit option is automatically added. No need to add it here. 
        return options;
    }

    @Override
    public void chooseOption(int option) {
        // Override this method to execute the option chosen by the user. Use the controller to execute the option.
    }

}
