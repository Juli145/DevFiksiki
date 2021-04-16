package QuestionsPage;

import Driver.DriverConfig;
import Driver.Methods;
import Driver.MethodsQ;
import org.junit.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;


public class General {

    MethodsQ methodsQ = new MethodsQ();
    Methods methods = new Methods();
    Actions action = new Actions(DriverConfig.driver);

    @Before
    public void setUp() {
        DriverConfig.initialize();
        methods.openPage("QuestionsPage");
    }

    @Test
    public void test1_checkNameFilters() {
        Assert.assertEquals("Select file format:", methodsQ.getNameFilterFileType().getText());
        Assert.assertEquals("Select a question topic:", methodsQ.getNameFilterTheme().getText());
    }

    @Test
    public void test2_addQuestionButton_Visibility_Name() {
        Assert.assertTrue(methodsQ.getAddQuestionButton().isDisplayed());
        Assert.assertEquals("Add question", methodsQ.getAddQuestionButton().getText());
    }

    @Test
    public void test3_addQuestionButton_hover() {
        action.moveToElement(methodsQ.getAddQuestionButton()).build().perform();
        String hoverColor = methodsQ.getAddQuestionButton().getCssValue("background-color");
        Color color = Color.fromString(hoverColor);
        Assert.assertEquals("Hover color is incorrect",
                "#ffffff", color.asHex());
    }

    @Test
    public void test4_FileTypeFilter_elementsDropDown() {
        Select dropdown = new Select(methodsQ.getFileTypeFilter());
        Assert.assertEquals("JSON", dropdown.getOptions().get(0).getText());
        Assert.assertEquals("CSV", dropdown.getOptions().get(1).getText());
        Assert.assertEquals("XML", dropdown.getOptions().get(2).getText());
        Assert.assertEquals("YAML", dropdown.getOptions().get(3).getText());
    }

    @Test
    public void test5_ThemeFilter_elementsDropDown() {
        Select dropdown = new Select(methodsQ.getThemeFilter());
        Assert.assertEquals("All themes", dropdown.getOptions().get(0).getText());
        Assert.assertEquals("HTML", dropdown.getOptions().get(1).getText());
        Assert.assertEquals("CSS", dropdown.getOptions().get(2).getText());
        Assert.assertEquals("JS", dropdown.getOptions().get(3).getText());
        Assert.assertEquals("React", dropdown.getOptions().get(4).getText());
        Assert.assertEquals("OOP", dropdown.getOptions().get(5).getText());
    }

    @Test
    public void test6_check_questionElements() throws InterruptedException {
        methodsQ.addTestQuestion("Test");
        Thread.sleep(500);
        Select dropdown = new Select(methodsQ.getThemeFilter());
        dropdown.selectByIndex(0);
        Thread.sleep(300);
        methodsQ.checkQuestionAdded("Test");
    }

    @Test
    public void test7_checkDeleteQuestionButton () throws InterruptedException {
        methodsQ.addTestQuestion("Test");
        Thread.sleep(300);
        action.moveToElement(methodsQ.getFirstQuestion()).build().perform();
        Assert.assertTrue("Button delete is not displayed",
                methodsQ.getDeleteQuestionButton().isDisplayed());
        action.moveToElement(methodsQ.getAddQuestionButton()).build().perform();
        Assert.assertFalse( "Button delete is displayed",
                methodsQ.getDeleteQuestionButton().isDisplayed());
    }

    @After
    public void finish() {
        DriverConfig.quit();
    }
}
