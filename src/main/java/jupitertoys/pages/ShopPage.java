package jupitertoys.pages;

import core.BasePage;
import jupitertoys.objects.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;

public class ShopPage extends BasePage {
    public ShopPage(WebDriver driver) {
        super(driver);
    }
    protected final ArrayList<Product> productList = new ArrayList<>();
    protected Product product;

    public void addToCart(String item, int quantity) {
        double itemPrice = 0;
        double subTotalPrice = 0;
        product = new Product();
        for (int i = 0; i < quantity; i++) {
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[text()='"+item+"']//following-sibling::p//a")));
            driver.findElement(By.xpath("//h4[text()='"+item+"']//following-sibling::p//a")).click();
            String originalPrice = driver.findElement(By.xpath("//h4[contains(text(),'" + item + "')]//following-sibling::p//span")).getText();
            String tempPrice = originalPrice.replace("$", "");
            itemPrice = Double.parseDouble(tempPrice);
            subTotalPrice = subTotalPrice + itemPrice;
        }
        product.setName(item);
        product.setPrice(itemPrice);
        product.setQuantity(quantity);
        product.setSubtotal(subTotalPrice);
        productList.add(product);
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
}
