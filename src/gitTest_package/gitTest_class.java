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
		
		//chrome dirver(chromedriver.exe)실행 파일을 찾을 위치를 시스템에 알려줌 
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tom\\browser driver\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		//회원가입 화면 
		driver.get("https://ohou.se/normal_users/new");
		// 브라우저 창 크기 최대화
		//driver.manage().window().maximize();
	}
	
	@Test
	public void test() {	
		
		//SignUpPage 인스턴스를 생성. 
		SignUpPage signUpPage = new SignUpPage(driver);
		
		//email, password, confirmPassword, nickname을 입력
		signUpPage.email.sendKeys("qa12345");
		signUpPage.password.sendKeys("qwer12!@");
		signUpPage.confirmPassrod.sendKeys("qwer12!@");
		signUpPage.nickname.sendKeys("qa123qa");
		
		//mail 주소를 naver로 선택
		//xpath로 드롭다운을 select 타입으로 가져온다. 
		Select make = new Select(driver.findElement
				(By.xpath("/html/body/div[1]/section/div/form/div[1]/div[2]/div/span[3]/select")));
				
		// 드롭다운이 단일 선택만 가능한지 검사 
		AssertJUnit.assertFalse(make.isMultiple());
				
		// 드롭다운 메뉴가 9개의 옵션으로 구성되어 있는지 검사 
		// 선택해 주세요 ~ 직접 입력 
		assertEquals(10, make.getOptions().size());
				
		//드롭다운의 기대한 값. 즉 옵션들의 실제값 
		List<String> exp_options = Arrays.asList
				(new String[] {"선택해주세요","naver.com","hanmail.net","daum.net","gmail.com","nate.com",
								"hotmail.com","outlook.com","icloud.com","직접입력"});
				
		//실제 수행한 값. 즉 가져온 실제 옵션값 
		List<String> act_options = new ArrayList<String>();
		//getOption() 메소드로 옵션 값을 가져온다. 
		for(WebElement option : make.getOptions())
			act_options.add(option.getText());
			
		//기대값과 결과값의 비교 
		AssertJUnit.assertArrayEquals(exp_options.toArray(),act_options.toArray());
				
		/*참고 - 특정 옵션이 있는지 확인하는 방법
		assertTrue(act_options.contains("naver.com")); */
				
		//텍스트로 드롭다운 옵션을 선택 "naver.com"
		make.selectByVisibleText("naver.com");
		//naver.com이 선택되었는지 검사 
		assertEquals("naver.com", make.getFirstSelectedOption().getText());
				
		//value 속성값으로 드롭다운 옵션을 선택 "hanmail.net"
		//make.selectByValue("hanmail.net");
		//hanmail.net이 선택되었는지 검사
		//assertEquals("hanmail.net",make.getFirstSelectedOption().getText());
				

		
		//전체 동의 체크 박스 찾아서 allCheck로 정의 
		WebElement allCheck = driver.findElement(By.xpath("/html/body/div[1]/section/div/form/div[5]/div[2]/div/div[1]/div/label/span[1]"));
		
		// 체크박스 선택 전 활성화 상태 확인 후 체크박스 클릭 
		if (allCheck.isEnabled()) {
				allCheck.click();
		} else {
				Assert.fail("전체 동의가 disable 되어 있음");
			}
		
		// 회원 가입하기 버튼 찾아서 signUpButton으로 정의 
		WebElement signUpButton = driver.findElement(By.className("user-sign-up__submit"));
		
		// 회원 가입하기 버튼이 보여지는지 확인 후 submit  
		if (signUpButton.isDisplayed()) {
			signUpButton.submit();
		} else {
				Assert.fail("회원가입하기 버튼이 안보여지고 있음");
			}
		
	try {
	Thread.sleep(7000);
	} catch(InterruptedException e) {
		e.printStackTrace();
	}
	
    //테스트 종료 확인. 
    System.out.println("종료");
    
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
			}
}