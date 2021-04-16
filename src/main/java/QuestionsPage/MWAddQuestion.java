package QuestionsPage;

import Driver.DriverConfig;
import Driver.Methods;
import Driver.MethodsQ;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


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
    public void test_255symbols_in_question_field_and_three_dots() throws InterruptedException {
        methodsQ.openMW();
        methodsQ.getQuestionField().click();
        methodsQ.getQuestionField().sendKeys("анализ текста от ext.ru - это уникальный сервис, " +
                "не имеющий аналогов. возможность подсветки «воды», заспамленности и ключей в тексте позволяет " +
                "сделать анализ текста интерактивным и легким для восприятия. анализ текста включает в себя:  счетчидллррррвввввf");
        methodsQ.getCreateButtonNewQuestion().click();
        Thread.sleep(300);
        methodsQ.checkTextOfQuestionAdded("анализ текста от ext.ru - это уникальный сервис, не имеющий аналогов. возможность подсветки «воды», заспамленности и ключей в тексте позволяет сделать анализ текста интерактивным и легким для восприятия. анализ текста включает в себя: счетчидллррррвввввf");
    }

    @Test
    public void test_256symbols_in_question_field(){
        methodsQ.openMW();
        methodsQ.getQuestionField().click();
        methodsQ.getQuestionField().sendKeys("анализ текста от ext.ru - это уникальный сервис, " +
                "не имеющий аналогов. возможность подсветки «воды», заспамленности и ключей в тексте позволяет " +
                "сделать анализ текста интерактивным и легким для восприятия. анализ текста включает в себя:  счетчидллррррвввввfj");
        methodsQ.getCreateButtonNewQuestion().click();
        Assert.assertTrue(
                "Cross is not visible",
                methodsQ.getCross().isDisplayed()
        );
    }

    @Test
    public void test_topic_choice(){
        methodsQ.addQuestion("some text", "CSS", "true","CSV");
    }



    //    @After
//    public void finish() {
//        DriverConfig.quit();
//    }
}
