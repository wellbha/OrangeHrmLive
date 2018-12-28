import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class OrangeHrmLiveLogin {
    static WebDriver driver;
    public void clickElementBy(By by){
        driver.findElement(by).click();
    }
    public void enterText(By by, String text){
        driver.findElement(by).sendKeys(text);
    }


    @BeforeMethod //run before every method
    public void openingBrowser(){
        System.setProperty("webdriver.chrome.driver","src\\BrowserDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
    }
    @AfterMethod //run after every method
    public void closingBrowser(){
       driver.quit();
    }

    @Test
    public void toVerifyOnceEmployeeAddedThenShouldBeAbleToLoginSuccessfully() throws InterruptedException {
        enterText(By.id("txtUsername"),"Admin");
        enterText(By.id("txtPassword"),"admin123");
        clickElementBy(By.id("btnLogin"));
        clickElementBy(By.id("menu_pim_viewPimModule"));
        clickElementBy(By.id("menu_pim_addEmployee"));
        DateFormat dateFormat = new SimpleDateFormat("DDMMYYYHHMMSS");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        enterText(By.id("firstName"),"Manoj");
        enterText(By.id("middleName"),"K");
        enterText(By.id("lastName"),"Shah");
        clickElementBy(By.id("chkLogin"));
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("status")));
        enterText(By.id("user_name"),"Manojkshah"+date1);
        String userName = driver.findElement(By.id("user_name")).getAttribute("value");
        System.out.println(userName);
        enterText(By.id("user_password"),"Mypass@12"); //please change the password adding one more digit to avoid relogin issue
        String password = driver.findElement(By.id("user_password")).getAttribute("value");
        System.out.println(password);
        enterText(By.id("re_password"),"Mypass@12"); // change the password as above to match
        clickElementBy(By.id("btnSave"));
        clickElementBy(By.id("welcome"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
        clickElementBy(By.linkText("Logout"));
        enterText(By.id("txtUsername"),userName);
        enterText(By.id("txtPassword"),password);
        clickElementBy(By.id("btnLogin"));
    }

}
