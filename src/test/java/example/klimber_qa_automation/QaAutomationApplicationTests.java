package example.klimber_qa_automation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;




@SpringBootTest

class QaAutomationApplicationTests {

	private WebDriver driver;
	String url = "https://purchase-testing.klimber.com/ar/GroupLife/Index";

	@BeforeEach
	
	void getup (){

		driver = new ChromeDriver();
		
	}
	
	/* ---- test ejemplo de carga de contenido y ejecución de elementos del DOM,
		   en este primer test se carga información del usuario y se procede a
		   la siguiente pagina ----- */

	@Test 
	void LoadContentFirstPage() {

		String altura = "1.73";
		String peso = "75";
		String date = "10102000";
		String phone_code = "11";
		String phoneNumber ="12345678";
		String province = "CABA";
		String name = "Damian";
		String surname = "Zampini";
		String doc = "12345678";
		String email ="Damianzampini@gmail.com";


		//Carga de url
		
		driver.get(url); 

		//espera de carga de 2 segundos
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); 

		//Selecciones de campos y envio de contenido

		driver.findElement(By.id("BirthdayStep1")).sendKeys(date);
		driver.findElement(By.id("txtPhoneCode")).sendKeys(phone_code);          
		driver.findElement(By.id("txtPhoneNumber")).sendKeys(phoneNumber);

		//Seleccion de dropdown y contenido

		Select drpprovince = new Select(driver.findElement(By.id("province")));
		drpprovince.selectByVisibleText(province);								
		WebElement checkbox_one = driver.findElement(By.id("chkDisability"));

		//Seleccion de checkboxes sobre planes de seguros

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", checkbox_one);
		WebElement checkbox_two = driver.findElement(By.id("chkAccident"));
		JavascriptExecutor jse_two = (JavascriptExecutor) driver;
		jse_two.executeScript("arguments[0].click();", checkbox_two);
		
		//Seleccion de Faqs y despliegue de dropdown
		
		WebElement Faq_one = driver.findElement(By.xpath("//button[@data-target='#product_1']"));
		JavascriptExecutor jsex_one = (JavascriptExecutor) driver;
		jsex_one.executeScript("arguments[0].click();", Faq_one);
		WebElement Faq_two = driver.findElement(By.xpath("//button[@data-target='#product_2']"));
		JavascriptExecutor jsex_two = (JavascriptExecutor) driver;
		jsex_two.executeScript("arguments[0].click();", Faq_two);
		WebElement Faq_three = driver.findElement(By.xpath("//button[@data-target='#product_3']"));
		JavascriptExecutor jsex_three = (JavascriptExecutor) driver;
		jsex_three.executeScript("arguments[0].click();", Faq_three);

		//Selección de botón y click para pasar a la siguiente página

		driver.findElement(By.id("btnSaveStep1")).click();

		//Carga de contenido en la siguiente pagina y cierre de test

		driver.findElement(By.id("txtHeight")).sendKeys(altura);
		driver.findElement(By.id("txtWeight")).sendKeys(peso);
		driver.findElement(By.id("btnSaveStep2")).click();
		driver.findElement(By.id("Name")).sendKeys(name);
		driver.findElement(By.id("Surname")).sendKeys(surname);
		driver.findElement(By.id("ID_Number")).sendKeys(doc);
		driver.findElement(By.id("txtEmail")).sendKeys(email);

		System.out.println("Test de carga de contenido y automatización de flujo");

		driver.close();
		
	}
	
	/* ---- Test ejemplo de assert de contenido de texto, se validan textos de la primer pagina---- */

	@Test 
	
	void assertcontentLoadFirstPage(){

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		driver.findElement(By.id("btnSaveStep1")).click();
		

		WebElement title = driver.findElement(By.xpath("//div[@class='col-sm-offset-1 col-sm-10 title form-group']"));
		JavascriptExecutor jseTitle = (JavascriptExecutor) driver;
		jseTitle.executeScript("arguments[0]", title);
		String actualElementText = title.getText();
		String expectedElementText = "Elegí cuánto querés pagar por tu seguro de vida";
		Assert.assertEquals(actualElementText, expectedElementText, "Actual and expect are the sama");

		WebElement subtitle = driver.findElement(By.xpath("//div[@class='col-sm-offset-1 col-sm-10 title groupLife-subtitle form-group']"));
		JavascriptExecutor jseSubtitle = (JavascriptExecutor) driver;
		jseSubtitle.executeScript("arguments[0]", subtitle);
		String subtitileText = subtitle.getText();
		String expectedSubtitleText = "Sumá tranquilidad hasta en tiempos de incertidumbre";
		Assert.assertEquals(subtitileText, expectedSubtitleText, "Actual and expect are the sama");
		System.out.println("Test de validación de elementos de texto");
		driver.close();

	}

	
}


