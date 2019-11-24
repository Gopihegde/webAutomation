package utils;
import Framework.BrowserDriver;
import PageObjects.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

public abstract class BaseTest {

    protected WebDriver driver;
    protected FKHomePage homePage;
    protected FKCartPage cartPage;
    protected ListingPage listingPage;
    protected AmazonSearchPage searchPage;
    protected FKSearchPage fkSearchPage;

    @Parameters( { "browser"} )
    @BeforeTest( alwaysRun = true)
    public void beforeTest(XmlTest test , String browser){

        System.out.println( browser );
        BrowserDriver browserDriver = new BrowserDriver(  browser);
        this.driver = browserDriver.getDriver();
        homePage = new FKHomePage( driver );
        cartPage = new FKCartPage( driver );
        listingPage = new ListingPage( driver );
        searchPage = new AmazonSearchPage( driver );
        fkSearchPage = new FKSearchPage(driver);
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

}
