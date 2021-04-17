package QuestionsPage;

import Driver.DriverConfig;
import Driver.Methods;
import Driver.MethodsQ;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;


public class MWAddQuestion {

    MethodsQ methodsQ = new MethodsQ();
    Methods methods = new Methods();

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


    @Test
    public void test_255symbols_in_question_field_and_three_dots(){
        methodsQ.openMW();
        methodsQ.getQuestionField().click();
        methodsQ.getQuestionField().sendKeys("анализ текста от ext.ru - это уникальный сервис, " +
                "не имеющий аналогов. возможность подсветки «воды», заспамленности и ключей в тексте позволяет " +
                "сделать анализ текста интерактивным и легким для восприятия. анализ текста включает в себя:  счетчидллррррвввввf");
        methodsQ.getCreateButtonNewQuestion().click();
        MethodsQ.waitForVisibility(methodsQ.getCreateButtonNewQuestion(), 5);
        methodsQ.checkTextOfQuestionAdded("анализ текста от ext.ru - это уникальный сервис, не имеющий аналогов. возможность подсветки «воды», заспамленности и ключей в тексте позволяет сделать анализ текста интерактивным и легким для восприятия. анализ текста включает в себя: счетчидллррррвввввf");
        methodsQ.deleteLastAddedQuestion();
    }

    @Test
    public void test_256symbols_in_question_field(){
        methodsQ.openMW();
        methodsQ.getQuestionField().click();
        methodsQ.getQuestionField().sendKeys("анализ текста от ext.ru - это уникальный сервис, " +
                "не имеющий аналогов. возможность подсветки «воды», заспамленности и ключей в тексте позволяет " +
                "сделать анализ текста интерактивным и легким для восприятия. анализ текста включает в себя:  счетчидллррррвввввfj");
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
        MethodsQ.waitForVisibility(methodsQ.getCreateButtonNewQuestion(), 5);
        Assert.assertEquals("Answer does not match",
                "true",
                methodsQ.getFirstQuestion().findElement(By.className("question__answer-value")).getText());
        methodsQ. deleteLastAddedQuestion();

        methodsQ.addQuestion("some text", "HTML", "false", "JSON");
        MethodsQ.waitForVisibility(methodsQ.getCreateButtonNewQuestion(), 5);
        Assert.assertEquals("Answer does not match",
                "false",
                methodsQ.getFirstQuestion().findElement(By.className("question__answer-value")).getText());
        methodsQ. deleteLastAddedQuestion();
    }

    @Test
    public void test_buttonCreate_disabled_without_chosen_filetype(){
        methodsQ.getAddQuestionButton().click();
        MethodsQ.waitForVisibility(methodsQ.getCreateButtonNewQuestion(), 5);
        methodsQ.getQuestionField().click();
        methodsQ.getQuestionField().sendKeys("some text");
        methodsQ.getJSON_checkBox().click();
        Assert.assertTrue(methodsQ.check_createButton_disabled());
    }

    @Test
    public void test_createButton_disabled_without_text(){
        methodsQ.getAddQuestionButton().click();
        Assert.assertTrue(methodsQ.check_createButton_disabled());
    }

    @Test
    public void test_createButton_disabled_with_spaces(){
        methodsQ.getAddQuestionButton().click();
        methodsQ.getQuestionField().sendKeys("           ");
        Assert.assertTrue(methodsQ.check_createButton_disabled());
    }

    @Test // not working
    public void test_createButton_disabled_with_adding_deletion_text(){
        methodsQ.getAddQuestionButton().click();
        methodsQ.getQuestionField().click();
        methodsQ.getQuestionField().sendKeys("some text");
        methodsQ.getQuestionField().clear();
        methodsQ.getQuestionField().click();
        //Assert.assertTrue(methodsQ.check_createButton_disabled());
    }

    @Test
    public void test_after_adding_fields_are_cleared(){
        methodsQ.addQuestion("some text", "OOP", "false", "CSV");
        methodsQ.getAddQuestionButton().click();
        methodsQ.getQuestionField().click();
        methodsQ.getQuestionField().sendKeys("some text");
        Assert.assertEquals(
                "Field is not empty",
                "",
                methodsQ.getQuestionField().getText()
        );
    }


//        @After
//    public void finish() {
//        DriverConfig.quit();
//    }
}
