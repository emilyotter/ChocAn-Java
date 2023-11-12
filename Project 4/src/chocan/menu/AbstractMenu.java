package chocan.menu;
import chocan.controller.AbstractController;




/**
 * AbstractMenu.java
 * Abstract class for all menus.

  Defines the interface for all user menus.

 */

public abstract class AbstractMenu {
    
    /**
       Attributes 
       controller: AbstractController, the controller for this menu. Has an subclass of AbstractController.
    */
    public AbstractController controller;

    /**
       * Parameterized constructor to set controller.
       * @param controller AbstractController, the controller for this menu. Has an subclass of AbstractController.
    */
    public AbstractMenu(AbstractController controller) {
        this.controller = controller;
    }

    /**
       * Abstract method to display the menu.
    */
    protected abstract void displayMenu();

    /**
       * Abstract to choose an option from the menu. Executes the option.
    */
    protected abstract void chooseOption(int option);


}