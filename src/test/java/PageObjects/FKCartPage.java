package PageObjects;

import Framework.ClassUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class FKCartPage {

    private WebDriver driver;

    @FindBy(xpath = "//ul/li[1]/button")
    public WebElement addToCart;
    @FindBy( xpath = "//div//button[2]")
    public WebElement incrementCount;
    @FindBy( xpath = "//div[3]/div/div/../span")
    public WebElement totalValue;

    public FKCartPage(WebDriver driver ){

        this.driver = driver;
        PageFactory.initElements( driver,this );
    }


    public void cartUpdate( int count ){

        List<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        addToCart.click();
        String basePrice = totalValue.getText().substring(1).replace( "," ,"" );
        setIncrementCount( count );
        try {
            Thread.sleep( 4000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals( totalValue.getText().substring(1).replace(",","") , checkTotalValue( basePrice , count ) );

    }


    public String checkTotalValue( String price , int count ){
        int basePrice = Integer.parseInt( price );
        int totalCartValue = basePrice * count;
        System.out.println( "total cart value is" + totalCartValue );
        return String.valueOf(totalCartValue );
    }

    private void setIncrementCount( int count){
        ClassUtils.WaitForElement(  incrementCount , driver, 20 );
        while (count > 1){
            System.out.println( "Incrementing count" );
            try {
                Thread.sleep( 2000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            incrementCount.click();
            count --;
        }
    }
}
