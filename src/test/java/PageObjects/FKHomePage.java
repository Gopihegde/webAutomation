package PageObjects;

import Framework.ClassUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FKHomePage {

    private WebDriver driver;


    public FKHomePage(WebDriver driver ){

        this.driver = driver;
        PageFactory.initElements( driver , this );
    }

    @FindBy( xpath = "/html/body/div[1]/div/div[2]/div/ul/li[1]/span")
    public WebElement electronics;
    @FindBys( {@FindBy( tagName = "a")})
    public List<WebElement> mobileList;
    @FindBy(xpath = "//button[@class='_2AkmmA _29YdH8']")
    public WebElement closeLoginAlert;

    public void  login( boolean login ){

        if( ! login ){

            closeLoginAlert.click();
        }
    }

    public void mobileSelector( String mobile){
        ClassUtils.mouseHover( electronics , driver , new WebDriverWait( driver ,20 ) );
        WebElement mobileFound = mobileList.stream().filter( webElement -> webElement.getText().contains( mobile ) ).findFirst().get();
        if(  mobileFound!= null && mobileFound.isDisplayed() ){
            mobileFound.click();
        } else {
            throw new IllegalArgumentException( "Please search for other mobile" );
        }
    }


}
