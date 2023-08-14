package com.pluralsight.streamlambda;

import java.util.HashMap;
import java.util.Map;

class JavaInUse {

  public static void main(String[] args) {
    printLists(Integer.valueOf(args[0]));
  }

  public static void printLists(int num) {
    Map<Integer, String> map = new HashMap();
    String initial = "";
    for (int i = 1; i <= num; i++) {

      initial = "[" + initial + "]";
      map.put(i, initial);
    }
    String output = "";
    for (int i = 1; i <= num; i++) {
      String s = map.get(i);
      output = output + s + ",";
    }

    String substring = output.substring(0, output.length() - 1);
    System.out.println("[" + substring + "]");
  }
}
