package chocan.controller;


import chocan.database.CredentialsDatabase;

public class ProviderController extends AbstractController{

    public ProviderController(CredentialsDatabase userDatabase) {
        super(userDatabase);
    }
}
