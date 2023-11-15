package test.controller;

import chocan.controller.OperatorController;
import chocan.database.CredentialsDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class OperatorControllerTest {

    private CredentialsDatabase mockDatabase;
    private OperatorController operatorController;

    @Before
    public void setUp() {
        mockDatabase = new CredentialsDatabase();
        operatorController = new OperatorController(mockDatabase);
    }

    @After
    public void tearDown() {
        mockDatabase.delete();
    }

    @Test
    public void testProviderDirectory() {
        operatorController.generateProviderDirectory();
    }

}
