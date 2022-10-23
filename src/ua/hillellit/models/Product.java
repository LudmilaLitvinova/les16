package ua.hillellit.models;

import java.time.LocalDate;

public class Product {
private ProductTypes type;
private Double price;
private boolean discountPossibility;
private LocalDate date;

  public Product(ProductTypes type, Double price, boolean discountPossibility,LocalDate date) {
    this.type = type;
    this.price = price;
    this.discountPossibility = discountPossibility;
    this.date =date;
  }

  @Override
  public String toString() {
    return "Product{" +
        "type='" + type + '\'' +
        ", price=" + price +
        ", discountPossibility=" + discountPossibility +
        ", date='" + date + '\'' +
        '}';
  }

  public ProductTypes getType() {
    return type;
  }

  public Double getPrice() {
    return price;
  }

  public boolean isDiscountPossibility() {
    return discountPossibility;
  }

  public LocalDate getDate() {
    return date;
  }

  public static int compare (Product p1, Product p2){
    if(p1.getPrice() > p2.getPrice())
      return 1;
    return -1;
  }
}
