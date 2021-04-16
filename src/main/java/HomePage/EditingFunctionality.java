package HomePage;

import Driver.DriverConfig;
import Driver.Methods;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EditingFunctionality {
    @Before
    public void setUp() {
        DriverConfig.initialize();
        methods.openPage("HomePage");
    }

    Methods methods = new Methods();

    @Test
    public void test1_edit_name() {
        methods.getEditButton().click();
        methods.getFieldName().clear();
        methods.getFieldName().sendKeys("Test");
        methods.getSaveButton().click();
        Assert.assertEquals("Test", methods.getFieldName().getAttribute("value"));
    }

    @Test
    public void test2_edit_height() {
        methods.getEditButton().click();
        methods.getValueHeight().clear();
        methods.getValueHeight().sendKeys("191");
        methods.getSaveButton().click();
        Assert.assertEquals("191", methods.getValueHeight().getAttribute("value"));
    }

    @Test
    public void test3_edit_weight() {
        methods.getEditButton().click();
        methods.getValueWeight().clear();
        methods.getValueWeight().sendKeys("72");
        methods.getSaveButton().click();
        Assert.assertEquals("72", methods.getValueWeight().getAttribute("value"));
    }

    @Test
    public void test4_edit_age() {
        methods.getEditButton().click();
        methods.getValueAge().clear();
        methods.getValueAge().sendKeys("33");
        methods.getSaveButton().click();
        Assert.assertEquals("33", methods.getValueAge().getAttribute("value"));
    }

    @Test
    public void test5_edit_birthday() {
        methods.getEditButton().click();
        methods.getValueBirthday().clear();
        methods.getValueBirthday().sendKeys("09.12.1991");
        methods.getSaveButton().click();
        Assert.assertEquals("09.12.1991", methods.getValueBirthday().getAttribute("value"));
    }

    @Test
    public void test6_edit_hobbies() {
        methods.getEditButton().click();
        methods.getValueHobbies().clear();
        methods.getValueHobbies().sendKeys("отдых");
        methods.getSaveButton().click();
        Assert.assertEquals("отдых", methods.getValueHobbies().getAttribute("value"));
    }


    @After
    public void finish() {
        DriverConfig.quit();
    }
}
