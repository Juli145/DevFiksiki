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
    public void test2_fileSystemFilter_amountOfSystems() {
        Select dropdown = new Select(methodsQ.getFileTypeFilter());
        List<WebElement> elementCount = dropdown.getOptions();
        Assert.assertEquals(4, elementCount.size());
    }

    @Test
    public void test3_checkLocalStorage_stateValuesOfFilters() throws InterruptedException {
        methodsQ.addQuestion("Test", "JS", "true", "CSV");
        methodsQ.chooseFileSystem("CSV");
        methodsQ.chooseTheme("JS");
        DriverConfig.driver.navigate().refresh();
        MethodsQ.LocalStorageJS js = new MethodsQ.LocalStorageJS(DriverConfig.driver);
        Assert.assertEquals("Local Storage theme filter is not needed",
                "js",
                js.getLocalStorageValue("theme_filter"));
        Assert.assertEquals("Local Storage file system filter is not needed",
                "csv",
                js.getLocalStorageValue("file_filter"));
        methodsQ.deleteLastAddedQuestion();
    }

    @Test
    public void test4_sortingByTimeAdded() throws InterruptedException {
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
            Thread.sleep(700);
        }
    }

    @Test
    public void test5_checkFilterByQuestionTopic() throws InterruptedException {
        methodsQ.addQuestion("REACT topic", "REACT", "true", "JSON");
        methodsQ.checkFilterByTopic("React", "REACT topic");
        methodsQ.addQuestion("HTML topic", "HTML", "true", "JSON");
        methodsQ.checkFilterByTopic("HTML", "HTML topic");
        methodsQ.addQuestion("CSS topic", "CSS", "true", "JSON");
        methodsQ.checkFilterByTopic("CSS", "CSS topic");
        methodsQ.addQuestion("JS topic", "JS", "true", "JSON");
        methodsQ.checkFilterByTopic("JS", "JS topic");
        methodsQ.addQuestion("OOP topic", "OOP", "true", "JSON");
        methodsQ.checkFilterByTopic("OOP", "OOP topic");
        methodsQ.chooseTheme("All themes");
        methodsQ.checkPageForFiveElementsAndDelete("REACT topic", "REACT topic",
                "JS topic", "OOP topic", "CSS topic");
    }

    @Test
    public void test6_checkFilterByFileSystem() throws InterruptedException {
        methodsQ.addQuestion("YAML file system", "HTML", "true", "YAML");
        methodsQ.checkFilterByFileSystem("YAML file system", "YAML");
        methodsQ.addQuestion("CSV file system", "HTML", "true", "CSV");
        methodsQ.checkFilterByFileSystem("CSV file system", "CSV");
        methodsQ.addQuestion("JSON file system", "HTML", "true", "JSON");
        methodsQ.checkFilterByFileSystem("JSON file system", "JSON");
        methodsQ.addQuestion("XML file system", "HTML", "true", "XML");
        methodsQ.checkFilterByFileSystem("XML file system", "XML");
        methodsQ.createQuestionWithAllFileSystems("all filters");
        methodsQ.checkPageByFileSystemAllAndDelete("all filters");
    }

    @After
    public void finish() {
        DriverConfig.quit();
    }
}
