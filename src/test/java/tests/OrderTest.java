package tests;

import Framework.ClassUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.util.List;

public class OrderTest extends BaseTest {



    @Test( description = "Automation1" , priority =  1, dataProvider = "mobileName")
    public void flipkartOrder( String phone , int count){

        driver.get(  "https://www.flipkart.com/" );
        homePage.login( false );
        homePage.mobileSelector( phone );
        Assert.assertTrue(  listingPage.topResult.isDisplayed() );
        listingPage.clickOnTopObject();
       // Assert.assertTrue( cartPage.addToCart.isDisplayed() );
        cartPage.cartUpdate( count );


    }


 //  @Test( description = "Automation2" , priority =  2, dataProvider = "mobiletoSearch")
    public void comparePrice(String item){
        driver.get(  "https://www.flipkart.com/" );
        homePage.login( false );
        double fkPrice = Integer.parseInt(fkSearchPage.searchForItemAndGetPrice( item  ).substring( 1 ).replace( "," , "" ));
        System.out.println( fkPrice );
       // ((JavascriptExecutor) driver).executeScript( "window.open('"+"https://www.amazon.in/"+"','_blank');" );
        driver.get("https://www.amazon.in/"  );
        double amazonPrice = Integer.parseInt(searchPage.searchAndGetPrice( item ).substring(1 ).replace( ",","" ));
        if( fkPrice > amazonPrice ){

            System.out.println( "flipKart price is higher" + "  " + fkPrice );
        } else {

            System.out.println( "Amazon price is higher" + " " + amazonPrice );
        }


    }


    @DataProvider(name = "mobileName")
    public Object[][] getMobileNames(){

       return new Object[][]{

               { "Redmi 8A" , 2 }
       };
    }



    @DataProvider(name = "mobiletoSearch")
    public Object[][] searchMobiles(){

        return new Object[][]{

                { "iPhone 7 32 gb(black)" }
        };
    }

}
