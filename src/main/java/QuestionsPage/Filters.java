package QuestionsPage;

import Driver.DriverConfig;
import Driver.Methods;
import Driver.MethodsQ;
import org.junit.After;
import org.junit.Before;

public class Filters {
    MethodsQ methodsQ = new MethodsQ();
    Methods methods = new Methods();

    @Before
    public void setUp() {
        DriverConfig.initialize();
        methods.openPage("QuestionsPage");
    }


    @After
    public void finish() {
        DriverConfig.quit();
    }
}
