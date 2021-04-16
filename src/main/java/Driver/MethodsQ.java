package Driver;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter

public class MethodsQ {
    public MethodsQ() {
        PageFactory.initElements(DriverConfig.getDriver(), this);
    }

    @FindBy(css = "#new-question")
    private WebElement addQuestionButton;






















































































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
