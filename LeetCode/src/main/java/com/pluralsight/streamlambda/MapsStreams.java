package com.pluralsight.streamlambda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapsStreams {

  public static void main(String[] args) {
    Map<String, Integer> map = new HashMap<>();
    map.put("A", 5);
    map.put("B", 3);
    map.put("C", 5);
    map.put("D", 7);

    List<String> keysWithValue5 = map.entrySet()
        .stream()
        .filter(entry -> entry.getValue() == 5)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());

    System.out.println(keysWithValue5); // prints [A, C]
  }

}
