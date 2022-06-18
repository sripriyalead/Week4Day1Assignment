package Week4Day1Assignment;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		//Store the Parent Window Handle
		String parentWindowHandle = driver.getWindowHandle();
		
		//search as oneplus 9 pro
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro",Keys.ENTER);
		Thread.sleep(3000);
		
		//Get the price of the first product
		//List<WebElement> price = driver.findElements(By.xpath("//span[@class='a-offscreen']"));
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
		String firstmobileprice = price.get(0).getText();
		System.out.println("Price of First Product is "+firstmobileprice);
		
		//Print the number of customer ratings for the first displayed product
		//List<WebElement> Ratings = driver.findElements(By.xpath("//span[@class='a-icon-alt']"));
		List<WebElement> Ratings = driver.findElements(By.xpath("//span[contains(@class,'a-size-base')]"));
		String customerrating = Ratings.get(0).getText();
		System.out.println("Rating of First Product is "+customerrating);
		
		//Click the first text link of the first image
		List<WebElement> Description = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
		String phonedesc = Description.get(0).getText();
		System.out.println("Description of First Product is "+phonedesc);
		Description.get(0).click();
		Thread.sleep(5000);
		
		//Take a screen shot of the product displayed
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowlist =new ArrayList<String> (windowHandles);
		driver.switchTo().window(windowlist.get(1));
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./amazonScreenshot.png");
		FileUtils.copyFile(src, dest);
		System.out.println("Screen shot saved");
		
		//Click 'Add to Cart' button
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		Thread.sleep(3000);
		
		//Get the cart subtotal and verify if it is correct.
		String subtotal = driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
		System.out.println("Cart subtotal is " +subtotal);
		if(firstmobileprice.equals(subtotal))
		{
			System.out.println("Subtotal is correct");
		}else
		{
			System.out.println("Subtotal is wrong");
		}
		
		driver.close();
		
		//Switch to Parent Window
		driver.switchTo().window(parentWindowHandle);
		
		
}

}
