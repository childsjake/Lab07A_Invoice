import java.util.ArrayList;

public class Invoice
{
    private String title;
    private String customerAddress;
    private ArrayList<LineItem> lineItems;
    private double totalAmountDue;

    public Invoice(String title, String customerAddress) {
        this.title = title;
        this.customerAddress = customerAddress;
        this.lineItems = new ArrayList<LineItem>();
        this.totalAmountDue = 0;
    }


    public void addLineItem(LineItem item)
    {
        lineItems.add(item);
        totalAmountDue += item.getTotal();

    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCustomerAddress() {
        return this.customerAddress;
    }


    public double getTotalAmountDue() {
        return this.totalAmountDue;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}
