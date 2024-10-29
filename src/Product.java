public class Product
{
    private String productName;
    private double unitPrice;

    public Product (String productName, double unitPrice)
    {
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}

