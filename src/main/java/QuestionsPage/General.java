package QuestionsPage;

import Driver.DriverConfig;
import Driver.Methods;
import Driver.MethodsQ;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

        Assert.assertEquals("Text of question added is incorrect",
                "Test",
                methodsQ.getFirstQuestion().findElement(By.className("question__text-box")).getText());
        Assert.assertTrue("Field answer is not exist",
                methodsQ.getFirstQuestion().findElement(By.className("question__answer-key")).isDisplayed());
        Assert.assertEquals("Field answer is named incorrect ",
                "Answer",
                methodsQ.getFirstQuestion().findElement(By.className("question__answer-key")).getText());
        Assert.assertEquals("Value of field answer is incorrect",
                "True",
                methodsQ.getFirstQuestion().findElement(By.className("question__answer-value")).getText());
        Assert.assertTrue("Field theme is not exist",
                methodsQ.getFirstQuestion().findElement(By.className("question__theme-key")).isDisplayed());
        Assert.assertEquals("Field theme is named incorrect ",
                "Theme",
                methodsQ.getFirstQuestion().findElement(By.className("question__theme-value")).getText());
        Assert.assertEquals("Value of field theme is incorrect",
                "JS",
                methodsQ.getFirstQuestion().findElement(By.className("question__theme-value")).getText());
        Assert.assertTrue("Field time is not exist",
                methodsQ.getFirstQuestion().findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div[3]/div[2]")).isDisplayed());
    }


    @After
    public void finish() {
        DriverConfig.quit();
    }
}
