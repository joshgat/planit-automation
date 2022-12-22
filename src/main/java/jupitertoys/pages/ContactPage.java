package jupitertoys.pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ContactPage extends BasePage {

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    //Page Elements
    protected final By submitBtn = By.xpath("//a[contains(text(),'Submit')]");
    protected final By forenameTxt = By.xpath("//input[@id='forename']");
    protected final By emailTxt = By.xpath("//input[@id='email']");
    protected final By messageTxt = By.xpath("//textarea[@id='message']");
    protected final By alertError = By.xpath("//div[@class='alert alert-error ng-scope']");
    protected final By forenameError = By.xpath("//span[@id='forename-err']");
    protected final By emailError = By.xpath("//span[@id='email-err']");
    protected final By messageError = By.xpath("//span[@id='message-err']");
    protected final By successMessage = By.xpath("//div[@class='alert alert-success']");

    //Variables
    protected final String expectedAlertError = "We welcome your feedback - but we won't get it unless you complete the form correctly.";
    protected final String expectedForenameError = "Forename is required";
    protected final String expectedEmailError = "Email is required";
    protected final String expectedMessageError = "Message is required";
    protected final String forename = "Planit";
    protected final String expectedSuccessMsg = "Thanks " + forename + ", we appreciate your feedback.";

    //Actions
    public void verifyContactPageErrors(){
        Assert.assertEquals(driver.findElement(alertError).getText(),expectedAlertError);
        Assert.assertEquals(driver.findElement(forenameError).getText(),expectedForenameError);
        Assert.assertEquals(driver.findElement(emailError).getText(),expectedEmailError);
        Assert.assertEquals(driver.findElement(messageError).getText(),expectedMessageError);
        System.out.println("Contact Page Errors are present");
    }

    public void fillOutPage(){
        driver.findElement(forenameTxt).sendKeys(forename);
        driver.findElement(emailTxt).sendKeys("email@email.com");
        driver.findElement(messageTxt).sendKeys("test");
    }

    public void verifyContactPageNoErrors() {
        if (driver.findElements(alertError).size() != 0) {
            Assert.fail("Alert Error is Found");
        }
        if (driver.findElements(forenameError).size() != 0) {
            Assert.fail("Missing Forename Error is Found");
        }
        if (driver.findElements(emailError).size() != 0) {
            Assert.fail("Missing Email Error is Found");
        }
        System.out.println("Contact Page Errors are not present");
    }

    public void verifySuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        Assert.assertEquals(driver.findElement(successMessage).getText(),expectedSuccessMsg);
        System.out.println("Successful Submission message is present");
    }
    public void clickSubmitBtn(){
        driver.findElement(submitBtn).click();
    }
}
