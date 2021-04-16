package Driver;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter

public class Methods {
    public Methods() {
        PageFactory.initElements(DriverConfig.getDriver(), this);
    }

    int teamMembers = 8;

    @FindBy(xpath = "/html/body/header/div/div/nav/a[1]")
    private WebElement linkHome;

    @FindBy(xpath = "/html/body/header/div/div/nav/a[2]")
    private WebElement linkQuestions;

    @FindBy(xpath = "/html/body/header/div/div/nav/a[3]")
    private WebElement linkAbout;

    @FindBy (xpath = "/html/body/header/div/div/a")
    private WebElement linkLogoTeamName;

    @FindBy (xpath = "/html/body/header/div/div/a/img")
    private WebElement logo;

    @FindBy (xpath = "/html/body/header/div/div/a/div")
    private WebElement teamName;

    @FindBy (xpath = "//*[@id=\"team-name\"]")
    private WebElement fieldName;

    @FindBy (xpath = "//*[@id=\"team-mate-info\"]/p[3]")
    private WebElement fieldHeight;

    @FindBy (xpath = "//*[@id=\"team-mate-info\"]/p[4]")
    private WebElement fieldWeight;

    @FindBy (xpath = "//*[@id=\"team-mate-info\"]/p[5]")
    private WebElement fieldAge;

    @FindBy (xpath = "//*[@id=\"team-mate-info\"]/p[6]")
    private WebElement fieldBirthday;

    @FindBy (xpath = "//*[@id=\"team-mate-info\"]/p[7]")
    private WebElement fieldHobbies;

    @FindBy (id = "next-photo")
    private WebElement nextCard;

    @FindBy (id = "previous-photo")
    private WebElement previousCard;

    @FindBy (id = "team-photo")
    private WebElement developerPhoto;

    @FindBy (id = "information-edit")
    private WebElement editButton;

    @FindBy (id = "information-save")
    private WebElement saveButton;

    @FindBy (id = "team-height")
    private WebElement valueHeight;

    @FindBy (id = "team-weight")
    private WebElement valueWeight;

    @FindBy (id = "team-age")
    private WebElement valueAge;

    @FindBy (id = "team-birthday")
    private WebElement valueBirthday;

    @FindBy (id = "team-hobby")
    private WebElement valueHobbies;

    public void openPage(String pageName) {
        if (pageName.equals("HomePage")) {
            DriverConfig.getDriver().get("http://localhost:4200/index.html");
        } else if (pageName.equals("QuestionsPage")) {
            DriverConfig.getDriver().get("http://localhost:4200/questions.html");
        } else if (pageName.equals("AboutPage")) {
            DriverConfig.getDriver().get("http://localhost:4200/about.html");
        }
    }

    public void fieldClear_SendKeys_Save (WebElement element, String key) {
        element.clear();
        element.sendKeys(key);
        getSaveButton().click();
    }

}
