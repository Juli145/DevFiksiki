package Header;

import Driver.DriverConfig;
import Driver.Methods;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Logo_TeamName {
    @Before
    public void setUp() {
        DriverConfig.initialize();
    }

    Methods methods = new Methods();

    @Test
    public void test1_logoRedirect_QuestionsToHome() {
        methods.openPage("Questions");
        methods.getLinkLogoTeamName().click();
        Assert.assertEquals("Home", DriverConfig.driver.getTitle());
    }

    @Test
    public void test2_logoRedirect_AboutToHome() {
        methods.openPage("About");
        methods.getLinkLogoTeamName().click();
        Assert.assertEquals("Home", DriverConfig.driver.getTitle());
    }

    @Test
    public void test3_checkLogoExist() {
        methods.openPage("Home");
        Assert.assertTrue(methods.getLogo().isDisplayed());
    }

    @Test
    public void test4_checkTeamName () {
        methods.openPage("Home");
        Assert.assertTrue(methods.getTeamName().isDisplayed());
        Assert.assertEquals("DevFiksiki", methods.getTeamName().getText());
    }

    @After
    public void finish() {
        DriverConfig.quit();
    }
}
