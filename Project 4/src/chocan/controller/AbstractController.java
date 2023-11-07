package chocan.controller;



/**
 * AbstractController.java
 * Abstract class for all controllers.

  Defines the interface for all user controllers.

 */


public abstract class AbstractController {
    
    /**
       Attributes
    */

    /* User ID */
    public String user;
    
    /* User's permission level to access database. Can help debug*/
    protected int permission;

    /**
       * Parameterized constructor to set user and permission level.
       * @param user User ID
       * @param permission User's permission level to access database. Can help debug
    */
    public AbstractController(String user, int permission) {
        this.user = user;
        this.permission = permission;
    }

    /**
       * Method to get permission level. Since attribute is protected.
    */
    public int getPermission() {
        return this.permission;
    }

}