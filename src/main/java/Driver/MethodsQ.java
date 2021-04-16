package Driver;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter

public class MethodsQ {
    public MethodsQ() {
        PageFactory.initElements(DriverConfig.getDriver(), this);
    }

    @FindBy(css = "#new-question")
    private WebElement addQuestionButton;

    @FindBy(css = "#create-modal-new-question")
    private WebElement createButtonNewQuestion;

    public static WebElement waitForVisibility(WebElement element, int timeOfWait, int... timeOfTryOut) {
        WebElement webElement = null;
        int timeOfRevision = timeOfTryOut.length == 0
                ? 100
                : timeOfTryOut[0];
        try {
            webElement = new WebDriverWait(DriverConfig.getDriver(),
                    timeOfWait,
                    timeOfRevision
            ).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webElement;
    }

    @FindBy(css = "#writeQuestion")
    private WebElement questionField;

    @FindBy(css = "#selectTheme")
    private WebElement themeSelector;

    @FindBy(css = "#answerTrue")
    private WebElement answerTrue;

    @FindBy(css = "#answerFalse")
    private WebElement answerFalse;

    @FindBy(css = "#CSV")
    private WebElement CSV_checkBox;

    @FindBy(css = "#JSON")
    private WebElement JSON_checkBox;

    @FindBy(css = "#XML")
    private WebElement XML_checkBox;

    @FindBy(css = "#YAML")
    private WebElement YAML_checkBox;

    @FindBy(css = "#cancel-modal-new-question")
    private WebElement cancelButtonNewQuestion;

    @FindBy(css = "#close-modal-new-question")
    private WebElement cross;

    public void openMW(){
        getAddQuestionButton().click();
        MethodsQ.waitForVisibility(getCreateButtonNewQuestion(), 5);
    }




















































































    @FindBy(css = "body > div.questions__panel > div > label:nth-child(1)")
    private WebElement nameFilterFileType;

    @FindBy(css = "body > div.questions__panel > div > label:nth-child(3)")
    private WebElement nameFilterTheme;

    @FindBy (id = "file-system")
    private WebElement fileTypeFilter;

    @FindBy (id = "questions-theme")
    private WebElement themeFilter;

    @FindBy (css = "body > div.questions > div > div > div > div:nth-child(1)")
    private WebElement firstQuestion;

    @FindBy (css = "#question > div")
    private WebElement questionBody;

    public void addTestQuestion (String text) {
        getAddQuestionButton().click();
        waitForVisibility(getCreateButtonNewQuestion(), 5);
        getQuestionField().click();
        getQuestionField().sendKeys(text);
        getCreateButtonNewQuestion().click();
    }

    public void checkQuestionAdded (String text) {
        Assert.assertEquals("Text of question added is incorrect",
                text,
                getFirstQuestion().findElement(By.className("question__text-box")).getText());
        Assert.assertTrue("Field answer is not exist",
                getFirstQuestion().findElement(By.className("question__answer-key")).isDisplayed());
        Assert.assertEquals("Field answer is named incorrect ",
                "Answer",
                getFirstQuestion().findElement(By.className("question__answer-key")).getText());
        Assert.assertEquals("Value of field answer is incorrect",
                "true",
                getFirstQuestion().findElement(By.className("question__answer-value")).getText());
        Assert.assertTrue("Field theme is not exist",
                getFirstQuestion().findElement(By.className("question__theme-key")).isDisplayed());
        Assert.assertEquals("Field theme is named incorrect ",
                "Theme",
                getFirstQuestion().findElement(By.className("question__theme-key")).getText());
        Assert.assertEquals("Value of field theme is incorrect",
                "HTML",
                getFirstQuestion().findElement(By.className("question__theme-value")).getText());
        Assert.assertTrue("Field time is not exist",
                getFirstQuestion().findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div[3]/div[2]")).isDisplayed());
    }

    public void checkTextOfQuestionAdded (String text) {
        Assert.assertEquals("Text of question added is incorrect or question does not exist",
                text,
                getFirstQuestion().findElement(By.className("question__text-box")).getText());
    }

}
