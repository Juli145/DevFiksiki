package Driver;

import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

@Getter

public class MethodsQ {
    public MethodsQ() {
        PageFactory.initElements(DriverConfig.getDriver(), this);
    }

    @FindBy(css = "#new-question")
    private WebElement addQuestionButton;






















































































    @FindBy(css = "body > div.questions__panel > div > label:nth-child(1)")
    private WebElement nameFilterFile;


}
