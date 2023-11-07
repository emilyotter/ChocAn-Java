package chocan.controller;

public class MemberReportController extends AbstractReportController{
    /**
     * Parameterized constructor to set user and permission level.
     *
     * @param user       User ID
     * @param permission User's permission level to access database. Can help debug.
     */
    public MemberReportController(String user, int permission) {
        super(user, permission);
    }

    @Override
    public void timedMethod() {

    }
}
