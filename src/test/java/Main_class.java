import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Main_class {
    private WebDriver driver;
    private static final String BASE_URL = "https://app.indusinsure.indusind.com/partner/signin/";
    private static final String VALID_USERNAME = "IBL121013";
    private static final String VALID_PASSWORD = "Welcome@222";
    private static final String INVALID_PASSWORD = "Welcome@2";

    @BeforeClass
    public void setUp() {
        System.setProperty("web driver.chrome.driver", "C:\\Users\\Laptops Garage\\Downloads\\chrome-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void loginWithValidCredentials() {
        driver.get(BASE_URL);

        WebElement userNameField = driver.findElement(By.xpath("/html/body/div/div/main/div[1]/div/form/div/input")); // Update XPath to be more reliable
        userNameField.sendKeys(VALID_USERNAME);

        WebElement passwordField = driver.findElement(By.xpath("/html/body/div/div/main/div[1]/div/form/div/div[2]/input")); // Update XPath to be more reliable
        passwordField.sendKeys(VALID_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath("/html/body/div/div/main/div[1]/div/form/div/div[3]/button[2]/span")); // Update XPath to be more reliable
        loginButton.click();

        String expectedUrl = "https://app.indusinsure.indusind.com/ibl/T1/home/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL does not match!");


    }

    @Test(priority = 2)
    public void loginWithInvalidCredentials() {
        driver.get(BASE_URL);

        WebElement userNameField = driver.findElement(By.xpath("/html/body/div/div/main/div[1]/div/form/div/input")); // Update XPath to be more reliable
        userNameField.sendKeys(VALID_USERNAME);

        WebElement passwordField = driver.findElement(By.xpath("/html/body/div/div/main/div[1]/div/form/div/div[2]/input")); // Update XPath to be more reliable
        passwordField.sendKeys(INVALID_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath("/html/body/div/div/main/div[1]/div/form/div/div[3]/button[2]/span")); // Update XPath to be more reliable
        loginButton.click();

        String expectedUrl = "https://app.indusinsure.indusind.com/credential-error/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL does not match!");
    }
    //This Journey only for life insurance
    @Test(priority = 3)
    public void navigateLifeJourney() {
        loginWithValidCredentials(); // Reuse the successful login

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the goalBased element to be clickable
        WebElement goalBased = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='border-b-2 border-b-custom-gray pb-6 mt-6 px-4 sm:px-0']//img[@class='max-w-full h-auto']")));

        // Click the goalBased element
        goalBased.click();
        String LifeExpectedUrl="https://app.indusinsure.indusind.com/ibl/T1/verifyCustomer/?package_code=INDUSIND_ENDOWMENT_RETAIL&type=RETAIL";
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.urlToBe(LifeExpectedUrl));

        String actualUrl1 = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl1, LifeExpectedUrl, "URL does not match!");

    }

    @Test(priority = 4)
    public void navigateHealthJourney() {
        loginWithValidCredentials(); // Reuse the successful login
        System.out.println("Success");
    }
    @Test(priority = 5)
    public void navigateMotorJourney() {
        loginWithValidCredentials(); // Reuse the successful login
        System.out.println("Success");
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

