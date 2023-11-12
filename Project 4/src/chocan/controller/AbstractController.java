package chocan.controller;


import chocan.database.AbstractDatabase;
import chocan.database.CredentialsDatabase;

/**
 * AbstractController.java
 * Abstract class for all controllers.

  Defines the interface for all user controllers.

 */

public abstract class AbstractController {
    protected final CredentialsDatabase userDatabase;
    AbstractController(CredentialsDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

}