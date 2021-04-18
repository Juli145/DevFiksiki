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
    public void test6_check_questionElements() {
        methodsQ.addQuestion("Test", "HTML", "", "JSON");
        MethodsQ.waitUntilElementNotDisplayed(methodsQ.getCancelButtonNewQuestion());
        methodsQ.chooseTheme("All themes");
        methodsQ.checkQuestionAdded("Test", "true", "HTML");
        methodsQ.deleteLastAddedQuestion();
    }

    @Test
    public void test7_checkDeleteQuestionButton() {
        methodsQ.addQuestion("Test", "HTML", "", "JSON");
        MethodsQ.waitUntilElementNotDisplayed(methodsQ.getCancelButtonNewQuestion());
        action.moveToElement(methodsQ.getFirstQuestion()).build().perform();
        Assert.assertTrue("Button delete is not displayed",
                methodsQ.getDeleteQuestionButton().isDisplayed());
        action.moveToElement(methodsQ.getAddQuestionButton()).build().perform();
        Assert.assertFalse("Button delete is displayed",
                methodsQ.getDeleteQuestionButton().isDisplayed());
        methodsQ.deleteLastAddedQuestion();
    }

    @Test
    public void test8_checkMessage_TheAreNoQuestions() {
        Assert.assertEquals("There are no questions", methodsQ.getMessageNoQuestions().getText());
        Assert.assertTrue(methodsQ.getMessageNoQuestions().isDisplayed());
    }

    @Test
    public void test9_checkTitle_textOfQuestion() throws InterruptedException {
        String title = "Test test test";
        methodsQ.addQuestion(title, "HTML", "", "JSON");
        methodsQ.checkLastQuestionTitle(title);
        Thread.sleep(400);
        methodsQ.deleteLastAddedQuestion();
    }

    @Test
    public void test10_filtersByDefault() {
        MethodsQ.LocalStorageJS js = new MethodsQ.LocalStorageJS(DriverConfig.driver);
        js.clearLocalStorage();
        DriverConfig.driver.navigate().refresh();
        Select dropdownFileSystem = new Select(methodsQ.getFileTypeFilter());
        Assert.assertEquals("Default file system filter is not JSON",
                "JSON",
                dropdownFileSystem.getFirstSelectedOption().getText());
        Select dropdownTheme = new Select(methodsQ.getThemeFilter());
        Assert.assertEquals("Default theme filter is not All themes",
                "All themes",
                dropdownTheme.getFirstSelectedOption().getText());
    }

    @After
    public void finish() {
        DriverConfig.quit();
    }
}
