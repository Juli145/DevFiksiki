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

    @Test
    public void test7_edit_validationFieldHeight () {
        methods.getEditButton().click();
        methods.fieldClear_SendKeys_Save (methods.getValueHeight(), "");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueHeight(), "0");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueHeight(), "1000");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueHeight(), "171");
        Assert.assertTrue(methods.getEditButton().isDisplayed());
        Assert.assertEquals("171", methods.getValueHeight().getAttribute("value"));
    }

    @Test
    public void test8_edit_validationFieldWeight () {
        methods.getEditButton().click();
        methods.fieldClear_SendKeys_Save (methods.getValueWeight(), "");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueWeight(), "0");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueWeight(), "1000");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueWeight(), "80");
        Assert.assertTrue(methods.getEditButton().isDisplayed());
        Assert.assertEquals("80", methods.getValueWeight().getAttribute("value"));
    }

    @Test
    public void test9_edit_validationFieldName () {
        methods.getEditButton().click();
        methods.fieldClear_SendKeys_Save (methods.getFieldName(), "");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.getFieldName().sendKeys("Алоизий Бенджи Кобвеб Дартаньян Эгб");
        methods.getSaveButton().click();
        Assert.assertTrue(methods.getEditButton().isDisplayed());
        Assert.assertEquals("Алоизий Бенджи Кобвеб Д", methods.getFieldName().getAttribute("value"));
    }

    @Test
    public void test10_edit_validationFieldAge () {
        methods.getEditButton().click();
        methods.fieldClear_SendKeys_Save (methods.getValueAge(), "");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueAge(), "0");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueAge(), "1000");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueAge(), "22");
        Assert.assertTrue(methods.getEditButton().isDisplayed());
        Assert.assertEquals("22", methods.getValueAge().getAttribute("value"));
    }

    @Test
    public void test11_edit_validationFieldHobbies () {
        methods.getEditButton().click();
        methods.fieldClear_SendKeys_Save (methods.getValueHobbies(), "");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.getValueHobbies().sendKeys("Дивитися фантастику, рибалити куховарити");
        methods.getSaveButton().click();
        Assert.assertTrue(methods.getEditButton().isDisplayed());
        Assert.assertEquals("Дивитися фантастику, рибалити куховар", methods.getValueHobbies().getAttribute("value"));
    }

    @Test
    public void test12_edit_validationFieldBirthday () {
        methods.getEditButton().click();
        methods.fieldClear_SendKeys_Save (methods.getValueBirthday(), "");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueBirthday(), "test");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueBirthday(), "12121998");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueBirthday(), "32.12.2000");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueBirthday(), "30.13.2000");
        Assert.assertTrue(methods.getSaveButton().isDisplayed());
        methods.fieldClear_SendKeys_Save (methods.getValueBirthday(), "30.12.2000");
        Assert.assertFalse(methods.getSaveButton().isDisplayed());
        Assert.assertEquals("30.12.2000", methods.getValueBirthday().getAttribute("value"));
    }


    @After
    public void finish() {
        DriverConfig.quit();
    }
}
