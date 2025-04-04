import java.text.DecimalFormat;

public class Customer {
    String name;
    Cart cart;
    double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }

    public void checkOut() {
        cart.finalizeCart();

        // Check fees and customer balance
        if (cart.getTotalFees() > balance) {
            throw new InsufficientBalanceException("Balance is insufficient");
        }

        System.out.println("Subtotal: " + cart.getFormattedOrderSubtotal());

        System.out.println("Shipping fees " + cart.getFormattedShippingFees());

        System.out.println("Total fees: " + cart.getFormattedTotalFees());

        //update balance
        balance -= cart.getTotalFees();
        System.out.println("Your current balance is: " + getFormattedBalance());
    }

    public double getBalance() {
        return balance;
    }

    public String getFormattedBalance()
    {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(balance);
    }

    public Cart getCart() {
        return cart;
    }
}
