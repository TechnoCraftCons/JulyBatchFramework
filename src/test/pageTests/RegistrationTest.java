package test.pageTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.pageUtils.RegistrationFlow;

public class RegistrationTest {
    WebDriver driver;

    @BeforeMethod
    public void SetUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.setHeadless(true);
        driver = new ChromeDriver(option);
        driver.get("http://eaapp.somee.com/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void CleanUp() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }

    @Test(priority = 1)
    public void UserNameIsRequired(){
        RegistrationFlow rf = new RegistrationFlow(driver);
        rf.RegisterUser("", "Welcome098","Welcome098","abc@abc.com");
        rf.UserNameRequiredValidation();
    }

    @Test(priority = 2)
    public void PassWordIsRequired(){
        RegistrationFlow rf = new RegistrationFlow(driver);
        rf.RegisterUser("myUserName", "", "Welcome098", "abc@abc.com");
        rf.PasswordRequiredValidation();
    }

    @Test(priority = 3)
    public void ConfirmPassWordIsRequired(){
        RegistrationFlow rf = new RegistrationFlow(driver);
        rf.RegisterUser("myUserName", "Welcome098", "", "abc@abc.com");
        rf.ConfirmPwdRequiredValidation();
    }
    @Test(priority = 4)
    public void EmailIsRequired(){
       RegistrationFlow rf = new RegistrationFlow(driver);
       rf.RegisterUser("myUserName", "Welcome098", "Welcome098", "");
       rf.EmailIsRequiredValidation();
    }
}
