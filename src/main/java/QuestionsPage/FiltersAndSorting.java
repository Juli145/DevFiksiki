package QuestionsPage;

import Driver.DriverConfig;
import Driver.Methods;
import Driver.MethodsQ;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FiltersAndSorting {
    MethodsQ methodsQ = new MethodsQ();
    Methods methods = new Methods();

    @Before
    public void setUp() {
        DriverConfig.initialize();
        methods.openPage("QuestionsPage");
    }

    @Test
    public void test1_themeFilter_amountOfThemes() {
        Select dropdown = new Select(methodsQ.getThemeFilter());
        List<WebElement> elementCount = dropdown.getOptions();
        Assert.assertEquals(6, elementCount.size());
    }

    @Test
    public void test2_fileSystemFilter_amountOfSystems () {
        Select dropdown = new Select(methodsQ.getFileTypeFilter());
        List<WebElement> elementCount = dropdown.getOptions();
        Assert.assertEquals(4, elementCount.size());
    }

    @Test
    public void test3_checkLocalStorage_stateValuesOfFilters () {
        methodsQ.addQuestion("Test", "JS", "true", "CSV");
        methodsQ.chooseFileSystem("CSV");
        methodsQ.chooseTheme("JS");
        DriverConfig.driver.navigate().refresh();
        methodsQ.checkQuestionAdded("Test", "true", "JS");
        MethodsQ.LocalStorageJS js = new MethodsQ.LocalStorageJS(DriverConfig.driver);
        Assert.assertEquals("Local Storage theme filter is not needed",
                "js",
                js.getLocalStorageValue("theme_filter"));
        Assert.assertEquals("Local Storage file system filter is not needed",
                "csv",
                js.getLocalStorageValue("file_filter"));
    }

    @Test
    public void test4_sortingByTimeAdded () {
        methodsQ.addQuestion("1", "JS", "true", "JSON");
        methodsQ.addQuestion("2", "JS", "true", "JSON");
        methodsQ.addQuestion("3", "JS", "true", "JSON");
        methodsQ.chooseFileSystem("JSON");
        methodsQ.chooseTheme("JS");
        methodsQ.checkTheSortingNumberQuestion("1", 3);
        methodsQ.checkTheSortingNumberQuestion("2", 2);
        methodsQ.checkTheSortingNumberQuestion("3", 1);
        for (int i = 0; i < 3; i++) {
            methodsQ.deleteLastAddedQuestion();
            MethodsQ.waitForVisibility(methodsQ.getAddQuestionButton(), 2);
        }
    }

    @After
    public void finish() {
        DriverConfig.quit();
    }
}
