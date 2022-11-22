import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ua.hillellit.models.Product;
import ua.hillellit.models.ProductTypes;

public class Main {

  public static void main(String[] args) {
    List<Product> products = Arrays.asList(
        new Product(ProductTypes.BOOK, 12.0, true, LocalDate.of(2022, 3, 1)),
        new Product(ProductTypes.COPYBOOK, 15.47, false, LocalDate.of(2022, 5, 12)),
        new Product(ProductTypes.BOOK, 300.0, true, LocalDate.of(2022, 7, 4)),
        new Product(ProductTypes.PEN, 19.54, false, LocalDate.of(2022, 2, 2)),
        new Product(ProductTypes.PENCIL, 180.80, true, LocalDate.of(2022, 6, 16)),
        new Product(ProductTypes.PEN, 16.57, true, LocalDate.of(2021, 3, 28)),
        new Product(ProductTypes.BOOK, 25.75, true, LocalDate.of(2022, 5, 25)));

    List<Product> booksAbove250 = products.stream()
        .filter(p -> (p.getType().equals(ProductTypes.BOOK) && p.getPrice() >= 250))
        .collect(Collectors.toList());
    System.out.println("Books whose price is higher than 250: ");
    System.out.println(booksAbove250);

    List<Product> productListWithDiscount = products.stream()
        .filter(p -> (p.getType().equals(ProductTypes.BOOK) && p.isDiscountPossibility()))
        .peek(p -> p.setPrice(
            p.getPrice() * 0.9))
        .collect(Collectors.toList());
    System.out.println("------------------------------");
    System.out.println("The price of books with a discount: ");
    System.out.println(productListWithDiscount);

    Product productListMinPrice = products.stream()
        .filter(p -> p.getType().equals(ProductTypes.BOOK))
        .min(Product::compare).orElseThrow(IllegalStateException::new);
    System.out.println("------------------------------");
    System.out.println("The cheapest book: ");
    System.out.println(productListMinPrice);

    List<Product> lastThreeAdd = products.stream()
        .sorted(Comparator.comparing(Product::getDate))
        .skip(products.size() - 3).collect(Collectors.toList());
    System.out.println("------------------------------");
    System.out.println("The last three products: ");
    System.out.println(lastThreeAdd);

    Double totalSum = products.stream()
        .filter(p -> (p.getDate().isAfter(LocalDate.of(LocalDate.now().getYear(), 1, 1))) &&
            (p.getType().equals(ProductTypes.BOOK) && p.getPrice() <= 75))
        .map(Product::getPrice)
        .reduce(Double::sum)
        .orElseThrow(IllegalStateException::new);
    System.out.println("------------------------------");
    System.out.println("The sum of books for last year: ");
    System.out.println(totalSum);

    Map<ProductTypes, List<Product>> groups = products.stream()
        .collect(
            Collectors.groupingBy(Product::getType));
    System.out.println("------------------------------");
    System.out.println("The grouping by type: ");
    System.out.println(groups);
  }
}