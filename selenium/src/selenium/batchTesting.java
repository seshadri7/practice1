package selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class batchTesting {
	public static void main(String[] args) {
	
		
		System.setProperty("webdriver.gecko.driver", "E:\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://newtours.demoaut.com/");
		try{
			System.out.println(driver.findElement(By.id("seshadri")).isDisplayed());
		}
		catch(Exception e){
			
			System.out.println(e);
			System.out.println("element not found");
		}
		
		 WebElement element = driver.findElement(By.linkText("REGISTER"));
		 System.out.println(element.isSelected());
		 element.click();
		 //Drop down
		 
		Select dropdown = new Select (driver.findElement(By.name("country")));
		//dropdown.selectByVisibleText("INDIA");
		// dropdown.selectByIndex(1);
		 List<WebElement> e = dropdown.getOptions();
		 int b= e.size();
		 System.out.println(b);
		
		
		 
		 /*Actions action = new Actions(driver);
		 
	        action.moveToElement(element).build().perform();
	 
	        driver.findElement(By.linkText("iPads")).click();*/
		driver.quit();
	
	}

}
