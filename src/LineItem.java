public class LineItem
{
    private Product product;
    private int quantity;
    private double total;

    public LineItem(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
        this.total = quantity * this.product.getUnitPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }
}
