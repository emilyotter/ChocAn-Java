package test;

import chocan.DailyTimer;
import chocan.controller.AbstractReportController;
import chocan.controller.MemberReportController;
import chocan.controller.ProviderReportController;
import chocan.controller.SummaryReportController;
import chocan.database.CredentialsDatabase;
import chocan.database.ServiceDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DailyTimerTest {
    DailyTimer dtTest;
    CredentialsDatabase cd;
    ServiceDatabase sd;

    @Before
    public void setUp() {
        cd = new CredentialsDatabase();
        sd = new ServiceDatabase();
        MemberReportController mrc = new MemberReportController(cd, sd);
        dtTest = new DailyTimer(0,0,0,0,mrc);
    }

    @After
    public void TearDown() {
        cd.delete();
        sd.delete();
    }

    @Test
    public void executeServiceTest() {
        dtTest.start();
    }

}