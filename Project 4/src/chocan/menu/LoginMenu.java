package chocan.menu;

import chocan.controller.AbstractController;
import chocan.controller.LoginController;

import java.util.HashMap;

public class LoginMenu extends UserMenu {
    /**
     * Constructs a UserMenu with the specified controller.
     *
     * @param controller AbstractController, the controller for this menu.
     */
    public LoginMenu(AbstractController controller) {super(controller);}

    @Override
    protected HashMap<Integer, String> getOptions() {
        // Login menu options HashMap
        HashMap<Integer, String> options = new HashMap<Integer, String>();

        options.put(1, "Login");
        options.put(2, "Check on timer");
        options.put(3, "Main accounting procedure");
        // Exit option is automatically added. No need to add it here.

        return options;
    }

    @Override
    protected void chooseOption(int option) {
        switch(option) {
            case 1:
                ((LoginController) controller).login();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    
}
