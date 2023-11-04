package chocan.report;
import java.time.LocalDateTime;
/**
 * Abstract class for all reports.
 * Defines the interface for all reports.
 */
public abstract class AbstractReport {
    
  /**
   * Base class for all reports.
   * 
   * Attributes:
   *  reportType: String
   * 
   * @param reportType The type of report.
   */
    protected String reportType;

    public AbstractReport(String reportType) {
        this.reportType = reportType;
    }


    /**
     * Abstract method for generating a report.
     * 
     * @return String The report.
     */
    public abstract String generateReport();

    /**
     * Abstract method for printing a report.
     * 
     * @return String The report.
     */
    public abstract String printReport();


    /**
     *  Method for getting current date and time. Utility method to stamp reports with date and time.
     * 
     * @return String The report.
     */
    public String getCurrentDateAndTime() {
        return LocalDateTime.now().toString();
    }
    

    /**
     * Method for getting the report type. Attribute is protected.
     * 
     * @return String The report.
     */
    public String getReportType() {
        return this.reportType;
    }


}
