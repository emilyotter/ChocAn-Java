package chocan.menu;

import chocan.controller.AbstractController;
import chocan.controller.ProviderController;

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
        options.put(7, "Print Serivces");
        
        // Exit option is automatically added. No need to add it here. 
        return options;
    }

    @Override
    public void chooseOption(int option) {
        switch(option) {
            case 1:
                ((ProviderController) controller).validateMember();
                break;
            case 2:
                ((ProviderController) controller).addService();
                break;
            case 3:
                ((ProviderController) controller).updateService();
                break;
            case 4:
                ((ProviderController) controller).deleteService();
                break;
            case 5:
                ((ProviderController) controller).generateProviderReport();
                break;
            case 6:
                ((ProviderController) controller).emailProviderReport();
                break;
            case 7:
                ((ProviderController) controller).printServiceDatabase();
                break;
        }
    }

}
