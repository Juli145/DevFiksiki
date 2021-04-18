package Smoke;

import Driver.DriverConfig;
import Driver.Methods;
import Driver.MethodsQ;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;


public class SmokeTest {

    MethodsQ methodsQ = new MethodsQ();
    Methods methods = new Methods();

    @Before
    public void setUp() {
        DriverConfig.initialize();
    }

    @Test
    public void smokeTest() throws InterruptedException {
        methods.openPage("HomePage");

        for (int i = 0; i < methods.getTeamMembers() - 1; i++) {
            methods.getNextCard().click();
        }
        Assert.assertFalse(methods.getNextCard().isDisplayed());

        methods.getEditButton().click();
        methods.getFieldName().clear();
        methods.getFieldName().sendKeys("Test");
        methods.getValueHeight().clear();
        methods.getValueHeight().sendKeys("191");
        methods.getSaveButton().click();

        Assert.assertEquals("Test", methods.getFieldName().getAttribute("value"));
        Assert.assertEquals("191", methods.getValueHeight().getAttribute("value"));

        methods.getLinkQuestions().click();
        MethodsQ.waitForVisibility(methodsQ.getAddQuestionButton(), 10);
        Assert.assertTrue(methodsQ.getAddQuestionButton().isDisplayed());

        Thread.sleep(300);
        methodsQ.addQuestion("Test", "OOP", "true", "CSV");
        methodsQ.chooseFileSystem("CSV");
        methodsQ.chooseTheme("OOP");
        Assert.assertTrue(DriverConfig.driver.getPageSource().contains("Test"));
        methodsQ.deleteLastAddedQuestion();

        methodsQ.getAddQuestionButton().click();
        methodsQ.getCancelButtonNewQuestion().click();
        Assert.assertFalse(methodsQ.getCreateButtonNewQuestion().isDisplayed());

        methods.getLinkAbout().click();
        Assert.assertTrue(DriverConfig.driver.getPageSource().contains("Welcome!!!"));
    }

    @Test
    public void smokeTest_dimension_1440_759 () throws InterruptedException {
        Dimension dimension = new Dimension(1440, 759);
        DriverConfig.driver.manage().window().setSize(dimension);
        smokeTest();
    }

    @Test
    public void smokeTest_dimension_1600_759 () throws InterruptedException {
        Dimension dimension = new Dimension(1600, 759);
        DriverConfig.driver.manage().window().setSize(dimension);
        smokeTest();
    }

    @After
    public void finish() {
        DriverConfig.quit();
    }
}
