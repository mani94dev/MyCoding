package com.pluralsight.streamlambda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test {

  @FunctionalInterface
  interface myinterface<T, V> {

    void apply(T val1, V val2);
  }

  static Optional<Product> findProduct(List<Product> products, Predicate<Product> pred){
    for(Product prod : products){
      if(pred.test(prod)){
        return Optional.of(prod);
      }
    }
    return Optional.empty();
  }

  public static void main(String[] args) {
    List<Product> products =
        ExampleData.getProducts();

//    List<Product> newProducts = products.stream().map(
//        product -> new Product(product.getCategory(), product.getName(),
//            product.getPrice().multiply(new BigDecimal(2)))).collect(
//        Collectors.toList());
//    System.out.println(products);
//    System.out.println(newProducts);

//    String name = "Oranges";
//    Optional<Product> prod = findProduct(products, product ->
//        product.getName().equals(name));
//    prod.map(Product::getPrice).
//        ifPresentOrElse(
//            price -> System.out.println(price),
//            () -> System.out.println("No product")
//        );

    sortProductsByName(products);

  }

  public Map<String, List<Employee>> groupByJobTitle(List<Employee> employeeList) {
    Map<String, List<Employee>> resultMap = new HashMap<>();
    for (int i = 0; i < employeeList.size(); i++) {
      Employee employee = employeeList.get(i);
      List<Employee> employeeSubList = resultMap.getOrDefault(employee.getTitle(), new ArrayList<Employee>());
      employeeSubList.add(employee);
      resultMap.put(employee.getTitle(), employeeSubList);
    }
    return resultMap;
  }

  public Map<String, List<Employee>> groupByJobTitleUsingStreams(List<Employee> employeeList) {
    return employeeList.stream().collect(Collectors.groupingBy(Employee::getTitle));
  }

  public double calculateAverage(List<Employee> employeeList) {
    return employeeList.stream()
        .mapToInt(employee -> employee.getSalary())
        .average()
        .getAsDouble();
  }

  public static void sortProductsByName(List<Product> products) {
    // TODO: Use a lambda expression to sort the list of products by name
    System.out.println(products);
    Collections.sort(products,(p1,p2) -> p1.getName().compareTo(p2.getName()));
    System.out.println(products);
  }

  public interface ProductFilter {
    boolean accept(Product product);
  }
  /**
   * Exercise 2: Find products by category by implementing a functional interface using a lambda expression and calling it.
   *
   * @param products The list of products to search.
   * @param category The category of products to search for.
   * @return A new list containing the products which are in the specified category.
   */
  public List<Product> findProductsByCategory(List<Product> products, Category category) {
    // TODO: Implement interface ProductFilter with a lambda expression
    // The lambda expression should return true if the product is in the given category
    ProductFilter filter = product -> product.getCategory().equals(category); // TODO: Replace 'null' by a lambda expression

    List<Product> result = new ArrayList<>();
    for (Product product : products) {
      if(filter.accept(product)){
        result.add(product);
      }
      // TODO: Add products that are accepted by the filter to the 'result' list
    }

    return result;
  }

  public interface ShoppingCartFactory {
    ShoppingCart newShoppingCart();
  }

  /**
   * Exercise 3a: Implement interface ShoppingCartFactory using a method reference.
   *
   * @return A new ShoppingCartFactory.
   */
  public ShoppingCartFactory getShoppingCartFactory() {
    // TODO: Implement interface ShoppingCartFactory by using a method reference
    // Note: Don't implement ShoppingCartFactory with an anonymous class
    return ShoppingCart::new; // Replace 'null' by your solution
  }



  public static class ShoppingCart {
    private final List<Product> products = new ArrayList<>();

    public void add(Product product) {
      this.products.add(product);
    }

    /**
     * Exercise 3b: Calculate the total amount of the items in the shopping cart.
     *
     * @return The total amount of the items in the shopping cart.
     */
    public BigDecimal getTotalAmount() {
      BigDecimal total = BigDecimal.ZERO;

      // TODO: This solution does not work. Can you explain why?
//      products.forEach(product -> total.add(product.getPrice()));

      // TODO: Implement this method in whatever way you like (you don't have to use lambda expressions)
      return products.stream()
          .map(Product::getPrice)
          .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
  }
}
