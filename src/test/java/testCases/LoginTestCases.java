package testCases;

import com.cloud.fileHandler.PropertyFileHandler;
import com.cloud.pageActions.Dashboard;
import com.cloud.pageActions.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.NoInjection;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestCases extends BaseTest {

    private Dashboard dashboard;

    @Test
    public void loginWithInvalidCredentials( ) throws IOException {
        initTestCase("TC_1");
        dashboard = new Dashboard(getDriver());
        dashboard.navigateToLoginPage()
                .loginInApp(PropertyFileHandler.getPropertyValue("USERNAME"),
                            PropertyFileHandler.getPropertyValue("PASSWORD"));
    }
}
