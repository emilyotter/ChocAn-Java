package chocan.controller;

public class ProviderReportController extends AbstractReportController{
    /**
     * Parameterized constructor to set user and permission level.
     *
     * @param user       User ID
     * @param permission User's permission level to access database. Can help debug.
     */
    public ProviderReportController(String user, int permission) {
        super(user, permission);
    }

    @Override
    public void timedMethod() {

    }
}
