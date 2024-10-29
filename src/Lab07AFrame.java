import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Lab07AFrame extends JFrame
{
    JPanel mainPnl;

    JPanel controlPnl;
    JButton displayBtn;
    JButton quitBtn;

    JPanel lineItemPnl;
    JLabel productNameLbl;
    JLabel unitPriceLbl;
    JLabel quantityLbl;
    JTextField productNameTF;
    JTextField unitPriceTF;
    JTextField quantityTF;
    JButton addBtn;

    JPanel invoicePnl;
    JLabel titleLbl;
    JLabel customerAddressLbl;
    JTextField titleTF;
    JTextField customerAddressTF;

    JPanel displayPnl;
    JTextArea displayTA;
    JScrollPane scroller;

    JPanel optionsPnl;

    String title;
    String customerAddress;
    Invoice invoice = new Invoice(title, customerAddress);
    int i = 0;


    public Lab07AFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createOptionsPanel();
        mainPnl.add(optionsPnl, BorderLayout.NORTH);

        CreateDisplayPanel();
        mainPnl.add(displayPnl, BorderLayout.CENTER);

        CreateControlPanel();
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(mainPnl);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        setSize(screenWidth * 1 / 4, screenHeight * 5 / 8);
        setLocation(screenWidth / 2, screenHeight / 8);
        setTitle("Invoice Creation Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void CreateControlPanel()
    {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1,2));

        displayBtn = new JButton("Display Invoice");
        displayBtn.setFont(new Font("Serif", Font.BOLD, 24));
        displayBtn.addActionListener((ActionEvent ae) ->
        {
            displayTA.setText("");
            displayTA.append(invoice.getTitle() + "\n");
            displayTA.append(invoice.getCustomerAddress() + "\n");
            for (LineItem item : invoice.getLineItems())
            {
                displayTA.append("Item: "+ item.getProduct().getProductName() + " " + "Quantity: " + item.getQuantity() + " " + "Unit Price: " + item.getProduct().getUnitPrice() + " " + "Total: " + item.getTotal() + "\n");
            }
            displayTA.append("Invoice total: " + String.valueOf(invoice.getTotalAmountDue()));

        });

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Serif", Font.BOLD, 24));
        quitBtn.addActionListener((ActionEvent ae) ->
        {
            int selectedOption = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        });

        controlPnl.add(displayBtn);
        controlPnl.add(quitBtn);

    }


    private void CreateDisplayPanel()
    {
        displayPnl = new JPanel();
        displayPnl.setLayout(new GridLayout(1, 1));
        displayPnl.setBorder(new TitledBorder(new EtchedBorder(), "Invoice"));
        displayTA = new JTextArea(10,15);
        displayTA.setEditable(false);
        displayTA.setFont(new Font("Serif", Font.PLAIN, 22));
        scroller = new JScrollPane(displayTA);
        displayPnl.add(scroller);
    }

    private void createOptionsPanel()
    {
        optionsPnl = new JPanel();
        optionsPnl.setLayout(new GridLayout(1,2));

        CreateInvoicePanel();
        optionsPnl.add(invoicePnl);

        CreateLineItemPanel();
        optionsPnl.add(lineItemPnl);
    }

    private void CreateInvoicePanel()
    {
        invoicePnl = new JPanel();
        invoicePnl.setLayout(new GridLayout(2,2));

        titleLbl = new JLabel("Customer Title");
        titleTF = new JTextField();
        titleTF.setEditable(true);

        customerAddressLbl = new JLabel("Customer Address");
        customerAddressTF = new JTextField();
        customerAddressTF.setEditable(true);

        invoicePnl.add(titleLbl);
        invoicePnl.add(titleTF);
        invoicePnl.add(customerAddressLbl);
        invoicePnl.add(customerAddressTF);
    }

    private void CreateLineItemPanel()
    {
        lineItemPnl = new JPanel();
        lineItemPnl.setLayout(new GridLayout(4,2));

        productNameLbl = new JLabel("Product Name");
        productNameTF = new JTextField();
        productNameTF.setEditable(true);

        unitPriceLbl = new JLabel("Unit Price");
        unitPriceTF = new JTextField();
        unitPriceTF.setEditable(true);

        quantityLbl = new JLabel("Quantity");
        quantityTF = new JTextField();
        quantityTF.setEditable(true);

        addBtn = new JButton("Add Line Item");
        addBtn.addActionListener((ActionEvent ae) ->
        {
            invoice.setTitle(titleTF.getText());
            invoice.setCustomerAddress(customerAddressTF.getText());
            addLineItems();

        });

        lineItemPnl.add(productNameLbl);
        lineItemPnl.add(productNameTF);
        lineItemPnl.add(unitPriceLbl);
        lineItemPnl.add(unitPriceTF);
        lineItemPnl.add(quantityLbl);
        lineItemPnl.add(quantityTF);
        lineItemPnl.add(addBtn);
    }

    private void addLineItems()
    {
        String productName = productNameTF.getText();
        double unitPrice = Double.parseDouble(unitPriceTF.getText());
        int quantity = Integer.parseInt(quantityTF.getText());
        Product product = new Product(productName, unitPrice);
        LineItem item = new LineItem(product, quantity);
        invoice.addLineItem(item);
        productNameTF.setText("");
        unitPriceTF.setText("");
        quantityTF.setText("");
    }
}
