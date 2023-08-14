package com.pluralsight.streamlambda;

public class StringOps {
    String addTwoStrings(String str1, String str2){
      return str1 + str2;
    }

    String addTwoStrings2(String str1, String str2){
      StringBuilder output = new StringBuilder();
      return output.append(str1).append(str2).toString();
    }
}
