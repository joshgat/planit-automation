package jupitertoys.pages;

import core.BasePage;
import jupitertoys.objects.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.ArrayList;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }
    protected ArrayList<Product> productList = new ArrayList<>();

    public void verifyItemName(){
        for(Product product: productList){
            String itemName = driver.findElement(By.xpath("//tr[@class='cart-item ng-scope']//td[contains(text(),'"+product.getName()+"')]")).getText().trim();
            Assert.assertEquals(itemName,product.getName());
        }
        System.out.println("Item Names are visible and correct in the Cart Page");
    }

    public void verifyQuantity(){
        for(Product product: productList){
            String tempQty = driver.findElement(By.xpath("//tr[@class='cart-item ng-scope']//td[contains(text(),'"+product.getName()+"')]/following-sibling::td//input")).getAttribute("value");
            int quantity = Integer.parseInt(tempQty);
            Assert.assertEquals(quantity,product.getQuantity());
        }
        System.out.println("Item Quantities are visible and correct in the Cart Page");
    }

    public void verifyItemPrice(){
        double itemPrice;
        for(Product product: productList){
            String originalPrice = driver.findElement(By.xpath("//tr[@class='cart-item ng-scope']//td[contains(text(),'"+product.getName()+"')]/following-sibling::td[1]")).getText();
            String tempPrice = originalPrice.replace("$", "");
            itemPrice = Double.parseDouble(tempPrice);
            Assert.assertEquals(itemPrice,product.getPrice());
        }
        System.out.println("Item Prices are visible and correct in the Cart Page");
    }

    public void verifySubtotalPerItem(){
        double subtotal;
        for(Product product: productList){
            String originalSubtotal = driver.findElement(By.xpath("//tr[@class='cart-item ng-scope']//td[contains(text(),'"+product.getName()+"')]/following-sibling::td[3]")).getText();
            String tempSubtotal= originalSubtotal.replace("$", "");
            subtotal = Double.parseDouble(tempSubtotal);
            Assert.assertEquals(subtotal,product.getSubtotal());
        }
        System.out.println("Item Subtotals are visible and correct in the Cart Page");
    }

    public void verifyTotalPrice(){
        double total;
        double productListTotal = 0;
        String originalTotal = driver.findElement(By.xpath("//strong[@class = 'total ng-binding']")).getText();
        String tempTotal = originalTotal.replace("Total: ", "");
        total = Double.parseDouble(tempTotal);
        for(Product product: productList){
            productListTotal = productListTotal + product.getSubtotal();
        }
        Assert.assertEquals(total,productListTotal);
        System.out.println("Total Price is visible and correct in the Cart Page");
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }
}
