import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.testng.Assert.assertTrue;

public class GoogleSearchSimpleTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

    /**
     * This method is used to check that an image tab of search results of the Google site contains images.
     * We are searching some word.
     * Following the "Images" link, that has a "data-hveid" attribute, which always ends with a "QAw".
     * Getting images container.
     * Gathering "img" tags on this container.
     * Checking that there are at least 20 images in the list.
     */
    @Test
    public void testGoogleImageSearchResultsAreImages() {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("octothorpe" + Keys.ENTER);
        driver.findElement(By.xpath("//a[contains(@data-hveid, 'QAw')]")).click();
        WebElement container = driver.findElement(By.xpath("//div[@id='islrg']"));
        List<WebElement> imgs = container.findElements(By.tagName("img"));
        assertTrue(imgs.size() > 20);
    }
}