package com.pluralsight.streamlambda;

import com.pluralsight.streamlambda.Employee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeMain {

  public static void main(String[] args) {
    List<Employee> employees = new ArrayList<>();
    employees.add(new Employee("Manivas", 20000));
    employees.add(new Employee("Akhila", 30000));
    OptionalDouble average = employees.stream().mapToInt(value -> value.empSalary).average();
    System.out.println(average);

    String[] names = new String[]{"Manivas", "Akhila"};
    List<String> nameslist = Arrays.asList(names);
    Stream<String> stream = nameslist.stream();
    Stream<String> names1 = Stream.of(names);

    List<List<String>> namesNested = Arrays.asList(
        Arrays.asList("Jeff", "Bezos"),
        Arrays.asList("Bill", "Gates"),
        Arrays.asList("Mark", "Zuckerberg"));

    System.out.println(namesNested);

    List<String> namesFlatStream = namesNested.stream()
        .flatMap(Collection::stream).filter(p -> !p.isEmpty()).filter(p -> p.startsWith("B"))
        .collect(Collectors.toList());

    System.out.println(namesFlatStream);
  }

}
