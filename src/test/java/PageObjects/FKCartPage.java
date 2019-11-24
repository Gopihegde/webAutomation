package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class FKCartPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[name()='svg']/*[name()='path']")
    public WebElement addToCart;
    @FindBy( xpath = "")
    public WebElement incrementCount;
    @FindBy( xpath = "")
    public WebElement totalValue;

    public FKCartPage(WebDriver driver ){

        this.driver = driver;
        PageFactory.initElements( driver,this );
    }


    public void cartUpdate( int count ){

        Actions actions = new Actions( driver );
        actions.moveByOffset( addToCart.getRect().x, addToCart.getRect().y  ).click().build().perform();
        String basePrice = totalValue.getText();
        setIncrementCount( count );
        Assert.assertEquals( totalValue.getText() , checkTotalValue( basePrice , count ) );

    }


    public String checkTotalValue( String price , int count ){
        double basePrice = Integer.parseInt( price );
        double totalCartValue = basePrice * count;

        return String.valueOf(totalCartValue );
    }

    private void setIncrementCount( int count){

        while (count >= 0){
            incrementCount.click();
            count --;
        }
    }
}
