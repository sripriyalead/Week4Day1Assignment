package Week4Day1Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameActivity {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		WebElement iframe1 = driver.findElement(By.id("frame1"));
		WebElement iframe2 = driver.findElement(By.id("frame2"));
		//Inputing the text TOPIC in frame 1
		driver.switchTo().frame(iframe1);
		System.out.println("Switched to frame 1");
		driver.findElement(By.xpath("//b[text()='Topic :']/following-sibling::input")).sendKeys("Not a Friendly Topic");
		//Clicking the checkbox in frame3
		WebElement iframe3 = driver.findElement(By.id("frame3"));
		driver.switchTo().frame(iframe3);
		driver.findElement(By.xpath("//input[@id='a']")).click();
		//moving back to default content
		driver.switchTo().defaultContent();
		//dropdown selection in frame 2
		driver.switchTo().frame(iframe2);
		WebElement selectele= driver.findElement(By.id("animals"));
		Select dd =new Select(selectele);
		dd.selectByValue("avatar");
		driver.switchTo().defaultContent();
		driver.close();
		
	}

}
