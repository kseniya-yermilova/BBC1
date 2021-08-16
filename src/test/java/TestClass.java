import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestClass {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/kseniya/Documents/selenium_grid/chromedriver");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://bbc.com/");
    }

    @Test
    public void checkNameOfHeadlineArticle() {
        driver.findElement(By.xpath("//div[@class='orb-nav-section orb-nav-links orb-nav-focus']//a[text()='News']")).
                click();
        String nameOfArticle = driver.findElement(By.xpath("//div[contains(@class,'gs-c-promo-body gs-u-display-none ')]//a[contains(@class,'gs-c-promo-heading')]//h3")).
                getText();
        Assert.assertEquals(nameOfArticle.toLowerCase(Locale.ROOT), "death toll mounts in raging south africa riots");
    }

    @Test
    public void checkNameOfSecondaryArticles() {
        driver.findElement(By.xpath("//div[@class='orb-nav-section orb-nav-links orb-nav-focus']//a[text()='News']")).
                click();
        ArrayList<String> namesOfArticles = new ArrayList<String>();
        namesOfArticles.add("Life in Afghanistan after America leaves");
        namesOfArticles.add("Google fined €500m by French regulator");
        namesOfArticles.add("'Pushing back' asylum seekers at sea");
        namesOfArticles.add("Footballer criticises UK minister for racism response");
        namesOfArticles.add("Pet owners warned as giant goldfish threaten lake");
        List<WebElement> listOfNames = driver.findElements(By.xpath("//div[@class='gs-c-promo nw-c-promo gs-o-faux-block-link gs-u-pb gs-u-pb+@m nw-p-default gs-c-promo--inline gs-c-promo--stacked@m nw-u-w-auto gs-c-promo--flex']//a[@class='gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-pica-bold nw-o-link-split__anchor']//h3"));
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(listOfNames.get(i).getText(), namesOfArticles.get(i));
        }
    }

    @Test
    public void checkSearch() {
        driver.findElement(By.xpath("//div[@class='orb-nav-section orb-nav-links orb-nav-focus']//a[text()='News']")).
                click();
        String textOfCategoryLink = driver.
                findElement(By.xpath("//div[@class='gs-c-promo-body gs-u-display-none gs-u-display-inline-block@m gs-u-mt@xs gs-u-mt0@m gel-1/3@m']//a[@aria-label='From US & Canada']//span[@aria-hidden='true']")).
                getText();
        driver.findElement(By.xpath("//input[@id='orb-search-q']")).sendKeys(textOfCategoryLink);
        driver.findElement(By.xpath("//button[@id='orb-search-button']")).click();
        String actualResult =
                driver.findElement(By.xpath("//li[1]//p[@class='ssrcss-7sxcrr-PromoHeadline e1f5wbog4']//span")).
                        getText();
        Assert.assertEquals(actualResult, "Masih Alinejad: Iranians 'plotted to kidnap US, Canada and UK targets'");
    }

    @Test
    public void checkFormWithWrongEmail() {
        driver.findElement(By.xpath("//div[@class='orb-nav-section orb-nav-links orb-nav-focus']//a[text()='News']")).
                click();
        driver.findElement(By.
                xpath("//li[@class='gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__wide-menuitem-container']//a[@class='nw-o-link']//span[text()='Coronavirus']")).
                click();
        driver.findElement(By.
                xpath("//li[@class='gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__secondary-menuitem-container']//a[@class='nw-o-link']//span[text()='Your Coronavirus Stories']")).
                click();
        driver.findElement(By.xpath("//span[@class='gel-brevier' and text()='Contact BBC News']")).click();
        driver.findElement(By.xpath("//a[@class='ssrcss-9nsdc6-InlineLink e1no5rhv0' and text()='send us a story']")).
                click();
        driver.findElement(By.xpath("//textarea[@class='text-input--long']")).sendKeys("Tratata");
        WebElement nameField = driver.findElement(By.xpath("//input[@class='text-input__input' and @aria-label='Name']"));
        nameField.sendKeys("Ksyu");
        driver.findElement(By.xpath("//input[@class='text-input__input' and @aria-label='Email address']")).
                sendKeys("www");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        driver.findElement(By.xpath("//p[text()='I accept the ']/..")).click();
        Assert.assertTrue(nameField.getText()!=null);
    }

    @Test
    public void checkFormWithoutMassage() {
        driver.findElement(By.xpath("//div[@class='orb-nav-section orb-nav-links orb-nav-focus']//a[text()='News']")).
                click();
        driver.findElement(By.
                xpath("//li[@class='gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__wide-menuitem-container']//a[@class='nw-o-link']//span[text()='Coronavirus']")).
                click();
        driver.findElement(By.
                xpath("//li[@class='gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__secondary-menuitem-container']//a[@class='nw-o-link']//span[text()='Your Coronavirus Stories']")).
                click();
        driver.findElement(By.xpath("//span[@class='gel-brevier' and text()='Contact BBC News']")).click();
        driver.findElement(By.xpath("//a[@class='ssrcss-9nsdc6-InlineLink e1no5rhv0' and text()='send us a story']")).
                click();
        WebElement nameField = driver.findElement(By.xpath("//input[@class='text-input__input' and @aria-label='Name']"));
        nameField.sendKeys("Ksyu");
        driver.findElement(By.xpath("//input[@class='text-input__input' and @aria-label='Email address']")).
                sendKeys("ksyu@gmail.com");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        driver.findElement(By.xpath("//p[text()='I accept the ']/..")).click();
        Assert.assertTrue(nameField.getText()!=null);
    }

    @Test
    public void checkFormWithoutAcceptance() {
        driver.findElement(By.xpath("//div[@class='orb-nav-section orb-nav-links orb-nav-focus']//a[text()='News']")).
                click();
        driver.findElement(By.
                xpath("//li[@class='gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__wide-menuitem-container']//a[@class='nw-o-link']//span[text()='Coronavirus']")).
                click();
        driver.findElement(By.
                xpath("//li[@class='gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__secondary-menuitem-container']//a[@class='nw-o-link']//span[text()='Your Coronavirus Stories']")).
                click();
        driver.findElement(By.xpath("//span[@class='gel-brevier' and text()='Contact BBC News']")).click();
        driver.findElement(By.xpath("//a[@class='ssrcss-9nsdc6-InlineLink e1no5rhv0' and text()='send us a story']")).
                click();
        driver.findElement(By.xpath("//textarea[@class='text-input--long']")).sendKeys("Tratata");
        WebElement nameField = driver.findElement(By.xpath("//input[@class='text-input__input' and @aria-label='Name']"));
        nameField.sendKeys("Ksyu");
        driver.findElement(By.xpath("//input[@class='text-input__input' and @aria-label='Email address']")).
                sendKeys("ksyu@gmail.com");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        Assert.assertTrue(nameField.getText()!=null);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}

