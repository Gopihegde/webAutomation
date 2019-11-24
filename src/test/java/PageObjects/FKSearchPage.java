package PageObjects;

import Framework.ClassUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FKSearchPage {

    private WebDriver driver;

    @FindBys({@FindBy(xpath = "//div[@class='_3wU53n']")
    })
    public List<WebElement> searchResults;
    @FindBy(xpath = "/html/body/div[1]/div/div[1]/div[1]/div[2]/div[2]/form/div/div/input")
    public WebElement searchField;
    @FindBy(xpath = "/html/body/div[1]/div/div[1]/div[1]/div[2]/div[2]/form/div/button")
    public WebElement searchEnter;
    @FindBys( {

            @FindBy( xpath =  "//div[@class='_3wU53n']")
    })
    public List<WebElement> searchResultsForMobile;
    By price = By.xpath( "//div[@class='_1vC4OE _2rQ-NK']" );

    public FKSearchPage( WebDriver driver ){

        this.driver = driver;
        PageFactory.initElements( driver , this );
    }


    public String searchForItemAndGetPrice(  String item ){

        searchField.sendKeys(  item );
        searchEnter.click();
        WebElement searched = searchResults.stream().filter( webElement ->
        {
            String result = webElement.getText();
            return result.contains( "Black" ) && result.contains( "32" );
        }).findFirst().get();
        String price = searched.findElement(  this.price ).getText();
        System.out.println( "Phone price in Flipakrt is"  + "  " + price  );
        return price;

    }

}
