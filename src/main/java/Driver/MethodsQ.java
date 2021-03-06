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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    @FindBy(id = "closePU")
    private WebElement crossDeleteQuestionMW;

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
            switch (currentFileType.toUpperCase()) {
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

    public void checkQuestionTheme(String theme) throws InterruptedException {
        addQuestion("Add question test5", theme, "true", "JSON");
        MethodsQ.waitUntilElementNotDisplayed(getCancelButtonNewQuestion());
        Assert.assertEquals(
                "Theme does not match",
                theme,
                getFirstQuestion().findElement(By.className("question__theme-value")).getAttribute("textContent")
        );
        Thread.sleep(300);
        deleteLastAddedQuestion();
        Thread.sleep(300);
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

    @FindBy (css = "body > section > div > div > p")
    private WebElement mainTextAboutPage;


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
        MethodsQ.waitUntilElementNotDisplayed(getCancelButtonNewQuestion());
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

    public void checkDefaultFileSystemInMW (String fileSystem) {
        chooseFileSystem(fileSystem);
        openMW();
        if (fileSystem.equals("JSON")) {
            Assert.assertNotNull(getJSON_checkBox().getAttribute("checked"));
        } else if (fileSystem.equals("CSV")) {
            Assert.assertNotNull(getCSV_checkBox().getAttribute("checked"));
        } else if (fileSystem.equals("XML")) {
            Assert.assertNotNull(getXML_checkBox().getAttribute("checked"));
        } else if (fileSystem.equals("YAML")) {
            Assert.assertNotNull(getYAML_checkBox().getAttribute("checked"));
        }
        getCancelButtonNewQuestion().click();
    }

    public void checkFilterByTopic (String theme, String text) throws InterruptedException {
        chooseTheme("All themes");
        Assert.assertTrue(DriverConfig.driver.getPageSource().contains(text));
        chooseTheme(theme);
        Assert.assertTrue(DriverConfig.driver.getPageSource().contains(text));
        String [] themeList = new String[]{"HTML", "CSS", "JS", "React", "OOP"};
        for (int i = 0; i < themeList.length; i++) {
            if (!theme.equals(themeList[i])) {
                chooseTheme(themeList[i]);
                Thread.sleep(500);
                Assert.assertFalse(DriverConfig.driver.getPageSource().contains(text));
            }
        }
    }

    public void checkPageForFiveElementsAndDelete (String first, String second, String third, String fourth, String fifth) throws InterruptedException {
        Assert.assertTrue(DriverConfig.driver.getPageSource().contains(first));
        Assert.assertTrue(DriverConfig.driver.getPageSource().contains(second));
        Assert.assertTrue(DriverConfig.driver.getPageSource().contains(third));
        Assert.assertTrue(DriverConfig.driver.getPageSource().contains(fourth));
        Assert.assertTrue(DriverConfig.driver.getPageSource().contains(fifth));
        for (int i = 0; i < 5; i++) {
            deleteLastAddedQuestion();
            Thread.sleep(500);
        }
    }

    public void checkFilterByFileSystem (String text, String fileSystem) throws InterruptedException {
        chooseTheme("All themes");
        chooseFileSystem(fileSystem);
        Assert.assertTrue(DriverConfig.driver.getPageSource().contains(text));
        String [] fileSystems = new String[]{"JSON", "CSV", "XML", "YAML"};
        for (int i = 0; i < fileSystems.length; i++) {
            if (!fileSystem.equals(fileSystems[i])) {
                chooseFileSystem(fileSystems[i]);
                Thread.sleep(500);
                Assert.assertFalse(DriverConfig.driver.getPageSource().contains(text));
            }
        }
    }

    public void createQuestionWithAllFileSystems (String text) {
        String currentFileType = getFileTypeFilter().getAttribute("value");
        openMW();
        getQuestionField().click();
        getQuestionField().sendKeys(text);
        String [] fileSystems = new String[]{"JSON", "CSV", "XML", "YAML"};
        for (int i = 0; i < fileSystems.length; i++) {
            if (!currentFileType.toUpperCase().equals(fileSystems[i])) {
                switch (fileSystems[i]) {
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
        }
        getCreateButtonNewQuestion().click();
    }

    public void checkPageByFileSystemAllAndDelete (String text) throws InterruptedException {
        String[] fileSystems = new String[]{"JSON", "CSV", "XML", "YAML"};
        for (int i = 0; i < fileSystems.length; i++) {
            chooseFileSystem(fileSystems[i]);
            Thread.sleep(400);
            Assert.assertTrue(DriverConfig.driver.getPageSource().contains(text));
            deleteLastAddedQuestion();
            Thread.sleep(400);
            deleteLastAddedQuestion();
            Thread.sleep(400);
        }
    }
}
