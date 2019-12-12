package tests;

import Framework.BrowserDriver;
import Framework.ClassUtils;
import PageObjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.util.HashSet;
import java.util.List;

public class YahooNews {

    private WebDriver driver;
    ClassUtils utils;

    @Parameters( { "browser"} )
    @BeforeTest( alwaysRun = true)
    public void beforeTest(XmlTest test , String browser){

        BrowserDriver browserDriver = new BrowserDriver(  browser);
        this.driver = browserDriver.getDriver();
        utils = new ClassUtils( driver );
    }



    @Test()
    public void yahooTest(){

        driver.get( "https://in.news.yahoo.com/" );
        utils.scroll( 100 , driver );
        List<WebElement> newsList = driver.findElements(By.xpath("//li[@class='js-stream-content Pos(r)']//a[@href]"));
        ClassUtils.sleep( 2000 );
        System.out.println( " The first headline is  ---------- " + newsList.get( 1 ).getText(  )  );
        driver.findElement( By.linkText( "National" ) ).click();
        ClassUtils.sleep( 2000 );
        List<WebElement> news = driver.findElements(By.xpath("//li[@class='js-stream-content Pos(r)']//a[@href]"));


        //Ov(h) Pend(44px) Pstart(25px)

    }

    @BeforeSuite
    public void beforeSuite(){

    }




    @BeforeClass
    public void beforeClass(){


    }


    @BeforeMethod
    public void beforeMethod(){


    }


    @AfterMethod
    public void afterMethod(){


    }


    @AfterTest
    public void afterTest(){
        if( driver != null ){

            driver.quit();
        }

    }

    @AfterClass
    public void afterClass(){

    }

    @AfterSuite
    public void afterSuite(){


    }


    private boolean adFilter( String text  )  {

        return  true;
    }

}
