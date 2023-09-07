package com.MakeMyTripTest_001;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MakeMTripApplicatioTest {
	Random ran = new Random();
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", ".//src/main/resources//Drivers//chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		impliWait(10);
//		driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
//		driver.findElement(By.id("webklipper-publisher-widget-container-notification-close-div")).click();
		impliWait(5);
		driver.navigate().refresh();
		impliWait(3);

	}

	@Test
	public void test01() throws Exception {

		// ways to take screenshots Full screen, Elemental screenshot and Sectional
		// Screenshots
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement oneWayElement = driver.findElement(By.xpath("//li[.='One Way']"));
		WebElement oneWaySection = driver
				.findElement(By.xpath("//div[@class='flightWidgetSection appendBottom40']/div"));

		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File tar1 = new File(".//src/main/resources//Screenshots/fullScreenshot.png");
		FileUtils.copyFile(src1, tar1);

		File src = oneWayElement.getScreenshotAs(OutputType.FILE);
		File tar = new File(".//src/main/resources//Screenshots/OnewayelementShots.png");
		FileUtils.copyFile(src, tar);

		File src2 = oneWaySection.getScreenshotAs(OutputType.FILE);
		File tar2 = new File(".//src/main/resources//Screenshots/OnewaySectionShots.png");
		FileUtils.copyFile(src2, tar2);

		// From city
		List<String> fromCityList = new ArrayList<String>();
		fromCityList.add("HYD");
		fromCityList.add("MAA");
		fromCityList.add("DXB");

		int index = getRandomEle(fromCityList);
		String finalWord = fromCityList.get(index);

		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/label/span"))
				.click();
		Thread.sleep(2000);
		List<WebElement> fromAutoSugg = driver.findElements(By.xpath(
				"//ul[@class='react-autosuggest__suggestions-list']/li/div/div[@class='pushRight font14 lightGreyText latoBold']"));
		for (int i = 0; i < fromAutoSugg.size(); i++) {
			String text = fromAutoSugg.get(i).getText();
			if (text.contains(finalWord)) {
				fromAutoSugg.get(i).click();
				Thread.sleep(3000);
				break;
			}
		}

		// To city
		List<String> toCityList = new ArrayList<String>();
		toCityList.add("BLR");
		toCityList.add("BOM");
		toCityList.add("DEL");

		int index2 = getRandomEle(toCityList);
		String finalWord2 = toCityList.get(index2);

		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label")).click();
		Thread.sleep(2000);
		List<WebElement> toAutoSugg = driver.findElements(By.xpath(
				"//ul[@class='react-autosuggest__suggestions-list']/li/div/div[@class='pushRight font14 lightGreyText latoBold']"));
		for (int i = 0; i < toAutoSugg.size(); i++) {
			String text = toAutoSugg.get(i).getText();
			if (text.contains(finalWord2)) {
				toAutoSugg.get(i).click();
				Thread.sleep(3000);
				break;
			}
		}

//		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/label/span"))
//				.click();
//		Thread.sleep(2000);
//		Thread.sleep(4000);
//		int var = ran.nextInt(-8 - 1 + 1) + 1;
//		fromAutoSugg.get(var).click();
//		
//		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label")).click();
//		Thread.sleep(4000);
//
//		List<WebElement> to_city =driver.findElements(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li"));
//		Thread.sleep(4000);
//		to_city.get(var+1).click();

		// calender Handling

		int temp, col = 0, n = 0;
		int priceInt = 999999;

		List<WebElement> allPriceValFrom2Mon = driver.findElements(By.xpath("//div[@class='DayPicker-Months']//p[2]"));
		
		List<String> removeUnwantedThingsFromPrice = allPriceValFrom2Mon.stream().map(e -> e.getText())
				.collect(Collectors.toList());
		for (int i = 0; i < removeUnwantedThingsFromPrice.size(); i++) {
			removeUnwantedThingsFromPrice.set(i, removeUnwantedThingsFromPrice.get(i).replace("₹ ", ""));
			removeUnwantedThingsFromPrice.set(i, removeUnwantedThingsFromPrice.get(i).replace(",", ""));
		}

		// Last Minimum value from the Two Months logic
		for (String s : removeUnwantedThingsFromPrice) {
			// String to integer conversion
			temp = Integer.valueOf(s);
			if (temp <= priceInt) {
				priceInt = temp;
				n = col;
			}
			col++;
		}

		System.out.println("Total number of Days Availble from two months" + allPriceValFrom2Mon.size());

		System.out.println(priceInt);
		System.out.println(removeUnwantedThingsFromPrice);
		allPriceValFrom2Mon.get(n).click();

		// Traveler Section
		driver.findElement(By.xpath("//span[.='Travellers & Class']")).click();
		impliWait(2);
		// X path by using Following Sibling
		driver.findElement(By.xpath("//p[@data-cy='adultRange']/following-sibling::ul/li[4]")).click();
		driver.findElement(By.xpath("//p[@data-cy='childrenRange']/following-sibling::ul/li[2]")).click();
		driver.findElement(By.xpath("//p[@data-cy='infantRange']/following-sibling::ul/li[2]")).click();

		WebElement adults = driver.findElement(By.xpath(
				"//p[@data-cy='adultRange']/following-sibling::ul/li[@data-cy = 'adults-4' and @class='selected']"));
		WebElement infant = driver.findElement(By.xpath(
				"//p[@data-cy='infantRange']/following-sibling::ul/li[@data-cy = 'infants-1' and @class='selected']"));

		System.out.println(adults.getText());
		System.out.println(infant.getText());
		if (Integer.valueOf(adults.getText()) < Integer.valueOf(infant.getText())) {
			WebElement error = driver.findElement(By.xpath("//p[.='Number of infants cannot be more than adults']"));
			System.out.println("Error: Adult count is less than Infant count " + error.getText());

			driver.findElement(By.xpath(
					"//*[@id=\"root\"]/div/div[2]/div/div/div/div[2]/div[1]/div[5]/div[2]/div[1]/div/div[2]/ul/li[1]"))
					.click();
			impliWait(2);
		} else {
			System.out.println("Adult count is greater than Infant count");
		}

		// Apply button
		driver.findElement(By.xpath("//button[.='APPLY']")).click();

		// search button

		driver.findElement(By.xpath("//a[.='Search']")).click();
		impliWait(10);
		driver.findElement(By.xpath("//*[contains(text(),'OKAY, GOT IT!')]")).click();
		js.executeScript("window.scrollBy(0,400)", "");
		impliWait(3);
		WebElement ele= driver.findElement(By.xpath(
				"//div[@class='listingCardWrap']//div[@class='clusterContent']/div/div[2]//span[.='View Prices']"));
		if(ele.isDisplayed())
		{
			ele.click();
			js.executeScript("window.scrollBy(0,200)", "");
			driver.findElement(By.xpath("//button[.='Book Now']")).click();

		}
		else {
			driver.findElement(By.xpath("//*[@id=\"bookbutton-RKEY:4669e2f7-7945-4ec7-9f73-70fe5dc26b39:35_0\"]/span[1]")).click();
			js.executeScript("window.scrollBy(0,200)", "");
			driver.findElement(By.xpath("//button[.='Book Now']")).click();

		}
		
		String parentWindow = driver.getWindowHandle();
		
		Set<String> allWindows=driver.getWindowHandles();
		for(String w:allWindows) {
			if(!parentWindow.equals(w)) {
				driver.switchTo().window(w);
				WebElement fareSummery =driver.findElement(By.id("FARE_SUMMARY"));
				File src4 =fareSummery.getScreenshotAs(OutputType.FILE);
				File tar4 = new File(".//src/main/resources//Screenshots/fareSumm.png");
				FileUtils.copyFile(src4, tar4);
			}
		}

	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	// implicit wait Function
	public void impliWait(int timeInSec) {
		// implicit wait syntax modified one from version 4
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSec));
	}

	// Random Function
	public int getRandomEle(List<String> list) {
		Random rand = new Random();
		return rand.nextInt(list.size());
	}
}
