package Driver;

import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

@Getter

public class MethodsQ {
    public MethodsQ() {
        PageFactory.initElements(DriverConfig.getDriver(), this);
    }
}
