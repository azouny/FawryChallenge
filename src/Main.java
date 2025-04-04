import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Product tv = new Product.Builder("TV", 2000, 3)
                .build();

        Product cheese = new Product.Builder("Cheese", 200, 50)
                .isExpirable(true)
                .expirationDate(LocalDate.of(2026, 4, 1))
                .build();

        
    }
}