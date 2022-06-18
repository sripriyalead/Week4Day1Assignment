package Week4Day1Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.sdk.metrics.view.View;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.linkText("Merge Contacts")).click();
		//Click on Widget of From Contact
		driver.findElement(By.xpath("//input[@id='partyIdFrom']/following-sibling::a")).click();
		Thread.sleep(2000);
		//Navigating to new window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows =new ArrayList<String> (windowHandles);
		System.out.println("size=" + windows.size());
		driver.switchTo().window(windows.get(1));
		Thread.sleep(3000);
		// Click on First Resulting Contact
		driver.findElement(By.xpath("(//div[@style='overflow: visible;']//table//tr//td[1]/div/a)[1]")).click();
		Thread.sleep(2000);
		//moving back to parent window
		driver.switchTo().window(windows.get(0));
		Thread.sleep(2000);
		//Click on Widget of To Contact 
		driver.findElement(By.xpath("//input[@id='partyIdTo']/following-sibling::a")).click();
		Thread.sleep(2000);
		//Navigating to new window
		Set<String> towindowHandles = driver.getWindowHandles();
		List<String> towindows =new ArrayList<String> (towindowHandles);
		System.out.println("size=" + towindows.size());
		driver.switchTo().window(towindows.get(1));
		//Thread.sleep(2000);
		// Click on Second Resulting Contact
		driver.findElement(By.xpath("//div[@style='overflow: visible;']/div/div[2]/table//tr[1]//td[1]/div/a")).click();
		Thread.sleep(2000);
		//moving back to parent window
		driver.switchTo().window(towindows.get(0));
		Thread.sleep(2000);
		//Click on merge button
		driver.findElement(By.linkText("Merge")).click();
		//Accept the alert
		Alert alert=driver.switchTo().alert();
		alert.accept();
		Thread.sleep(2000);
		//Verify the title of the page
		String title = driver.getTitle();
		if(title.contains("View Contact"))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		driver.close();
	}
	

}
