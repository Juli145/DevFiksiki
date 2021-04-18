package QuestionsPage;

import Driver.DriverConfig;
import Driver.Methods;
import Driver.MethodsQ;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MWDeleteQuestion {
    MethodsQ methodsQ = new MethodsQ();
    Methods methods = new Methods();

    @Before
    public void setUp() {
        DriverConfig.initialize();
        methods.openPage("QuestionsPage");
        methodsQ.addQuestion("delete question", "OOP", "true", "JSON");
        MethodsQ.waitUntilElementNotDisplayed(methodsQ.getCancelButtonNewQuestion());
    }

    @Test
    public void test_opening_MW_delete_question(){
        methodsQ.getDeleteQuestionButton().click();
        MethodsQ.waitForVisibility(methodsQ.getConfirmDeleteQuestion(), 5);
        Assert.assertTrue(
                "Question field is not visible",
                methodsQ.getConfirmDeleteQuestion().isDisplayed()
        );
        methodsQ.getCrossDeleteQuestionMW().click();
    }

    @Test
    public void test_check_MW_DQ_text(){
        methodsQ.getDeleteQuestionButton().click();
        Assert.assertEquals(
                "Text does not match",
                "Are you sure you want to delete this question?",
                methodsQ.getDQ_MW_text().getAttribute("textContent")
        );
        methodsQ.getCrossDeleteQuestionMW().click();
    }

    @Test
    public void test_check_MW_DQ_confirmButton(){
        methodsQ.getDeleteQuestionButton().click();
        MethodsQ.waitForVisibility(methodsQ.getConfirmDeleteQuestion(), 5);
        Assert.assertTrue(
                "Confirm delete button is not visible",
                methodsQ.getConfirmDeleteQuestion().isDisplayed()
        );
        methodsQ.getCrossDeleteQuestionMW().click();
    }

    @Test
    public void test_check_MW_DQ_cancelButton(){
        methodsQ.getDeleteQuestionButton().click();
        MethodsQ.waitForVisibility(methodsQ.getConfirmDeleteQuestion(), 5);
        Assert.assertTrue(
                "Confirm delete button is not visible",
                methodsQ.getCancelDeleteQuestion().isDisplayed()
        );
        methodsQ.getCrossDeleteQuestionMW().click();
    }

    @Test
    public void test_check_closing_MW_DQ_byClicking_cancelButton(){
        methodsQ.getDeleteQuestionButton().click();
        MethodsQ.waitForVisibility(methodsQ.getConfirmDeleteQuestion(), 5);
        methodsQ.getCancelDeleteQuestion().click();
        Assert.assertFalse(
                "Modal window is not closed",
                methodsQ.getCancelDeleteQuestion().isDisplayed()
        );
    }

    @Test
    public void test_check_closing_MW_DQ_byClicking_cross(){
        methodsQ.getDeleteQuestionButton().click();
        MethodsQ.waitForVisibility(methodsQ.getConfirmDeleteQuestion(), 5);
        methodsQ.getCrossDeleteQuestionMW().click();
        Assert.assertFalse(
                "Modal window is not closed",
                methodsQ.getCancelDeleteQuestion().isDisplayed()
        );
    }

    @After
    public void finish() throws InterruptedException {
        methodsQ.deleteLastAddedQuestion();
        MethodsQ.waitUntilElementNotDisplayed(methodsQ.getConfirmDeleteQuestion());
        DriverConfig.quit();
    }
}
