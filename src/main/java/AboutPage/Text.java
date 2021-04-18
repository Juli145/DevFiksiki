package AboutPage;

import Driver.DriverConfig;
import Driver.Methods;
import Driver.MethodsQ;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Text {
    @Before
    public void setUp() {
        DriverConfig.initialize();
        methods.openPage("AboutPage");
    }

    Methods methods = new Methods();
    MethodsQ methodsQ = new MethodsQ();

    @Test
    public void test1_checkText () {
       Assert.assertEquals("To write a project, we had to learn a lot of new things. " +
                "It was hard but interesting. We put in a lot of effort, but our expectations were met. " +
                "With every line of the written code we got stronger", methodsQ.getMainTextAboutPage().getText());
    }

    @After
    public void finish() {
        DriverConfig.quit();
    }
}
