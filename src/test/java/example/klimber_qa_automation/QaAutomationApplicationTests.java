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
import java.util.concurrent.ThreadLocalRandom;


@SpringBootTest

class QaAutomationApplicationTests {

	private WebDriver driver;
	String url = "https://purchase-testing.klimber.com/ar/GroupLife/Index";

	public static void main(String[] args) {
        int longitud = 12;
        String cadena = mailAleatorio(longitud);
        System.out.printf("Cadena aleatoria de %d caracteres: %s\n", longitud, cadena);
    }

    public static String mailAleatorio(int longitud) {
        // El banco de caracteres
        String name = "arlopedro";
		String mail = "@gmail.com";
		String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        // La cadena en donde iremos agregando un carácter aleatorio
        String cadena = "";
        for (int x = 0; x < longitud; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += (name+caracterAleatorio+mail);
        }
        return cadena;
    }

    public static int numeroAleatorioEnRango(int minimo, int maximo) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

		




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
		String pass = "Dynamic1234";
		String street ="pedro goyena";
		String houseNumber = "5";
		String dpto = "c";
		String floor ="3";
		String zipCode = "1024";
		String placeOfBirth = "Buenos Aires";


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
		
		//Seleccion de checkboxes sobre planes de seguros
		
		WebElement checkbox_one = driver.findElement(By.id("chkDisability"));
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
		driver.findElement(By.id("txtEmail")).sendKeys(mailAleatorio(1));

		driver.findElement(By.xpath("//span[@id='select2-Gender-container']")).click();
		driver.findElement(By.xpath("//li[@class='select2-results__option select2-results__option--highlighted' and contains(text(), 'Masculino')]")).click();
		driver.findElement(By.xpath("//span[@id='select2-CivilStatus-container']")).click();
		driver.findElement(By.xpath("//li[@class='select2-results__option' and contains(text(), 'Casada/o')]")).click();
		driver.findElement(By.id("Password")).sendKeys(pass);
		driver.findElement(By.id("Street")).sendKeys(street);
		driver.findElement(By.id("HouseNumber")).sendKeys(houseNumber);
		driver.findElement(By.id("Floor")).sendKeys(floor);
		driver.findElement(By.id("Apartment")).sendKeys(dpto);
		driver.findElement(By.id("zipCode")).sendKeys(zipCode);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); 
		driver.findElement(By.xpath("//button[@id='btnRegister' and contains(text(), 'Siguiente')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); 

		//driver.findElement(By.id("btnRegister")).click();

		driver.findElement(By.xpath("//span[@id='select2-Nationality-container']")).click();
		driver.findElement(By.xpath("//li[@class='select2-results__option select2-results__option--highlighted' and contains(text(), 'Argentina')]")).click();
		driver.findElement(By.id("PlaceOfBirth")).sendKeys(placeOfBirth); 

		driver.findElement(By.id("txtOccupation")).sendKeys(zipCode);
		driver.findElement(By.id("txtSalaryAnual")).sendKeys(zipCode);
		driver.findElement(By.id("txtFullName")).sendKeys(zipCode);
		driver.findElement(By.id("txtNumberId")).sendKeys(zipCode);
		driver.findElement(By.id("txtOccupation")).sendKeys(zipCode);

		WebElement chkExposedPerson = driver.findElement(By.id("chkExposedPerson"));
		JavascriptExecutor jse_hkExposedPerson  = (JavascriptExecutor) driver;
		jse_hkExposedPerson .executeScript("arguments[0].click();", chkExposedPerson );

		
		
		//System.out.println("Test de carga de contenido y automatización de flujo");

		//driver.close();
		
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


