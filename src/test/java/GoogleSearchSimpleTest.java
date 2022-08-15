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

    @Test
    public void testGoogleImageSearchResultsAreImages() {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("octothorpe" + Keys.ENTER);         // searching some word
        driver.findElement(By.xpath("//a[contains(@data-hveid, 'QAw')]")).click();  // "Images" link has a "data-hveid" attribute, which always ends with a "QAw"
        WebElement container = driver.findElement(By.xpath("//div[@id='islrg']"));  // getting images container
        List<WebElement> imgs = container.findElements(By.tagName("img"));                       // gathering "img" tags on this container
        assertTrue(imgs.size() > 20);                                                   // checking that there are at least 20 images in the list
    }
}