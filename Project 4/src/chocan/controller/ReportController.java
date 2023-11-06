
package chocan.controller;

public class ReportController extends AbstractController{
    private final int reportType;

    /**
     * Parameterized constructor to set user and permission level.
     *
     * @param user       User ID
     * @param permission User's permission level to access database. Can help debug
     */
    public ReportController(String user, int permission, int reportType) {
        super(user, permission);
        this.reportType = reportType;
    }

    @Override
    public void abstractTimedMethod() {
        switch (reportType) {
            case 0:
                System.out.print("Generating Summary Report\n");
                break;

            case 1:
                System.out.print("Generating Member Report\n");
                break;

            case 2:
                System.out.print("Generating Provider Report\n");
                break;

            default:
                System.out.print("No report type defined");
                break;
        }
    }

    // Generator Code Here

}
