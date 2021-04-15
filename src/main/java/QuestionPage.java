import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class QuestionPage {
    WebDriver driver;

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "D://JuliDirectory//Selenium drivers and jars//Drivers//chromedrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(Constant.QUESTION_PAGE_URL);
    }

    // 1
    @Test
    public void test_findFileSystemDropdown(){
        driver.findElement(By.id(Constant.FILE_SYSTEM_DROPDOWN_ID));
    }

    // 2
    @Test
    public void test_FileSystemDropdownDisplayALL(){
        driver.findElement(By.id(Constant.FILE_SYSTEM_DROPDOWN_ID)).click();
        boolean actual1 = driver.getPageSource().contains("JSON");
        Assertions.assertTrue(actual1);
        boolean actual2 = driver.getPageSource().contains("CSV");
        Assertions.assertTrue(actual2);
        boolean actual3 = driver.getPageSource().contains("XML");
        Assertions.assertTrue(actual3);
        boolean actual4 = driver.getPageSource().contains("YAML");
        Assertions.assertTrue(actual4);
    }

//    // 3
//    @Test
//    public void test_FileSystemDropdownJSON(){
//        driver.findElement(By.id(FILE_SYSTEM_DROPDOWN_ID)).click();
//        boolean actual = driver.getPageSource().contains("JSON");
//        Assertions.assertTrue(actual);
//    }
//
//    // 4
//    @Test
//    public void test_FileSystemDropdownCSV(){
//        driver.findElement(By.id(FILE_SYSTEM_DROPDOWN_ID)).click();
//        boolean actual = driver.getPageSource().contains("CSV");
//        Assertions.assertTrue(actual);
//    }
//
//    // 5
//    @Test
//    public void test_FileSystemDropdownXML(){
//        driver.findElement(By.id(FILE_SYSTEM_DROPDOWN_ID)).click();
//        boolean actual = driver.getPageSource().contains("XML");
//        Assertions.assertTrue(actual);
//    }
//
//    // 6
//    @Test
//    public void test_FileSystemDropdownYAML(){
//        driver.findElement(By.id(FILE_SYSTEM_DROPDOWN_ID)).click();
//        boolean actual = driver.getPageSource().contains("YAML");
//        Assertions.assertTrue(actual);
//    }

    // 7
    @Test
    public void test_findQuestionThemeDropdown(){
        driver.findElement(By.id(Constant.QUESTION_THEME_DROPDOWN_ID));
    }

    // 8
    @Test
    public void test_QuestionThemeDropdownDisplayALL(){
        driver.findElement(By.id(Constant.QUESTION_THEME_DROPDOWN_ID));
        boolean actual1 = driver.getPageSource().contains("Theme 1");
        Assertions.assertTrue(actual1);
        boolean actual2 = driver.getPageSource().contains("Theme 2");
        Assertions.assertTrue(actual2);
        boolean actual3 = driver.getPageSource().contains("Theme 3");
        Assertions.assertTrue(actual3);
        boolean actual4 = driver.getPageSource().contains("Theme 4");
        Assertions.assertTrue(actual4);
        boolean actual5 = driver.getPageSource().contains("Theme 5");
        Assertions.assertTrue(actual5);
    }

//    @Test
//    public void test_QuestionThemeDropdownTheme1(){
//        driver.findElement(By.id(QUESTION_THEME_DROPDOWN_ID)).click();
//        boolean actual = driver.getPageSource().contains("Theme 1");
//        Assertions.assertTrue(actual);
//    }

    // 9
    @Test
    public void test_findNewQuestionButton(){
        driver.findElement(By.id(Constant.NEW_QUESTION_BUTTON_ID));
    }

    // 10
    @Test
    public void test_findTeamName(){
        String actual = driver.findElement(By.className(Constant.HEADER_CLASS_NAME)).findElement(By.className(Constant.TEAM_NAME_CLASS_NAME)).getText();
        Assertions.assertEquals(actual, Constant.TEAM_NAME);
    }

    // 11
    @Test
    public void test_findLogo(){
        driver.findElement(By.className(Constant.HEADER_CLASS_NAME)).findElement(By.className(Constant.LOGO_CLASS_NAME));
    }

    // 12
    @Test
    public void test_findDeleteMessageButton(){
        driver.findElement(By.id(Constant.DELETE_MESSAGE_BUTTON_ID));
    }

    // 13
    @Test
    public void test_findPopUp(){
        driver.findElement(By.id(Constant.DELETE_MESSAGE_BUTTON_ID)).click();
        driver.findElement(By.id(Constant.POP_UP_ID));
    }

    // 14
    @Test
    public void test_MW_DeleteQuestion(){
        driver.findElement(By.id(Constant.DELETE_MESSAGE_BUTTON_ID)).click();
        boolean actual = driver.getPageSource().contains("Are you sure you want to delete this question?");
        Assertions.assertTrue(actual);
    }

    // 15
    @Test
    public void test_find_MW_DeleteQuestion_ConfirmButton(){
        driver.findElement(By.id(Constant.DELETE_MESSAGE_BUTTON_ID)).click();
        driver.findElement(By.id(Constant.DELETE_QUESTION_CONFIRM_BUTTON_ID)).click();

//        driver.switchTo().activeElement();
//        boolean act = driver.getPageSource().contains("Are you sure you want to delete this question?");
//        Assertions.assertTrue(act);
    }

    // 16
    @Test
    public void test_find_MW_DeleteQuestion_CancelButton(){
        driver.findElement(By.id(Constant.DELETE_MESSAGE_BUTTON_ID)).click();
        driver.findElement(By.id(Constant.DELETE_QUESTION_CANCEL_BUTTON_ID)).click();
    }

    // 17
    @Test
    public void test_find_MW_DeleteQuestion_CloseButton(){
        driver.findElement(By.id(Constant.DELETE_MESSAGE_BUTTON_ID)).click();
        driver.findElement(By.id(Constant.POP_UP_CLOSE_BUTTON_ID)).click();
    }

    @After
    public void close() {
        driver.quit();
    }
}
