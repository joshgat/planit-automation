package jupitertoys.pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Home Page Elements
    protected final By startShopBtn = By.xpath("//a[contains(text(),'Start Shopping')]");
    protected final By contactNav = By.xpath("//li[@id='nav-contact']");

    //Shop Page Elements
    protected final By cartNav = By.cssSelector("#nav-cart");


    //Home Page Actions
    public void goToHomePage(){
        driver.manage().window().maximize();
        driver.get("https://jupiter.cloud.planittesting.com/#/");
    }

    public void clickContact(){
        driver.findElement(contactNav).click();
    }


    public void clickStartShopBtn(){
        driver.findElement(startShopBtn).click();
    }


    public void viewCart(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(cartNav));
        driver.findElement(cartNav).click();
    }
}
