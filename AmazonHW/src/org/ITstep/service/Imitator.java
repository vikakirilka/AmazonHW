package org.ITstep.service;

import java.util.List;
//import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.ITstep.dao.AccountDAO;
import org.ITstep.model.AccRandom;
import org.ITstep.model.Account;
import org.ITstep.model.Good;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Imitator {

	private static final String BASE_URL = "https://www.amazon.com";

	private WebDriver getWebDriver() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("usre.dir") + System.getProperty("file.separator") + "chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		WebDriver googleDriver = new ChromeDriver(options);

		googleDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		googleDriver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

		Timer.waitSeconds(3);
		return googleDriver;
	}

	public WebDriver registerAmazonAccount(Account acc) {

		AccRandom accRand=new AccRandom();
		acc = new Account(accRand.firstName1(), accRand.getEmail(), accRand.getPassword());
		WebDriver driver = getWebDriver();
		Timer.waitSeconds(3);
		driver.get(BASE_URL);
		Timer.waitSeconds(5);

		WebElement registerBlock = driver.findElement(By.id("nav-flyout-ya-newCust"));
		WebElement registerLinkElement = registerBlock.findElement(By.tagName("a"));

		String registerLink = registerLinkElement.getAttribute("href");

		driver.get(registerLink);
		Timer.waitSeconds(25);

		WebElement nameElement = driver.findElement(By.id("ap_customer_name"));
		WebElement emailElement = driver.findElement(By.id("ap_email"));
		WebElement passwordElement = driver.findElement(By.id("ap_password"));
		WebElement checkPasswordElement = driver.findElement(By.id("ap_password_check"));
		WebElement submitElement = driver.findElement(By.id("continue"));

		nameElement.sendKeys(acc.getName());
		emailElement.sendKeys(acc.getEmail());
		passwordElement.sendKeys(acc.getPassword());
		checkPasswordElement.sendKeys(acc.getPassword());

		submitElement.submit();

		String pageLink = driver.getCurrentUrl();
		Timer.waitSeconds(6);
		driver.get(pageLink);

		Timer.waitSeconds(20);

		return driver;
	}

	public WebDriver addItemToCart(WebDriver driver, String itemAsin) {

		WebElement searchInput = driver.findElement(By.id("twotabsearchtextbox"));
		searchInput.sendKeys(itemAsin);
		Timer.waitSeconds(15);

		WebElement searchBox = driver.findElement(By.id("nav-search"));
		List<WebElement> inputElements = searchBox.findElements(By.tagName("input"));
		for (WebElement inputElement : inputElements) {
			if (inputElement.getAttribute("type").equals("submit")) {
				inputElement.submit();
				break;
			}
		}
		Timer.waitSeconds(5);
		String currentUrl = driver.getCurrentUrl();
		driver.get(currentUrl);

		Timer.waitSeconds(5);

		WebElement productsBlock = driver.findElement(By.id("s-results-list-atf"));
		List<WebElement> productList = productsBlock.findElements(By.tagName("li"));

		String productLink = "";

		for (WebElement productElement : productList) {

			if (productElement.getAttribute("data-asin") == null) {
				continue;
			}
			if (productElement.getAttribute("data-asin").equals(itemAsin)) {
				List<WebElement> productLinkElements = productElement.findElements(By.tagName("a"));
				for (WebElement aElement : productLinkElements) {
					if (aElement.getAttribute("class").contains("s-access-detail-page")) {
						productLink = aElement.getAttribute("href");
						if (!productLink.contains(BASE_URL)) {
							productLink = BASE_URL + productLink;
						}

						break;

					}
				}
			}
		}

		if (productLink.isEmpty()) {
			return driver;

		}
		driver.get(productLink);
		Timer.waitSeconds(5);

		WebElement wlBtn = driver.findElement(By.id("add-to-cart-button"));
		wlBtn.click();
		Timer.waitSeconds(15);

		//////////////////////////driver.quit();
		return driver;
	}
}