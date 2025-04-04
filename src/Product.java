import java.time.LocalDate;

public class Product {
    String name;
    double price;
    int quantity;
    boolean isExpirable;
    LocalDate expirationDate;
    boolean isShippable;
    double weight;

    private Product(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.isExpirable = builder.isExpirable;
        this.expirationDate = builder.expirationDate;
        this.isShippable = builder.isShippable;
        this.weight = builder.weight;
    }

    public boolean isExpired() {
        return this.isExpirable && this.expirationDate.isBefore(LocalDate.now());
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public static class Builder {

        String name;
        double price;
        int quantity;
        boolean isExpirable = false;
        LocalDate expirationDate = null;
        boolean isShippable = false;
        double weight = 0;

        public Builder(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public Builder isExpirable(boolean isExpirable) {
            this.isExpirable = isExpirable;
            return this;
        }

        public Builder expirationDate(LocalDate expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public Builder isShippable(boolean isShippable) {
            this.isShippable = isShippable;
            return this;
        }

        public Builder weight(double weight) {
            this.weight = weight;
            return this;
        }

        public Product build() {
            if (isExpirable && expirationDate == null) {
                throw new IllegalArgumentException("Expirable products must have an expiration date");
            }

            if (isShippable && weight == 0) {
                throw new IllegalArgumentException("Shippable products must have weight");
            }

            return new Product(this);
        }
    }
}
