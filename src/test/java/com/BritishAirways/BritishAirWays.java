package com.BritishAirways;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class BritishAirWays {
	WebDriver driver;

	@Test
	public void registerAccount() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", ".//src/main/resources//Drivers//chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("https://www.britishairways.com/");
		driver.manage().window().maximize();
		impliWait(10);
		driver.findElement(By.xpath("//button[.='Agree to all cookies']")).click();

//		driver.navigate().refresh();
		impliWait(3);

		List<String> toCityList = new ArrayList<String>();
		toCityList.add("Hyderabad");
		toCityList.add("chennai");
		toCityList.add("Mumbai");
		toCityList.add("New Delhi");
		toCityList.add("Kolkata");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,320)", "");
		impliWait(3);
		int index = getRandomEle(toCityList);
		String finalWord = toCityList.get(index);

		int index1 = getRandomEle(toCityList);
		String finalWord2 = toCityList.get(index1);

		driver.findElement(By.xpath("//input[@id='from']")).sendKeys(finalWord);
		driver.findElement(By.xpath("//ul[@class='resultsContainer choicesContainer']/li[1]")).click();

//		driver.findElement(By.xpath("//input[@id='to']")).sendKeys(finalWord2);
//		driver.findElement(By.xpath("//ul[@class='resultsContainer choicesContainer']/li[1]")).click();
		driver.findElement(By.xpath("//div[@id='typeahead-to']/input[@name='searchEntry']")).sendKeys("Heathrow", Keys.ENTER);
		impliWait(3);
		driver.findElement(By.xpath("//div[@id='flight-outbound-date-selector']")).click();
		
		


		// calender selection
		Random random = new Random();
		int rand = 5;

		while (true) {
			rand = random.nextInt(30);
			if (rand > 9)
				break;
		}
		System.out.println(rand);
//		int date = rand;
		int date =9;
		driver.findElement(By.xpath("//div[@id='flight-outbound-date-selector']")).click();

		WebElement sd = driver.findElement(By.xpath("//*[@id=\"calendar-popup\"]/div/div[1]"));
		if (sd.isEnabled()) {
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0,120)", "");
		}
		List<WebElement> dates = driver.findElements(By.xpath(
				"//table[@class='datepicker-table-dates']/tbody/tr/td/div[@tabindex='0' or @data-ng-if='!day.outOfMonth || day.disabled']/span"));
		List<String> dateslist = dates.stream().map(e -> e.getText()).collect(Collectors.toList());
		System.out.println(dateslist);

		driver.findElement(By.xpath(
				"//table[@class='datepicker-table-dates']/tbody/tr/td/div[@tabindex='0' or @data-ng-if='!day.outOfMonth || day.disabled']/span[.='"
						+ date + "']"))
				.click();

		driver.findElement(By.xpath("//*[@id=\"sb-Flight-pax-mix\"]")).click();
		impliWait(3);
		js.executeScript("window.scrollBy(0,100)");

		// passengers Section
		WebElement noOfAdult = driver.findElement(By.xpath("//*[@id=\"AdultKey0\"]/div[1]/span"));

		System.out.println("initial no of adults is  : " + noOfAdult.getText());
		int first = Integer.valueOf(noOfAdult.getText());
		System.out.println(first);

		js.executeScript("window.scrollBy(0,50)");

//			driver.findElement(By.xpath("//*[@id=\"AdultKey0\"]/div[3]/div/button[2]")).click();
		impliWait(3);
		while (Integer.valueOf(noOfAdult.getText()) != 4 && Integer.valueOf(noOfAdult.getText()) < 4) {
			impliWait(3);
			driver.findElement(By.xpath("//*[@id='AdultKey0']/div[3]/div/button[2]")).click();
			impliWait(3);
			if ((++first) == Integer.valueOf(noOfAdult.getText())) {
				System.out.println("after increasing no of adults is   : " + noOfAdult.getText());
				System.out.println("success");
			} else {
				System.out.println("Failed");
			}
		}

		WebElement confirm = driver.findElement(By.xpath("//*/button[.='Confirm']"));
		confirm.click();

		// Travel class section
		driver.findElement(By.xpath("//*[@id='sb-flight-travel-class']/span[2][.='Travel class']")).click();
		impliWait(3);
		List<String> randomList1 = new ArrayList<String>();
		randomList1.add("Economy");
		randomList1.add("Primium economy");
		randomList1.add("Business");
		randomList1.add("First");
		int index2 = getRandomEle(randomList1);
		String finalWord3 = randomList1.get(index2);

		List<WebElement> travelClass = driver.findElements(By.xpath(
				"//div[@class='pop-up-dialog-content ng-scope']/div//label[@class='radio-button bold ng-binding']"));

		for (int i = 0; i < travelClass.size(); i++) {
			String text = travelClass.get(i).getText();
			// System.out.println("Text is: " + text);
			if (text.contains(finalWord3)) {
				travelClass.get(i).click();

				js.executeScript("window.scrollBy(0,100)");

				impliWait(3);
				driver.findElement(By.xpath("//*[@id='NaN']/button")).click();
				System.out.println("done SoMax");
				break;
			} else {
				System.out.println("Failure to select so it select default location");
			}
		}
		impliWait(3);

		driver.findElement(By.xpath("//button[@class='primary search-button']")).click();

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
