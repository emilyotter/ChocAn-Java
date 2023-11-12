package chocan.menu;

import chocan.controller.AbstractController;
import java.util.HashMap;

public class OperatorMenu extends UserMenu {
    /**
     * Parameterized constructor to set controller.
     *
     * @param controller AbstractController, the controller for this menu. Has an subclass of AbstractController.
     */
    public OperatorMenu(AbstractController controller) {
        super(controller);
    }

    @Override
    protected HashMap<Integer, String> getOptions() {
        // Manager menu options HashMap
        HashMap<Integer, String> options = new HashMap<Integer, String>();

        // Add options to HashMap. Use Acending order for keys.
        options.put(1, "Add Member");
        options.put(2, "Update Member");
        options.put(3, "Delete Member");
        options.put(4, "Add Provider");
        options.put(5, "Update Provider");
        options.put(6, "Delete Provider");
        options.put(7, "Generate Provider Directory");
        
        // Exit option is automatically added. 

        return options;
    }

    @Override
    public void chooseOption(int option) {
        // Override this method to execute the option chosen by the user. Use the controller to execute the option.
        // Get Input using UserMenu.NextInt() || UserMenu.NextString() helper functions
    }

    
}
