import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FirefoxTest {
	
	private FirefoxDriver driver;
	private String filePath;
	private WebDriverWait wait;

	public FirefoxDriver getDriver() {
		return this.driver;
	}
	
	public FirefoxTest(String filePath) {
		this.driver = new FirefoxDriver();
	    this.wait = new WebDriverWait(driver, 10);
		this.filePath = filePath;
	}
	
	public void getPage(String url) {
    	driver.get(url);		
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public void reloadPage() {
		driver.get(this.getCurrentUrl());
	}
 	
	public void takeScreenshot(String filename) throws IOException {
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	FileUtils.copyFile(scrFile, new File(this.filePath + "\\" + filename + ".png"));
	}

    public void loginAs(String username, String password) {
        driver.findElement(By.name("name")).sendKeys(username);
        driver.findElement(By.name("pass")).sendKeys(password);
        this.formSubmit(".save");
    }
    
    public void formSubmit(String selector) {
    	driver.findElementByCssSelector(selector).submit();
    }

    public void clickElement(String selector) {
    	driver.findElementByCssSelector(selector).click();
    }
    
    public void input(String selector, String input) {
        driver.findElementByCssSelector(selector).sendKeys(input);
    }
    
    public void waitFor(String selector) {
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector)));
    }
    
    public void waitWhile(String selector) {
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(selector)));
    }
    
    public void clearSession() {
    	driver.manage().deleteAllCookies();
    }
    

    public void delay(int seconds) {
    	driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
    
    public boolean isElemExist(String elemSelector) {
    	try {
    		driver.findElementByCssSelector(elemSelector);
    		return true;
    	}
    	catch(NoSuchElementException error) {
    		return false;
    	}
    }

    public WebElement getElem(String selector) {
    	try {
    		WebElement elem = driver.findElementByCssSelector(selector);
    		return elem;
    	}
    	catch(NoSuchElementException error) {
    		return null;
    	}
    }
    
    public void closeBrowser() {
    	this.driver.close();
    	this.driver.quit();
    }

	public void chageScreenSize(Dimension dimension) {
		this.driver.manage().window().setSize(dimension);
		
	}
}
