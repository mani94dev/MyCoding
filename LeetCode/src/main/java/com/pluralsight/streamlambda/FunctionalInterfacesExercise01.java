package com.pluralsight.streamlambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FunctionalInterfacesExercise01 {

  public List<Product> filterProducts(
      List<Product> products, /* TODO: Replace 'Object' with a functional interface */
      Predicate<Product> f) {
    List<Product> result = new ArrayList<>();
    for (Product prod : products) {
      if (f.test(prod)) {
        result.add(prod);
      }
    }
    return result;
  }

}
