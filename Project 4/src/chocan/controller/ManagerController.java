package chocan.controller;


import chocan.database.CredentialsDatabase;

public class ManagerController extends AbstractController {

    public ManagerController(CredentialsDatabase userDatabase) {
        super(userDatabase);
    }
}
