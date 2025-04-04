public class Customer {

    Cart cart;
    double balance;

    public Customer(double balance) {
        this.balance = balance;
        this.cart = new Cart();
    }

    public void checkOut() {
        cart.finalizeCart();

        // Check fees and customer balance
        if (cart.getTotalFees() > balance) {
            throw new InsufficientBalanceException("Balance is insufficient");
        }

        System.out.println("Subtotal: " + cart.getOrderSubtotal());

        System.out.println("Shipping fees " + cart.getShippingFees());

        System.out.println("Total fees: " + cart.getTotalFees());

        //update balance
        balance -= cart.getTotalFees();
        System.out.println("Your current balance is: " + balance);
    }

    public double getBalance() {
        return balance;
    }

    public Cart getCart() {
        return cart;
    }
}
