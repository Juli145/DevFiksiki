package Driver;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

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

    @FindBy(css = "#delete-message-popup > div > div > div.popup__text > p")
    private WebElement DQ_MW_text;

    @FindBy(css = "body > div.questions > div > div > div > div:nth-child(1) > button")
    private WebElement deleteQuestionButton;

    @FindBy(id = "confirm-delete-question")
    private WebElement confirmDeleteQuestion;

    @FindBy(id = "cancel-delete-question")
    private WebElement cancelDeleteQuestion;


    public void openMW() {
        getAddQuestionButton().click();
        MethodsQ.waitForVisibility(getCreateButtonNewQuestion(), 5);
    }

    public void addQuestion(String text, String theme, String answer, String fileType) {
        String currentFileType = getFileTypeFilter().getAttribute("value");
        openMW();
        getQuestionField().click();
        getQuestionField().sendKeys(text);
        Select dropdown = new Select(DriverConfig.driver.findElement(By.id("selectTheme")));
        dropdown.selectByVisibleText(theme);
        if (answer.equals("false")) {
            getAnswerFalse().click();
        }
        if (!fileType.equals(currentFileType.toUpperCase())) {
            switch (fileType) {
                case ("CSV"):
                    getCSV_checkBox().click();
                    break;
                case ("JSON"):
                    getJSON_checkBox().click();
                    break;
                case ("XML"):
                    getXML_checkBox().click();
                    break;
                case ("YAML"):
                    getYAML_checkBox().click();
                    break;
            }
        }
        getCreateButtonNewQuestion().click();
    }

    public void checkQuestionTheme(String theme) {
        addQuestion("some text", theme, "true", "JSON");
        MethodsQ.waitForVisibility(getCreateButtonNewQuestion(), 5);
        Assert.assertEquals(
                "Theme does not match",
                theme,
                getFirstQuestion().findElement(By.className("question__theme-value")).getText()
        );
        deleteLastAddedQuestion();
    }

    public boolean check_createButton_disabled() {
        getCreateButtonNewQuestion().click();
        return getCross().isDisplayed();
    }

    @FindBy(css = "body > div.questions__panel > div > label:nth-child(1)")
    private WebElement nameFilterFileType;

    @FindBy(css = "body > div.questions__panel > div > label:nth-child(3)")
    private WebElement nameFilterTheme;

    @FindBy(id = "file-system")
    private WebElement fileTypeFilter;

    @FindBy(id = "questions-theme")
    private WebElement themeFilter;

    @FindBy(css = "body > div.questions > div > div > div > div:nth-child(1)")
    private WebElement firstQuestion;

    @FindBy(css = "#question > div")
    private WebElement questionBody;

    @FindBy(css = "body > div.questions > div > div > div > p")
    private WebElement messageNoQuestions;


    public void checkQuestionAdded(String text, String answer, String theme) {
        Assert.assertEquals("Text of question added is incorrect",
                text,
                getFirstQuestion().findElement(By.className("question__text-box")).getText());

        Assert.assertTrue("Field answer is not exist",
                getFirstQuestion().findElement(By.className("question__answer-key")).isDisplayed());

        Assert.assertEquals("Field answer is named incorrect ",
                "Answer",
                getFirstQuestion().findElement(By.className("question__answer-key")).getText());

        Assert.assertEquals("Value of field answer is incorrect",
                answer,
                getFirstQuestion().findElement(By.className("question__answer-value")).getText());

        Assert.assertTrue("Field theme is not exist",
                getFirstQuestion().findElement(By.className("question__theme-key")).isDisplayed());

        Assert.assertEquals("Field theme is named incorrect ",
                "Theme",
                getFirstQuestion().findElement(By.className("question__theme-key")).getText());

        Assert.assertEquals("Value of field theme is incorrect",
                theme,
                getFirstQuestion().findElement(By.className("question__theme-value")).getText());

        Assert.assertTrue("Field time is not exist",
                getFirstQuestion().findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div[3]/div[2]")).isDisplayed());
    }

    public void checkTextOfQuestionAdded(String text) {
        Assert.assertEquals("Text of question added is incorrect or question does not exist",
                text,
                getFirstQuestion().findElement(By.className("question__text-box")).getText());
    }

    public void deleteLastAddedQuestion() {
        getFirstQuestion().findElement(By.cssSelector("body > div.questions > div > div > div > div:nth-child(1) > button")).click();
        getConfirmDeleteQuestion().click();
    }

    public void checkLastQuestionTitle(String title) {
        MethodsQ.waitForVisibility(getCreateButtonNewQuestion(), 1);
        Assert.assertEquals(title, getFirstQuestion().findElement(By.className("question__text-box")).getText());
    }

    public static class LocalStorageJS {
        private final JavascriptExecutor js;

        public LocalStorageJS(WebDriver webDriver) {
            this.js = (JavascriptExecutor) webDriver;
        }

        public void clearLocalStorage() {
            js.executeScript("window.localStorage.clear();");
        }

        public String getLocalStorageValue(String key) {
            return (String) js.executeScript(String.format(
                    "return window.localStorage.getItem('%s');", key));
        }
    }

    public void chooseFileSystem(String fileSystem) {
        Select dropdownFileSystem = new Select(getFileTypeFilter());
        dropdownFileSystem.selectByVisibleText(fileSystem);
    }

    public void chooseTheme(String theme) {
        Select dropdownFileSystem = new Select(getThemeFilter());
        dropdownFileSystem.selectByVisibleText(theme);
    }

    public void checkTheSortingNumberQuestion(String text, int number) {
        WebElement question = DriverConfig.driver.
                findElement(By.cssSelector("body > div.questions > div > div > div > div:nth-child(" + number + ")"));
        String textOfQuestion = question.findElement(By.className("question__text-box")).getText();
        Assert.assertEquals(text, textOfQuestion);
    }

    public static void waitUntilElementNotDisplayed (WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverConfig.driver, 5);
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }
}
