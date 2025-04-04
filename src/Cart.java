import java.util.ArrayList;

public class Cart {

    double orderSubtotal = 0;
    double shippingFees = 0;
    double totalFees = 0;
    ArrayList<Order> orderList;

    public Cart() {
        orderList = new ArrayList<>();
    }

    private boolean checkProductExist(Order order) {
        for (Order o : orderList) {
            if (o.getProductName().equals(order.getProductName())) {
                return true;
            }
        }

        return false;
    }

    public void addOrder(Order order) {

        boolean productExistInCart = checkProductExist(order);

        // Update quantity and Remove from orderList
        if (productExistInCart) {
            for (Order o : orderList) {
                if (o.getProductName().equals(order.getProductName())) {
                    o.updateQuantity(order);
                    order = o;
                    break;
                }
            }

            orderList.remove(order);
        }

        boolean isValidOrder = order.isValid();

        if (isValidOrder) {
            orderList.add(order);
        } else {
            throw new InvalidOrderException("Order is invalid either product is expired or quantity is more than storage");
        }
    }

    private double calculateOrderSubtotal() {
        double subtotal = 0;
        for (Order o : orderList) {
            subtotal += o.getProductPrice() * o.getQuantity();
        }

        return subtotal;
    }

    private double calculateShippingFees() {
        double shippingFees = 0;
        for (Order o : orderList) {
            if (o.getProduct().isShippable) {
                double totalWeight = o.getProduct().getWeight() * o.getQuantity();

                shippingFees += (totalWeight / 300);
            }
        }

        return shippingFees;
    }

    public void finalizeCart() {

        // Check cart has orders
        if (orderList.isEmpty()) {
            throw new InvalidCartException("Cart is empty");
        }

        // Check all products are not expired and in stock
        for (Order order : orderList) {
            boolean isValidOrder = order.isValid();

            if (!isValidOrder) {
                throw new InvalidOrderException("Product " + order.getProductName() + " is either expired or out of stock");
            }
        }

        //Calculate order subtotal
        this.orderSubtotal = calculateOrderSubtotal();

        //Calculate shipping fees
        this.shippingFees = calculateShippingFees();

        //Total amount
        this.totalFees = this.orderSubtotal + this.shippingFees;
    }

    public double getOrderSubtotal() {
        return orderSubtotal;
    }

    public double getShippingFees() {
        return shippingFees;
    }

    public double getTotalFees() {
        return totalFees;
    }
}
