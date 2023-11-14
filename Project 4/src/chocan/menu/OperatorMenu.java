package chocan.menu;

import chocan.controller.AbstractController;
import chocan.controller.OperatorController;

import java.util.HashMap;

public class OperatorMenu extends UserMenu {
    /**
     * Parameterized constructor to set controller.
     *
     * @param controller AbstractController, the controller for this menu. Has a subclass of AbstractController.
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
        switch(option) {
            case 1:
                ((OperatorController) controller).addMember();
                break;
            case 2:
                ((OperatorController) controller).updateMember();
                break;
            case 3:
                ((OperatorController) controller).deleteMember();
                break;
            case 4:
                ((OperatorController) controller).addProvider();
                break;
            case 5:
                ((OperatorController) controller).updateProvider();
                break;
            case 6:
                ((OperatorController) controller).deleteProvider();
                break;
            case 7:
                ((OperatorController) controller).generateProviderDirectory();
                break;
        }
    }

}
