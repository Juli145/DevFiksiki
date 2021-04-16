package Driver;

import org.openqa.selenium.support.PageFactory;

public class MethodsQ {
    public MethodsQ() {
        PageFactory.initElements(DriverConfig.getDriver(), this);
    }
}
