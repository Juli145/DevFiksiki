package Header;

import Driver.DriverConfig;
import Driver.Methods;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;


public class Hovers {
    @Before
    public void setUp() {
        DriverConfig.initialize();
    }

    Methods methods = new Methods();
    Actions action = new Actions(DriverConfig.driver);

    @Test
    public void test1_hover_linkQuestions() {
        methods.openPage("Home");
        action.moveToElement(methods.getLinkQuestions()).build().perform();
        String backColor = methods.getLinkQuestions().getCssValue("background-color");
        Color color = Color.fromString(backColor);
        Assert.assertEquals("#7068e2", color.asHex());
    }

    @Test
    public void test1_hover_linkAbout() {
        methods.openPage("Home");
        action.moveToElement(methods.getLinkAbout()).build().perform();
        String backColor = methods.getLinkAbout().getCssValue("background-color");
        Color color = Color.fromString(backColor);
        Assert.assertEquals("#7068e2", color.asHex());
    }

    @Test
    public void test1_hover_linkHome() {
        methods.openPage("About");
        action.moveToElement(methods.getLinkHome()).build().perform();
        String backColor = methods.getLinkHome().getCssValue("background-color");
        Color color = Color.fromString(backColor);
        Assert.assertEquals("#7068e2", color.asHex());
    }


    @After
    public void finish() {
        DriverConfig.quit();
    }
}
