public class Order {
    Product product;
    int quantity;

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public boolean isValid() {
        boolean isValidQuantity = quantity <= product.quantity;

        boolean productNotExpired = !product.isExpired();

        return isValidQuantity && productNotExpired;
    }

    public Product getProduct() {
        return this.product;
    }

    public String getProductName() {
        return this.product.getName();
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void updateQuantity(Order order) {
        this.quantity += order.quantity;
    }

    public double getProductPrice() {
        return this.product.getPrice();
    }
}
