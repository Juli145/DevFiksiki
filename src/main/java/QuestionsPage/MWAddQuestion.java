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
    public void test_check_MW_elements(){
        methodsQ.getAddQuestionButton().click();
        MethodsQ.waitForVisibility(methodsQ.getCreateButtonNewQuestion(), 5);
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
}
