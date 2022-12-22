package jupitertoys.tests;

import core.BaseTest;
import jupitertoys.pages.CartPage;
import jupitertoys.pages.ShopPage;
import org.testng.annotations.Test;
import jupitertoys.pages.ContactPage;
import jupitertoys.pages.HomePage;

public class JupiterToysTest extends BaseTest {

    @Test
    public void testCase1() {
        HomePage homePage = new HomePage(getDriver());
        ContactPage contactPage = new ContactPage(getDriver());
        homePage.goToHomePage();
        homePage.clickContact();
        contactPage.clickSubmitBtn();
        contactPage.verifyContactPageErrors();
        contactPage.fillOutPage();
        contactPage.verifyContactPageNoErrors();
    }

    @Test (priority = 1, invocationCount = 5)
    public void testCase2(){
        HomePage homePage = new HomePage(getDriver());
        ContactPage contactPage = new ContactPage(getDriver());
        homePage.goToHomePage();
        homePage.clickContact();
        contactPage.fillOutPage();
        contactPage.clickSubmitBtn();
        contactPage.verifySuccessMessage();
    }

    @Test (priority = 3)
    public void testCase3(){
        HomePage homePage = new HomePage(getDriver());
        ShopPage shopPage = new ShopPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        homePage.goToHomePage();
        homePage.clickStartShopBtn();
        shopPage.addToCart("Stuffed Frog",2);
        shopPage.addToCart("Fluffy Bunny",5);
        shopPage.addToCart("Valentine Bear",3);
        homePage.viewCart();
        cartPage.setProductList(shopPage.getProductList());
        cartPage.verifyItemPrice();
        cartPage.verifySubtotalPerItem();
        cartPage.verifyTotalPrice();
    }
}
