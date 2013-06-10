import java.io.IOException;

import org.openqa.selenium.Dimension;

public class TestAdmartketplace  {
	private FirefoxTest ff;
	private Dimension[] resolutions;
	private String[] pages;
	private final int screenHeight = 600;
	private final String siteBasePath = "http://clients.adciserver.com/admarketplace/";
	final private String filePath = "c:\\tmp\\ads";
	
    public static void main(String[] args) throws IOException {
    	TestAdmartketplace test = new TestAdmartketplace();
    	test.runTest();
    }
    
    public TestAdmartketplace() {
		this.ff = new FirefoxTest(this.filePath);
    	this.resolutions = new Dimension[4];
    	this.resolutions[0] = new Dimension(320, this.screenHeight);
    	this.resolutions[1] = new Dimension(980, this.screenHeight);
    	this.resolutions[2] = new Dimension(1024, this.screenHeight);
    	this.resolutions[3] = new Dimension(1250, this.screenHeight);
    	this.pages = new String[6];
    	this.pages[0] = "";
    	this.pages[1] = "categories-location";
    	this.pages[2] = "news";
    	this.pages[3] = "contact-us";
    	this.pages[4] = "adci";
    	this.pages[5] = "ads/sneakers-future";
    }
    
    public void runTest() throws IOException {
    	for (int j = 0; j < this.pages.length; j++) {
	    	for (int i = 0; i < this.resolutions.length; i++) {
	        	this.ff.getPage(this.siteBasePath + this.pages[j]);
	        	this.ff.chageScreenSize(this.resolutions[i]);
	        	this.ff.takeScreenshot("ads-" + this.pages[j] + "-" + this.resolutions[i].width + "-" + this.resolutions[i].height);
	    	}
    	}
    	this.ff.closeBrowser();
    }
    
}