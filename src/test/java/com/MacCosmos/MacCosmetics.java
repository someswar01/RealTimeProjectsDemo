package com.MacCosmos;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class MacCosmetics {
	WebDriver driver;

	//@Test(priority = 1)
	public void register() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", ".//src/main/resources//Drivers//chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("https://www.maccosmetics.in/");
		driver.manage().window().maximize();
		impliWait(8);
		driver.findElement(By.xpath("//button[.='OK, ACCEPT ALL']")).click();
		impliWait(3);
		driver.navigate().refresh();
		impliWait(3);
		driver.findElement(By.xpath("//*[@id=\"site-my-mac\"]")).click();
		impliWait(4);
		driver.findElement(By.xpath("//a[.='Register']")).click();
		List<String> randomList1 = new ArrayList<String>();
		String character = "abcdefghijklmnopqrstuvwxyz";
		String randomString = "";
		int length = 8;
		Random rand = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = character.charAt(rand.nextInt(character.length()));
		}
		for (int i = 0; i < text.length; i++) {
			randomString += text[i];
		}
// System.out.println(randomString);
		driver.findElement(By.xpath("//*[@id=\"form--registration_short--field--PC_EMAIL_ADDRESS\"]"))
				.sendKeys(randomString);
		driver.findElement(By.xpath("//*[@id=\"form--registration_short--field--PASSWORD\"]")).sendKeys("athiraR");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,50)");
		driver.findElement(By.xpath("//*[@id=\"registration_short\"]/fieldset/div[2]/div/input")).click();
		String act_msg = driver.findElement(By.xpath("//*[@id=\"invalid.pc_email_address.registration_short\"]"))
				.getText();
		String exp_msg = "PLEASE ENTER YOUR EMAIL ADDRESS IN THE FOLLOWING FORMAT: JULIE@GMAIL.COM";
		Assert.assertEquals(act_msg, exp_msg);
		String act_msg1 = driver
				.findElement(By.xpath("//*[@id=\"required.accepted_privacy_policy.registration_short\"]")).getText();
		String exp_msg1 = "PLEASE INDICATE THAT YOU ACCEPT THE TERMS AND CONDITIONS AND PRIVACY POLICY OF THE MAC COSMETICS SITE IN ORDER TO CREATE AN ACCOUNT.";
		Assert.assertEquals(act_msg1, exp_msg1);
		String act_msg2 = driver.findElement(By.xpath("//*[@id=\"format.password.registration_short\"]")).getText();
		String exp_msg2 = "PLEASE ENTER A PASSWORD THAT IS A MINIMUM OF 8 CHARACTERS, INCLUDING AT LEAST ONE UPPER CASE LETTER, ONE LOWER CASE LETTER, AND ONE NUMBER.";
		Assert.assertEquals(act_msg2, exp_msg2);
	}

	//@Test(priority = 2)
	public void loginwithCorrCredentials() throws Exception {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", ".//src/main/resources//Drivers//chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("https://www.maccosmetics.in/");
		driver.manage().window().maximize();
		impliWait(8);
		driver.findElement(By.xpath("//button[.='OK, ACCEPT ALL']")).click();
		impliWait(3);
		driver.navigate().refresh();
		impliWait(4);
		driver.findElement(By.xpath("//*[@id=\"site-my-mac\"]")).click();
		impliWait(4);
		driver.findElement(By.xpath("//a[.='Register']")).click();
		List<String> randomList1 = new ArrayList<String>();
		String character = "abcdefghijklmnopqrstuvwxyz";
		String randomString = "";
		int length = 8;
		Random rand = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = character.charAt(rand.nextInt(character.length()));
		}
		for (int i = 0; i < text.length; i++) {
			randomString += text[i];
		}
		driver.findElement(By.xpath("//*[@id=\"form--registration_short--field--PC_EMAIL_ADDRESS\"]"))
				.sendKeys(randomString + "@mailinator.com");
		List<String> randomList2 = new ArrayList<String>();
		randomList2.add("Athira01");
		randomList2.add("Abhinav143");
		randomList2.add("ramyaN231");
		randomList2.add("karthikBB1");
		int index2 = getRandomElement(randomList2);
		String finalWord2 = randomList2.get(index2);
		driver.findElement(By.xpath("//*[@id=\"form--registration_short--field--PASSWORD\"]")).sendKeys(finalWord2);
		impliWait(2);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,150)");
		impliWait(3);
		WebElement chck = driver
				.findElement(By.xpath("//*[@id=\"registration_short\"]/fieldset/div[1]/div/div[5]/div[1]/div"));
		WebElement chck1 = driver
				.findElement(By.xpath("//*/label/span[.='YES! I WOULD LIKE TO HEAR FROM M·A·C COSMETICS ONLINE.']"));
		impliWait(3);
		jse.executeScript("arguments[0].click();", chck);
		impliWait(3);
		jse.executeScript("arguments[0].click();", chck1);
		impliWait(3);
		driver.findElement(By.xpath("//*[@id=\"registration_short\"]/fieldset/div[2]/div/input")).click();
// Thread.sleep(2000);
		impliWait(8);
//WebElement popup=driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div"));
//Thread.sleep(5000);
//if(popup.isDisplayed()) {
////*[@id="idww"]
// WebElement closebtn=driver.findElement(By.xpath("//*[@id='idww']"));
//closebtn.click();
//}
// driver.navigate().refresh();
// Thread.sleep(5000);
// driver.findElement(By.xpath("//ul[1][@class='sidebar-menu']//a[.='Account Profile']")).click();
// Thread.sleep(2000);
		
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='ab-in-app-message  ab-html-message ab-modal-interactions']")));
		driver.findElement(By.xpath("//button[@class='bz-close-btn']")).click();
		
		
		String act = "ACCOUNT PROFILE";
		String exp = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/h2")).getText();
		Assert.assertEquals(exp, act);
		
		impliWait(2);
		WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"form--profile_preferences--field--TITLE--index--mr_label\"]"));
		checkBox.click();

		
		String character1 = "abcdefghijklmnopqrstuvwxyz";
		String randomString1 = "";
		int length1 = 8;
		Random rand1 = new Random();
		char[] text1 = new char[length1];
		for (int i = 0; i < length; i++) {
			text1[i] = character1.charAt(rand.nextInt(character.length()));
		}
		for (int i = 0; i < text.length; i++) {
			randomString1 += text1[i];
		}
		driver.findElement(By.xpath("//*[@id=\"form--profile_preferences--field--FIRST_NAME\"]"))
				.sendKeys(randomString1);
		randomString1 = null;
		for (int i = 0; i < length; i++) {
			text1[i] = character1.charAt(rand.nextInt(character.length()));
		}
		for (int i = 0; i < text.length; i++) {
			randomString1 += text1[i];
		}
		driver.findElement(By.xpath("//*[@id=\"form--profile_preferences--field--LAST_NAME\"]"))
				.sendKeys(randomString1);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");
// driver.findElement(By.xpath("//*[@id=\"s2id_form--profile_preferences--field--BIRTH_DAY\"]/a")).click();
		
		
		
		driver.findElement(By.xpath("//span[@id='select2-chosen-1']")).click();
		impliWait(3);
		List<WebElement> dayList = driver.findElements(By.xpath("//div[@id='select2-drop']/ul/li/div"));
// generating random date
		Random random = new Random();
		int rand11 = 1;
		while (true) {
			rand11 = random.nextInt(31);
			if (rand11 > 1)
				break;
		}
		int ranDate = rand11;
		impliWait(3);
		dayList.get(ranDate).click();
		impliWait(3);
		driver.findElement(By.xpath("//span[@id='select2-chosen-2']")).click();
		List<WebElement> monthList = driver.findElements(By.xpath("//div[@id='select2-drop']/ul/li/div"));
		System.out.println(monthList.size());
		Random random1 = new Random();
		int rand2 = 1;
		while (true) {
			rand2 = random1.nextInt(12);
			if (rand2 > 1)
				break;
		}
		int randMonth = rand2;
		impliWait(3);
		monthList.get(randMonth).click();
		impliWait(3);
		driver.findElement(By.xpath("//span[@id='select2-chosen-3']")).click();
		List<WebElement> yearList = driver.findElements(By.xpath("//div[@id='select2-drop']/ul/li/div"));
		System.out.println(yearList.size());
		Random random3 = new Random();
		int rand3 = 1;
		while (true) {
			rand3 = random3.nextInt(20);
			if (rand3 > 1)
				break;
		}
		int randYear = rand3;
		impliWait(3);
		yearList.get(randYear).click();
		impliWait(3);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,600)");
		driver.findElement(By.xpath("//input[@class='btn btn--full form-submit']")).click();
		impliWait(3);
		String exp_updateMsg = driver.findElement(By.xpath("//li[@id='account_updated..']")).getText();
		String act_updateMsg = "YOUR PERSONAL INFORMATION HAS BEEN UPDATED.";
		Assert.assertEquals(act_updateMsg, exp_updateMsg);
		System.out.println(exp_updateMsg);
	}

	@Test(priority = 3)
	public void searchInmailinator() throws Exception {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", ".//src/main/resources//Drivers//chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("https://www.maccosmetics.in/");
		driver.manage().window().maximize();
		impliWait(7);
		driver.findElement(By.xpath("//button[.='OK, ACCEPT ALL']")).click();
		impliWait(4);
		driver.navigate().refresh();
		impliWait(4);
		driver.findElement(By.xpath("//*[@id=\"site-my-mac\"]")).click();
		impliWait(4);
		driver.findElement(By.xpath("//a[.='Register']")).click();
		List<String> randomList1 = new ArrayList<String>();
		String character = "abcdefghijklmnopqrstuvwxyz";
		String randomString = "";
		int length = 8;
		Random rand = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = character.charAt(rand.nextInt(character.length()));
		}
		for (int i = 0; i < text.length; i++) {
			randomString += text[i];
		}
		driver.findElement(By.xpath("//*[@id=\"form--registration_short--field--PC_EMAIL_ADDRESS\"]"))
				.sendKeys(randomString + "@mailinator.com");
		List<String> randomList2 = new ArrayList<String>();
		randomList2.add("Athira01");
		randomList2.add("Abhinav143");
		randomList2.add("ramyaN231");
		randomList2.add("karthikBB1");
		int index2 = getRandomElement(randomList2);
		String finalWord2 = randomList2.get(index2);
		driver.findElement(By.xpath("//*[@id=\"form--registration_short--field--PASSWORD\"]")).sendKeys(finalWord2);
		impliWait(2);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,150)");
		impliWait(2);
		WebElement chck = driver
				.findElement(By.xpath("//*[@id=\"registration_short\"]/fieldset/div[1]/div/div[5]/div[1]/div"));
		WebElement chck1 = driver
				.findElement(By.xpath("//*/label/span[.='YES! I WOULD LIKE TO HEAR FROM M·A·C COSMETICS ONLINE.']"));
		impliWait(2);
		jse.executeScript("arguments[0].click();", chck);
		impliWait(2);
		jse.executeScript("arguments[0].click();", chck1);
		impliWait(2);
		driver.findElement(By.xpath("//*[@id=\"registration_short\"]/fieldset/div[2]/div/input")).click();
		impliWait(2);
		
		//Navigate to mailinator Website
		driver.navigate().to("https://www.mailinator.com/");
		driver.manage().window().maximize();
		impliWait(3);
		driver.findElement(By.xpath("//*[@id=\"search\"]")).sendKeys(randomString + "@mailinator.com", Keys.ENTER);
		Thread.sleep(4000);
		driver.findElement(By.xpath("//table[@class='table-striped jambo_table']/tbody/tr[1]")).click();
		Thread.sleep(7000);
		WebElement details = driver
				.findElement(By.xpath("//div[@class='sender-info d-flex flexcolumn p-xy-20 w-100 primary-border-r']"));
		File src7 = details.getScreenshotAs(OutputType.FILE);
		File tar7 = new File(".//src//main//resources//Screenshots//details.png");
		FileUtils.copyFile(src7, tar7);
	}

	//@AfterMethod
	public void tearMethod() {
		driver.close();
	}

	public int getRandomElement(List<String> list) {
		Random rand = new Random();
		return rand.nextInt(list.size());
	}
	// implicit wait Function
			public void impliWait(int timeInSec) {
				// implicit wait syntax modified one from version 4
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSec));
			}
}