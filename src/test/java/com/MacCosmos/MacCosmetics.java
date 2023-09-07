package com.MacCosmos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MacCosmetics {
	WebDriver driver;

	@Test(priority = 2)
	 public void loginwithCorrCredentials() throws Exception {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", ".//src/main/resources//Drivers//chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("https://www.maccosmetics.in/");
		driver.manage().window().maximize();
		
		Thread.sleep(8000);
		driver.findElement(By.xpath("//button[.='OK, ACCEPT ALL']")).click();
		Thread.sleep(3000);
		driver.navigate().refresh();
// driver.findElement(By.xpath("//*[@id=\"in99\"]/svg/svg")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"site-my-mac\"]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[.='Register']")).click();
// List<String> randomList1 = new ArrayList<String>();
// randomList1.add("athira02@mailinator.com");
// randomList1.add("abhinav14@mailinator.com");
// randomList1.add("ramyaN6@mailinator.com");
// randomList1.add("karthikBB4@mailinator.com");
// int index1 = getRandomElement(randomList1);
// String finalWord1 = randomList1.get(index1);
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
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,150)");
		Thread.sleep(2000);
		WebElement chck = driver
				.findElement(By.xpath("//*[@id=\"registration_short\"]/fieldset/div[1]/div/div[5]/div[1]/div"));
		WebElement chck1 = driver
				.findElement(By.xpath("//*/label/span[.='YES! I WOULD LIKE TO HEARFROM M·A·C COSMETICS ONLINE.']"));
		Thread.sleep(2000);
// chck.click();
		jse.executeScript("arguments[0].click();", chck);
		Thread.sleep(2000);
		chck1.click();
		jse.executeScript("arguments[0].click();", chck1);
// Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"registration_short\"]/fieldset/div[2]/div/input")).click();
		Thread.sleep(2000);
		String exp = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/h2")).getText();
		String act = "ACCOUNT PROFILE";
		Assert.assertEquals(exp, act);
		Thread.sleep(5000);
		WebElement checkBox = driver
				.findElement(By.xpath("//*[@id=\"form--profile_preferences--field--TITLE--index--mr_label\"]"));
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
		Thread.sleep(2000);
		List<WebElement> dayList = driver.findElements(By.xpath("//div[@id='select2-drop']/ul/li/div"));
// generating random date
		Random random = new Random();
		int rand11 = 1;
		while (true) {
			rand11 = random.nextInt(31);
			if (rand11 > 1)
				break;
		}
// System.out.println(rand);
		int ranDate = rand11;
		Thread.sleep(2000);
		dayList.get(ranDate).click();
		Thread.sleep(2000);
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
// System.out.println(rand);
		int randMonth = rand2;
		Thread.sleep(4000);
		monthList.get(randMonth).click();
		Thread.sleep(2000);
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
// System.out.println(rand);
		int randYear = rand3;
		Thread.sleep(4000);
		yearList.get(randYear).click();
		Thread.sleep(2000);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,600)");
		driver.findElement(By.xpath("//input[@class='btn btn--full form-submit']")).click();
		Thread.sleep(2000);
		String exp_updateMsg = driver.findElement(By.xpath("//li[@id='account_updated..']")).getText();
		String act_updateMsg = "YOUR PERSONAL INFORMATION HAS BEEN UPDATED.";
		Assert.assertEquals(act_updateMsg, exp_updateMsg);
		System.out.println(exp_updateMsg);
		driver.quit();
	}

	public int getRandomElement(List<String> list) {
		Random rand = new Random();
		return rand.nextInt(list.size());
	}
}