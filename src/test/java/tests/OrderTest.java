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

  // @Test
    public void searchFK(){
        driver.get(  "https://www.flipkart.com/" );
        try {
            driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']")).click();
        } catch ( WebDriverException e) { e.printStackTrace(); }

        ClassUtils.mouseHover( homePage.electronics , driver , new WebDriverWait( driver ,20 ) );
        //homePage.electronics.click();
        homePage.mobileList.stream().filter( webElement -> webElement.getText().contains( "Redmi 8A" ) ).findFirst().get().click();
        driver.findElement( By.className(  "_3wU53n" ) ).click();
        //JavascriptExecutor jse= (JavascriptExecutor)driver;

        System.out.println( "searching for add to cart" );
            WebElement mapObject = driver.findElement(By.xpath("//*[name()='svg']/*[name()='path']"));
            Actions actions = new Actions( driver );
            actions.click( mapObject ).build().perform();

      //  WebElement payabale = driver.findElement( By.xpath( "//div[contains(string(), 'Total Payable')]") );
       // System.out.println(  payabale.getText());
        //driver.findElement( By.className( "wNrY5O" ) ).click();
        //System.out.println(  payabale.getText());

      //  ((JavascriptExecutor) driver).executeScript( "window.open('"+"https://www.amazon.in/"+"','_blank');" );

        //driver.findElement( By.xpath( "/html/body/div[1]/header/div/div[1]/div[3]/div/form/div[3]/div[1]/input" ) ).sendKeys( "iphone 7 black" );
        //driver.findElement(By.cssSelector((".nav-search-submit"))).click();
        try {
            Thread.sleep(  1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



  //  @Test
    public void  amazon(){
        driver.get( "https://www.amazon.in/" );
        driver.findElement( By.xpath( "//*[@id=\"twotabsearchtextbox\"]" ) ).sendKeys( "Iphone 7 black" );
        driver.findElement( By.xpath( "//input[@value='Go' ]" ) ).click();
        try {
            Thread.sleep( 5090 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> el = driver.findElements(By.xpath( "//span[@class='a-size-medium a-color-base a-text-normal']" ));
        WebElement element = el.stream().filter( webElement -> webElement.getText().contains( "Black" )    ).findFirst().get();
        String price = element.findElement( By.xpath(  "//span[@class='a-price-whole']") ).getText();
        System.out.println( price );
        try {
            Thread.sleep( 5090 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


   // @Test
    public void FkSearch(){

        driver.get(  "https://www.flipkart.com/" );
        try {
            driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']")).click();
        } catch ( WebDriverException e) { e.printStackTrace(); }

        driver.findElement( By.xpath( "/html/body/div[1]/div/div[1]/div[1]/div[2]/div[2]/form/div/div/input" ) ).sendKeys( "Iphone 7 black" );
        driver.findElement( By.xpath( "/html/body/div[1]/div/div[1]/div[1]/div[2]/div[2]/form/div/button" ) ).click();
        WebElement x = driver.findElements( By.xpath( "//div[@class='_3wU53n']" ) ).stream().filter( webElement -> webElement.getText().contains( "32" ) && webElement.getText().contains( "Black" ) ).findFirst().get();

        String price = x.findElement( By.xpath( "//div[@class='_1vC4OE _2rQ-NK']") ).getText();
        System.out.println( price );

        try {
            Thread.sleep( 5090 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test( description = "Automation1" , priority =  1, dataProvider = "mobileName")
    public void flipkartOrder( String phone , int count){

        driver.get(  "https://www.flipkart.com/" );
        homePage.login( false );
        homePage.mobileSelector( phone );
        Assert.assertTrue(  listingPage.topResult.isDisplayed() );
        listingPage.clickOnTopObject();
        Assert.assertTrue( cartPage.addToCart.isDisplayed() );
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
