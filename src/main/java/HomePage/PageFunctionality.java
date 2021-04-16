package HomePage;

import Driver.DriverConfig;
import Driver.Methods;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class PageFunctionality {
    @Before
    public void setUp() {
        DriverConfig.initialize();
    }

    Methods methods = new Methods();

    @Test
    public void test1_openAsDefaultPage() {
        DriverConfig.driver.get("http://localhost:4200/");
        Assert.assertEquals("Home", DriverConfig.driver.getTitle());
    }

    @Test
    public void test2_infoElementsAboutDevelopers() {
        methods.openPage("HomePage");
        Assert.assertTrue(methods.getFieldName().isDisplayed());
        Assert.assertEquals("Height:", methods.getFieldHeight().getText());
        Assert.assertEquals("Weight:", methods.getFieldWeight().getText());
        Assert.assertEquals("Age:", methods.getFieldAge().getText());
        Assert.assertEquals("Birthday:", methods.getFieldBirthday().getText());
        Assert.assertEquals("Hobbies:", methods.getFieldHobbies().getText());
    }

    @Test
    public void test3_slider_firstCard_checkVisibility() {
        methods.openPage("HomePage");
        Assert.assertTrue(methods.getNextCard().isDisplayed());
        Assert.assertFalse(methods.getPreviousCard().isDisplayed());
    }

    @Test
    public void test4_slider_nextCard() {
        methods.openPage("HomePage");
        String firstPhoto = methods.getDeveloperPhoto().getAttribute("src");
        methods.getNextCard().click();
        String secondPhoto = methods.getDeveloperPhoto().getAttribute("src");
        Assert.assertNotEquals(firstPhoto, secondPhoto);
        Assert.assertTrue(methods.getPreviousCard().isDisplayed());
    }

    @Test
    public void test5_slider_lastCard_checkVisibility () {
        methods.openPage("HomePage");
        for (int i = 0; i < methods.getTeamMembers() - 1; i++) {
            methods.getNextCard().click();
        }
        Assert.assertTrue(methods.getPreviousCard().isDisplayed());
        Assert.assertFalse(methods.getNextCard().isDisplayed());
    }

    @After
    public void finish() {
        DriverConfig.quit();
    }


}
