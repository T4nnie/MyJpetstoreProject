package org.eql.MyJpetstoreProject;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	WebDriver driver;
	Logger LOGGER = LoggerFactory.getLogger(AppTest.class);
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void JpetstoreTest() {
		
		LOGGER.info("********** ETAPE 1 : Connexion à l'url de départ de JpetStore **********");
		driver.get("http://localhost:8080/jpetstore");
		LOGGER.info("*********** ETAPE 2 accèder à la boutique JpetSore *********************");
		WebElement lienEnterTheStore = driver.findElement(By.xpath("//a[@href='actions/Catalog.action']"));
		lienEnterTheStore.click();
        LOGGER.info("********** ETAPE 3 : Cliquer sur le lien 'SignOn' **********");
        driver.findElement(By.xpath("//a[contains(@href,'signonForm')]")).click();
        
        LOGGER.info("********** ETAPE 4 : Supprimer le contenu du champ login et indiquer mon login **********");
        WebElement champLogin = driver.findElement(By.xpath("//input[@name='username']"));
        champLogin.clear();
        champLogin.sendKeys("ACID");
        
        LOGGER.info("********** ETAPE 5 : Supprimer le contenu du champ password et indiquer mon password **********");
        WebElement champPassword = driver.findElement(By.xpath("//input[@name='password']"));
        champPassword.clear();
        champPassword.sendKeys("ACID");
        
        LOGGER.info("********** ETAPE 6 : Cliquer sur le bouton 'Login' **********");
        driver.findElement(By.xpath("//input[@name='signon']")).click();
        
        LOGGER.info("********** ETAPE 7 : Vérifier que l'on est connecté avec le bon utilisateur ***********");
        String nomAccueilRecupere = driver.findElement(By.xpath("//div[@id='WelcomeContent']")).getText();
        String nomAccueilAttendu = "Welcome ABC!";
        assertEquals("Le nom récupéré '" + nomAccueilRecupere + "' ne correspond pas au nom accueil attendu '"
                + nomAccueilAttendu + "'",nomAccueilAttendu,nomAccueilRecupere);
        
        //Va chercher 1 serpent
        
        driver.findElement(By.xpath("//img[@src=\"../images/sm_reptiles.gif\"]")).click();
        driver.findElement(By.xpath("//a[contains(@href,'RP-SN-01')]")).click();
        driver.findElement(By.xpath("//a[@class='Button'][contains(@href,'EST-11')]")).click();
        WebElement champNSerpent = driver.findElement(By.xpath("//input[@name='EST-11']"));
        champNSerpent.clear();
        champNSerpent.sendKeys("1");
        LOGGER.info("*********  IWH ****************");
        driver.findElement(By.xpath("//input[@name='updateCartQuantities']")).click();
        LOGGER.info("*********  IWH2 ****************");
        driver.findElement(By.xpath("//a[@class='Button'][contains(@href,'newOrderForm')]")).click();
        LOGGER.info("*********  j'ai commandé 1 serpent ****************");
        //Page d'adresse
        driver.findElement(By.xpath("//input[@name='newOrder']")).click();
        //Oage de confirmation
        driver.findElement(By.xpath("//a[@class='Button']")).click();
        
        
        //retour à la page principale
        driver.findElement(By.xpath("//img[@src='../images/logo-topbar.gif']")).click();
        LOGGER.info("**_*_*_* FINI *_*_*_**");
        
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
}
