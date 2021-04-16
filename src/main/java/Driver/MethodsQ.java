package Driver;

import lombok.Getter;
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

    public void addTestQuestion () {
        getAddQuestionButton().click();

    }

}
