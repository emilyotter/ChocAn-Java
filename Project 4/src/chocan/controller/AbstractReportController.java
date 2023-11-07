package chocan.controller;

public abstract class AbstractReportController extends AbstractController {
    /**
     * Parameterized constructor to set user and permission level.
     *
     * @param user       User ID
     * @param permission User's permission level to access database. Can help debug.
     */
    public AbstractReportController(String user, int permission) {
        super(user, permission);
    }

    public abstract void timedMethod();

}
