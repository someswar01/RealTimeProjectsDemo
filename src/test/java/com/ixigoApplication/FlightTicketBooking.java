package com.ixigoApplication;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class FlightTicketBooking {

	WebDriver driver;
	JavascriptExecutor js21 = (JavascriptExecutor) driver;

	@Test
	public void flightBooking() throws Exception {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", ".//src/main/resources//Drivers//chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("https://www.ixigo.com/flights");
		driver.manage().window().maximize();

		// sending random value into "From" Search
		List<String> randomList = new ArrayList<String>();
		randomList.add("HYD");
		randomList.add("MAA");
		randomList.add("DXB");

		int index = getRandomElement(randomList);
		String finalWord = randomList.get(index);
		driver.findElement(By.xpath(
				"//div[@class='orgn u-ib u-v-align-bottom u-text-left']//div[@class='clear-input ixi-icon-cross']"))
				.click();
		impliWait(3);
		driver.findElement(By.xpath("//div[.='From']/input")).sendKeys(finalWord);
		impliWait(3);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[5]/div/div/div[1]/div/div[3]/div/div[1]"))
				.click();

		// sending random value into "To" Search
		List<String> randomList1 = new ArrayList<String>();
		randomList1.add("BLR");
		randomList1.add("BOM");
		randomList1.add("DEL");

		int index1 = getRandomElement(randomList1);
		String finalWord1 = randomList1.get(index1);

		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[5]/div/div/div[3]/div/div[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[5]/div/div/div[3]/div/div[1]/input")).sendKeys(finalWord1);
		impliWait(4);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[5]/div/div/div[3]/div/div[3]/div/div[1]"))
				.click();
		
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[5]/div/div/div[4]/div/div[1]/div/input")).click();

		// calender Handling for last lowest value from three months
		impliWait(3);

		List<WebElement> row = driver.findElements(By.xpath(
				"//div[@class='rd-date']//div//td[@class='rd-day-body start-of-month' or  @class='rd-day-body start-of-month low' or  @class='rd-day-body start-of-month high' or @class='rd-day-body' or @class='rd-day-body low' or @class='rd-day-body high' or @class='rd-day-body end-of-month' or @class='rd-day-body end-of-month low' or @class='rd-day-body end-of-month high' or @class='rd-day-body trip-date trip-oneWay rd-day-selected' or @class='rd-day-body trip-date trip-oneWay rd-day-selected low' or @class='rd-day-body trip-date trip-oneWay rd-day-selected high']//div[@class='info']"));
		impliWait(3);
		List<String> ThirdMonth = row.stream().map(e -> e.getText()).collect(Collectors.toList());

		int twoMonthIndex = row.size();
		System.out.println(twoMonthIndex);
		driver.findElement(By.xpath("//button[@class='ixi-icon-arrow rd-next']")).click();
		impliWait(5);
		row.addAll(driver.findElements(By.xpath(
				"//div[@class='rd-date']//div[2]//td[@class='rd-day-body start-of-month' or  @class='rd-day-body start-of-month low' or  @class='rd-day-body start-of-month high' or @class='rd-day-body' or @class='rd-day-body low' or @class='rd-day-body high'  or @class='rd-day-body end-of-month' or @class='rd-day-body end-of-month low' or @class='rd-day-body end-of-month high']//div[@class='info']")));
		int overallIndex = row.size();
		System.out.println(overallIndex);

		for (int i = twoMonthIndex; i < row.size(); i++) {
			ThirdMonth.add(row.get(i).getText());
		}
		boolean flag = false;
		int temp = 0, n = 0, col = 0, priceInt = 675875;
		for (String s : ThirdMonth) {
			temp = Integer.valueOf(s);
			if (temp <= priceInt) {
				priceInt = temp;
				n = col;
			}
			col++;
		}
		if (n >= twoMonthIndex) {
			flag = true;
		}

		if (!flag) {
			driver.findElement(By.xpath("//button[@class='ixi-icon-arrow u-rotate-180 rd-back']")).click();
			impliWait(3);
			System.out.println(priceInt);
			List<WebElement> row1 = driver.findElements(By.xpath(
					"//div[@class='rd-date']//div//td[@class='rd-day-body start-of-month' or  @class='rd-day-body start-of-month low' or  @class='rd-day-body start-of-month high' or @class='rd-day-body' or @class='rd-day-body low' or @class='rd-day-body high' or @class='rd-day-body end-of-month' or @class='rd-day-body end-of-month low' or @class='rd-day-body end-of-month high' or @class='rd-day-body trip-date trip-oneWay rd-day-selected' or @class='rd-day-body trip-date trip-oneWay rd-day-selected low' or @class='rd-day-body trip-date trip-oneWay rd-day-selected high']//div[@class='info']"));

			row1.get(n).click();

		} else {
			row.get(n).click();
			System.out.println(priceInt);
		}

		impliWait(3);

		// Travellers and class
		driver.findElement(By.xpath("//input[@value='1 Passenger, Economy']")).click();
		Random random = new Random();
		int rand = 1;
		while (true) {
			rand = random.nextInt(5);
			if (rand > 1)
				break;
		}
//		System.out.println(rand);
		int noOfPass = rand;
		impliWait(3);
		// Adults Slelection
		List<WebElement> Adulist = driver.findElements(By.xpath("//div[@class='u-box-result']/div[1]/div[2]/span"));
		List<String> ranNumForAdu = Adulist.stream().map(e -> e.getText()).collect(Collectors.toList());
		System.out.println(ranNumForAdu);
		driver.findElement(
				By.xpath("//div[@class='u-box-result']/div[1]/div[2]/span[contains(text(),'" + noOfPass + "')]"))
				.click();

		// child Selection
		List<WebElement> chilist = driver.findElements(
				By.xpath("//div[@class='u-box-result']/div[2]/div[2]/span[@class='counter-item u-text-center u-ib']"));
		List<String> ranNumForChild = chilist.stream().map(e -> e.getText()).collect(Collectors.toList());
		System.out.println(ranNumForChild);
		driver.findElement(By.xpath(
				"//div[@class='u-box-result']/div[2]/div[2]/span[@class='counter-item u-text-center u-ib' and .='"
						+ noOfPass + "']"))
				.click();
		impliWait(3);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,100)");

//		// class Type Selection
		List<String> randomList2 = new ArrayList<String>();
		randomList2.add("Economy");
//		randomList2.add("Business");
//		randomList2.add("Premium Economy");

		int index2 = getRandomElement(randomList2);
		String finalWord2 = randomList2.get(index2);
		System.out.println(finalWord2);
		List<WebElement> classType = driver.findElements(By.xpath("//span[@class='radio-list']/div"));
		List<String> randClass = classType.stream().map(e -> e.getText()).collect(Collectors.toList());
		System.out.println(randClass);
		driver.findElement(
				By.xpath("//span[@class='radio-list']/div/span[@class='label u-pos-rel u-ib u-v-align-top' and .='"
						+ finalWord2 + "']"))
				.click();
		jse.executeScript("window.scrollBy(0,-100)");
		driver.findElement(By.xpath("//div[.='Search']")).click();

		jse.executeScript("window.scrollBy(0,100)");
		impliWait(8);

		// clicking Book Btn
		impliWait(8);
		impliWait(3);

		String selected_date = driver.findElement(By.xpath("//*/div/div/div[1]/a[@class='otlk-item current']/div[1]"))
				.getText();
		System.out.println(selected_date);
		// first flight sectional Screenshot
		List<WebElement> flightScreenShots = driver
				.findElements(By.xpath("//div[@class='summary-section']/parent :: div"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)", "");
		impliWait(3);
		File src1 = flightScreenShots.get(0).getScreenshotAs(OutputType.FILE);
		File tar1 = new File(".//src//main//resources//Screenshots//flight1.png");
		FileUtils.copyFile(src1, tar1);

		impliWait(3);
		// Second flight sectional Screenshot

		File src2 = flightScreenShots.get(1).getScreenshotAs(OutputType.FILE);
		File tar2 = new File(".//src//main//resources//Screenshots//flight2.png");
		FileUtils.copyFile(src2, tar2);

		driver.findElement(By.xpath("//div[@data-rank='1']//button")).click();

		// printing navigated page label after clicking and taking some screenshots like
		// Total price and final flight details

		impliWait(4);
		String travelLabel = driver.findElement(By.xpath("//div[@class='leg-summary']/div[1]")).getText();
		System.out.println(travelLabel);
		impliWait(3);

		WebElement totalPriiceScreenshot = driver
				.findElement(By.xpath(" //div[@class=' c-accordion-item u-box expanded collapsible diff-collapsed']"));
		File pricesrc = totalPriiceScreenshot.getScreenshotAs(OutputType.FILE);
		File pricetar = new File(".//src//main//resources//Screenshots//totalPriiceScreenshot.png");
		FileUtils.copyFile(pricesrc, pricetar);
		impliWait(3);

		WebElement flightDetail = driver.findElement(By.xpath("//div[@class=' c-accordion-item u-box expanded']"));
		while (!flightDetail.isDisplayed()) {
			JavascriptExecutor js23 = (JavascriptExecutor) driver;
			js23.executeScript("window.scrollBy(0,550)", "");
		}
		File flightDeSrc = flightDetail.getScreenshotAs(OutputType.FILE);
		File flightDeTar = new File(".//src//main//resources//Screenshots//bookingFlightDetails.png");
		FileUtils.copyFile(flightDeSrc, flightDeTar);

	}

//	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	// implicit wait Function
		public void impliWait(int timeInSec) {
			// implicit wait syntax modified one from version 4
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSec));
		}
		
	public int getRandomElement(List<String> list) {
		Random rand = new Random();
		return rand.nextInt(list.size());
	}

}
