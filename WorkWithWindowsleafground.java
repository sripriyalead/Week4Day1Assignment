package Week4Day1Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkWithWindowsleafground {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.manage().window().maximize();
		
		//Click button to open home page in New Window		
		driver.findElement(By.xpath("//button[@id='home']")).click();
		
		//Find the number of opened windows//Close all except this window
		driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
		Set<String> openedwindows = driver.getWindowHandles();
		List<String> openwindowlist = new ArrayList<String>(openedwindows);
		System.out.println("Number of opened windows is " +openedwindows.size());
		for(int i=0; i<openwindowlist.size();i++)
		{
			driver.switchTo().window(openwindowlist.get(i));
			String title=driver.getTitle();
			System.out.println(title);
			if(driver.getTitle().contains("Buttons"))
			{
				System.out.println("Buttons window is open");
			}else
			{driver.close();
			}
		}
		}		
	}


