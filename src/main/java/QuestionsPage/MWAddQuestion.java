package QuestionsPage;

import Driver.DriverConfig;
import Driver.Methods;
import Driver.MethodsQ;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class MWAddQuestion {

    MethodsQ methodsQ = new MethodsQ();
    Methods methods = new Methods();
    Actions action = new Actions(DriverConfig.driver);

    @Before
    public void setUp() {
        DriverConfig.initialize();
        methods.openPage("QuestionsPage");
    }

    @Test
    public void test_openMW(){
        methodsQ.openMW();
        Assert.assertTrue(
                "Question field is not visible",
                methodsQ.getQuestionField().isDisplayed()
        );
    }

    @Test
    public void test_check_MW_elements(){
        methodsQ.openMW();
        Assert.assertTrue(
                "Question field is not visible",
                methodsQ.getQuestionField().isDisplayed()
        );
        Assert.assertTrue(
                "Theme selector is not visible",
                methodsQ.getThemeSelector().isDisplayed()
        );
        Assert.assertTrue(
                "Answer true is not visible",
                methodsQ.getAnswerTrue().isDisplayed()
        );
        Assert.assertTrue(
                "Answer false is not visible",
                methodsQ.getAnswerFalse().isDisplayed()
        );
        Assert.assertTrue(
                "CSV check box is not visible",
                methodsQ.getCSV_checkBox().isDisplayed()
        );
        Assert.assertTrue(
                "JSON check box is not visible",
                methodsQ.getJSON_checkBox().isDisplayed()
        );
        Assert.assertTrue(
                "XML check box is not visible",
                methodsQ.getXML_checkBox().isDisplayed()
        );
        Assert.assertTrue(
                "YAML check box is not visible",
                methodsQ.getYAML_checkBox().isDisplayed()
        );
        Assert.assertTrue(
                "Create button is not visible",
                methodsQ.getCreateButtonNewQuestion().isDisplayed()
        );
        Assert.assertTrue(
                "Cancel button is not visible",
                methodsQ.getCancelButtonNewQuestion().isDisplayed()
        );
        Assert.assertTrue(
                "Cross is not visible",
                methodsQ.getCross().isDisplayed()
        );
    }


    @Test // не понимаю почему он не удаляется
    public void test_255symbols_in_question_field_and_three_dots() throws InterruptedException {
        methodsQ.addQuestion("255sym testта от ext.ru - это уникальный сервис, " +
                        "не имеющий аналогов. возможность подсветки «воды», заспамленности и ключей в тексте позволяет " +
                        "сделать анализ текста интерактивным и легким для восприятия. анализ текста включает в себя:  счетчидллррррвввввf",
                "OOP", "true", "JSON");
        MethodsQ.waitUntilElementNotDisplayed(methodsQ.getCancelButtonNewQuestion());
        methodsQ.checkTextOfQuestionAdded("255sym testта от ext.ru - это уникальный сервис, не имеющий аналогов. возможность подсветки «воды», заспамленности и ключей в тексте позволяет сделать анализ текста интерактивным и легким для восприятия. анализ текста включает в себя: счетчидллррррвввввf");
        methodsQ.deleteLastAddedQuestion();
    }

    @Test
    public void test_256symbols_in_question_field(){
        methodsQ.addQuestion("анализ текста от ext.ru - это уникальный сервис, " +
                        "не имеющий аналогов. возможность подсветки «воды», заспамленности и ключей в тексте позволяет " +
                        "сделать анализ текста интерактивным и легким для восприятия. анализ текста включает в себя:  счетчидллррррвввввfj",
                "OOP", "true", "XMl");
        Assert.assertTrue(methodsQ.check_createButton_disabled());
    }

    @Test
    public void test_topic_choice(){
        methodsQ.checkQuestionTheme("CSS");
        methodsQ.checkQuestionTheme("HTML");
        methodsQ.checkQuestionTheme("JS");
        methodsQ.checkQuestionTheme("REACT");
        methodsQ.checkQuestionTheme("OOP");
    }

    @Test
    public void test_radio_buttons_efficiency(){
        methodsQ.addQuestion("some text", "HTML", "true", "JSON");
        MethodsQ.waitUntilElementNotDisplayed(methodsQ.getCancelButtonNewQuestion());
        Assert.assertEquals("Answer does not match",
                "true",
                methodsQ.getFirstQuestion().findElement(By.className("question__answer-value")).getText());
        methodsQ. deleteLastAddedQuestion();

        methodsQ.addQuestion("some text", "HTML", "false", "JSON");
        MethodsQ.waitUntilElementNotDisplayed(methodsQ.getCancelButtonNewQuestion());
        Assert.assertEquals("Answer does not match",
                "false",
                methodsQ.getFirstQuestion().findElement(By.className("question__answer-value")).getText());
        methodsQ. deleteLastAddedQuestion();
    }

    @Test
    public void test_buttonCreate_disabled_without_chosen_filetype(){
        methodsQ.openMW();
        methodsQ.getQuestionField().click();
        methodsQ.getQuestionField().sendKeys("some text");
        methodsQ.getJSON_checkBox().click();
        Assert.assertTrue(methodsQ.check_createButton_disabled());
    }

    @Test
    public void test_createButton_disabled_without_text(){
        methodsQ.openMW();
        Assert.assertTrue(methodsQ.check_createButton_disabled());
    }

    @Test
    public void test_createButton_disabled_with_spaces(){
        methodsQ.openMW();
        methodsQ.getQuestionField().sendKeys("           ");
        Assert.assertTrue(methodsQ.check_createButton_disabled());
    }

    @Test
    public void test_createButton_disabled_with_adding_deletion_text() {
        methodsQ.openMW();
        methodsQ.getQuestionField().click();
        methodsQ.getQuestionField().sendKeys("some text");
        methodsQ.getQuestionField().sendKeys(Keys.CONTROL + "a");
        methodsQ.getQuestionField().sendKeys(Keys.DELETE);
        Assert.assertTrue(methodsQ.check_createButton_disabled());
    }

    @Test
    public void test_after_adding_question_fields_are_cleared(){
        methodsQ.addQuestion("Test", "OOP", "false", "CSV");
        methodsQ.getAddQuestionButton().click();
        Assert.assertEquals(
                "Field is not empty",
                "",
                methodsQ.getQuestionField().getText()
        );
        methodsQ.getCross().click();
        Select dropdown = new Select(DriverConfig.driver.findElement(By.id("file-system")));
        dropdown.selectByVisibleText("CSV");
        methodsQ.deleteLastAddedQuestion();
    }

    @Test
    public void test_closing_MW_with_cancelButton(){
        methodsQ.openMW();
        methodsQ.getCancelButtonNewQuestion().click();
        MethodsQ.waitUntilElementNotDisplayed(methodsQ.getCancelButtonNewQuestion());
    }

    @Test
    public void test_closing_MW_with_cross(){
        methodsQ.openMW();
        methodsQ.getCross().click();
        MethodsQ.waitUntilElementNotDisplayed(methodsQ.getCancelButtonNewQuestion());
    }

    @Test
    public void test_closing_MW_byClicking_outside(){
        methodsQ.openMW();
        action.moveByOffset(10, 10).click().build().perform();
        MethodsQ.waitUntilElementNotDisplayed(methodsQ.getCancelButtonNewQuestion());
    }

    @Test
    public void test_closing_MW_byClicking_Esc (){
        methodsQ.openMW();
        methodsQ.getQuestionField().sendKeys(Keys.ESCAPE);
        MethodsQ.waitUntilElementNotDisplayed(methodsQ.getCancelButtonNewQuestion());
    }

    @Test
    public void test_defaultFileType_MW () {
        methodsQ.checkDefaultFileSystemInMW("JSON");
        methodsQ.checkDefaultFileSystemInMW("CSV");
        methodsQ.checkDefaultFileSystemInMW("XML");
        methodsQ.checkDefaultFileSystemInMW("YAML");
    }

        @After
    public void finish() {
        DriverConfig.quit();
    }
}
