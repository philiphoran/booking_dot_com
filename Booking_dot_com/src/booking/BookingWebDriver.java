package booking;

import java.security.InvalidParameterException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingWebDriver implements WebDriver {

	private WebDriver webDriver;

	public BookingWebDriver(String browserType) {
		initWebDriver(browserType);
		navigateToURL("https://www.booking.com");
		clickAcceptOnCookieMessage();
	}

	public WebDriver initWebDriver(String browserType) {
		if (browserType == null) {
			browserType = "chrome";
		}
		switch (browserType) {

		case "chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
			webDriver = new ChromeDriver(); // Chrome WebDriver definition
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver-v0.24.0-win64\\geckodriver.exe");
			webDriver = new FirefoxDriver(); // Firefox WebDriver definition
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", "C:\\Drivers\\edgedriver_win64\\msedgedriver.exe");
			webDriver = new EdgeDriver(); // Edge WebDriver definition
			break;
		default:
			throw new InvalidParameterException("Invalid Browser");
		}
		webDriver.manage().window().maximize();
		return webDriver;
	}

	public void sleep(int sleepMilliseconds) {
		try {
			Thread.sleep(sleepMilliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Page Navigation and Input Controls methods
	public void navigateToURL(String URL) {
		getWebDriver().get(URL);
		WebDriverWait waitForPageLoad = new WebDriverWait(getWebDriver(), 30);
		waitForPageLoad.until(ExpectedConditions.jsReturnsValue("return (document.readyState == 'complete' && jQuery.active == 0);"));
	}

	public void clickAcceptOnCookieMessage() {
		// Content on the page changes with every run, page is not consistent.

		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(getWebDriver()).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(200)).ignoring(org.openqa.selenium.NoSuchElementException.class);

		try {
			fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(), 'Manage cookie preferences')]")));
		} catch (Exception e) {
			try {
				fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Manage cookie preferences')]")));
			} catch (Exception f) {
				fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Manage cookie preferences')]")));
			}
		}

		try {
			WebElement acceptCookies = getWebDriver().findElement(By.xpath("//span[contains(text(), 'Accept')]"));
			acceptCookies.click();
		} catch (Exception e) {
			WebElement acceptCookies = getWebDriver().findElement(By.xpath("//button[contains(text(), 'Accept')]"));
			acceptCookies.click();
		}

	}

	@Override
	public void get(String url) {
		this.webDriver.get(url);
	}

	@Override
	public String getCurrentUrl() {
		return this.webDriver.getCurrentUrl();
	}

	@Override
	public String getTitle() {
		return this.webDriver.getTitle();
	}

	@Override
	public List<WebElement> findElements(By by) {
		return this.webDriver.findElements(by);
	}

	@Override
	public WebElement findElement(By by) {
		return this.webDriver.findElement(by);
	}

	@Override
	public String getPageSource() {
		return this.webDriver.getPageSource();
	}

	@Override
	public void close() {
		this.webDriver.close();
	}

	@Override
	public void quit() {
		this.webDriver.quit();
	}

	@Override
	public Set<String> getWindowHandles() {
		return this.webDriver.getWindowHandles();
	}

	@Override
	public String getWindowHandle() {
		return this.webDriver.getWindowHandle();
	}

	@Override
	public TargetLocator switchTo() {
		return this.webDriver.switchTo();
	}

	@Override
	public Navigation navigate() {
		return this.webDriver.navigate();
	}

	@Override
	public Options manage() {
		return this.webDriver.manage();
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}
}
