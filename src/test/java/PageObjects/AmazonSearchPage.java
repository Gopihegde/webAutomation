package PageObjects;

import Framework.ClassUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AmazonSearchPage {

    private WebDriver driver;


    @FindBy(xpath = "//*[@id=\"twotabsearchtextbox\"]")
    public WebElement searchField;
    @FindBy(xpath =  "//input[@value='Go' ]"  )
    public WebElement searchEnter;
    @FindBys({
            @FindBy(xpath = "//span[@class='a-size-medium a-color-base a-text-normal']")
    })
    public List<WebElement> searchResults;
    By price = By.xpath(  "//span[@class='a-price-whole']" );
    public AmazonSearchPage( WebDriver driver ){

        this.driver = driver;
        PageFactory.initElements( driver, this );
    }

    public String searchAndGetPrice( String  item ){
        searchField.sendKeys(  item );
        searchEnter.click();
        WebElement searched = searchResults.stream().filter( webElement ->
        {
            String result = webElement.getText();
            return result.contains( "Black" ) && result.contains( "32" );
        } ).findFirst().get();
        String price = searched.findElement( this.price ).getText();
        System.out.println( "Phone price in amazon is"  + "  " + price  );
        return price;
    }



}
