package Header;

import Driver.DriverConfig;
import Driver.Methods;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Links {


    @Before
    public void setUp() {
        DriverConfig.initialize();
    }

    Methods methods = new Methods();

    @Test
    public void test1_questionLink_HomeToQuestions() throws InterruptedException {
        methods.openPage("HomePage");
        methods.getLinkQuestions().click();
        Assert.assertEquals("Questions", DriverConfig.driver.getTitle());
    }

    @Test
    public void test2_aboutLink_HomeToAbout() {
        methods.openPage("HomePage");
        methods.getLinkAbout().click();
        Assert.assertEquals("About", DriverConfig.driver.getTitle());
    }

    @Test
    public void test3_aboutLink_QuestionsToAbout() {
        methods.openPage("QuestionsPage");
        methods.getLinkAbout().click();
        Assert.assertEquals("About", DriverConfig.driver.getTitle());
    }

    @Test
    public void test4_homeLink_AboutToHome() {
        methods.openPage("AboutPage");
        methods.getLinkHome().click();
        Assert.assertEquals("Home", DriverConfig.driver.getTitle());
    }


    @Test
    public void test5_questionLink_AboutToQuestions() {
        methods.openPage("AboutPage");
        methods.getLinkQuestions().click();
        Assert.assertEquals("Questions", DriverConfig.driver.getTitle());
    }

    @Test
    public void test6_homeLink_QuestionToHome() {
        methods.openPage("Questions");
        methods.getLinkHome().click();
        Assert.assertEquals("Home", DriverConfig.driver.getTitle());
    }

    @After
    public void finish() {
        DriverConfig.quit();
    }
}
