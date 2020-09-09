package gitTest_package;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Set;
import java.util.List;
import java.net.MalformedURLException;
import java.net.URL;
import java.lang.*;
import java.lang.annotation.*;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.pagefactory.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.runners.Parameterized.Parameters;
import potest.pageobjects.*;

public class Case1_signUp {
	
	public static WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		//chrome dirver(chromedriver.exe)���� ������ ã�� ��ġ�� �ý��ۿ� �˷��� 
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tom\\browser driver\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		//ȸ������ ȭ�� 
		driver.get("https://ohou.se/normal_users/new");
		// ������ â ũ�� �ִ�ȭ
		//driver.manage().window().maximize();
	}
	
	@Test
	public void test() {	
		
		//SignUpPage �ν��Ͻ��� ����. 
		SignUpPage signUpPage = new SignUpPage(driver);
		
		//email, password, confirmPassword, nickname�� �Է�
		signUpPage.email.sendKeys("qa12345");
		signUpPage.password.sendKeys("qwer12!@");
		signUpPage.confirmPassrod.sendKeys("qwer12!@");
		signUpPage.nickname.sendKeys("qa123qa");
		
		//mail �ּҸ� naver�� ����
		//xpath�� ��Ӵٿ��� select Ÿ������ �����´�. 
		Select make = new Select(driver.findElement
				(By.xpath("/html/body/div[1]/section/div/form/div[1]/div[2]/div/span[3]/select")));
				
		// ��Ӵٿ��� ���� ���ø� �������� �˻� 
		AssertJUnit.assertFalse(make.isMultiple());
				
		// ��Ӵٿ� �޴��� 9���� �ɼ����� �����Ǿ� �ִ��� �˻� 
		// ������ �ּ��� ~ ���� �Է� 
		assertEquals(10, make.getOptions().size());
				
		//��Ӵٿ��� ����� ��. �� �ɼǵ��� ������ 
		List<String> exp_options = Arrays.asList
				(new String[] {"�������ּ���","naver.com","hanmail.net","daum.net","gmail.com","nate.com",
								"hotmail.com","outlook.com","icloud.com","�����Է�"});
				
		//���� ������ ��. �� ������ ���� �ɼǰ� 
		List<String> act_options = new ArrayList<String>();
		//getOption() �޼ҵ�� �ɼ� ���� �����´�. 
		for(WebElement option : make.getOptions())
			act_options.add(option.getText());
			
		//��밪�� ������� �� 
		AssertJUnit.assertArrayEquals(exp_options.toArray(),act_options.toArray());
				
		/*���� - Ư�� �ɼ��� �ִ��� Ȯ���ϴ� ���
		assertTrue(act_options.contains("naver.com")); */
				
		//�ؽ�Ʈ�� ��Ӵٿ� �ɼ��� ���� "naver.com"
		make.selectByVisibleText("naver.com");
		//naver.com�� ���õǾ����� �˻� 
		assertEquals("naver.com", make.getFirstSelectedOption().getText());
				
		//value �Ӽ������� ��Ӵٿ� �ɼ��� ���� "hanmail.net"
		//make.selectByValue("hanmail.net");
		//hanmail.net�� ���õǾ����� �˻�
		//assertEquals("hanmail.net",make.getFirstSelectedOption().getText());
				

		
		//��ü ���� üũ �ڽ� ã�Ƽ� allCheck�� ���� 
		WebElement allCheck = driver.findElement(By.xpath("/html/body/div[1]/section/div/form/div[5]/div[2]/div/div[1]/div/label/span[1]"));
		
		// üũ�ڽ� ���� �� Ȱ��ȭ ���� Ȯ�� �� üũ�ڽ� Ŭ�� 
		if (allCheck.isEnabled()) {
				allCheck.click();
		} else {
				Assert.fail("��ü ���ǰ� disable �Ǿ� ����");
			}
		
		// ȸ�� �����ϱ� ��ư ã�Ƽ� signUpButton���� ���� 
		WebElement signUpButton = driver.findElement(By.className("user-sign-up__submit"));
		
		// ȸ�� �����ϱ� ��ư�� ���������� Ȯ�� �� submit  
		if (signUpButton.isDisplayed()) {
			signUpButton.submit();
		} else {
				Assert.fail("ȸ�������ϱ� ��ư�� �Ⱥ������� ����");
			}
		
	try {
	Thread.sleep(7000);
	} catch(InterruptedException e) {
		e.printStackTrace();
	}
	
    //�׽�Ʈ ���� Ȯ��. 
    System.out.println("����");
    
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
			}
}