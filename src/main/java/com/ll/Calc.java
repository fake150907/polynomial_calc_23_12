package com.ll;

public class Calc {
  public static int run(String exp) {
    int sum = 0;

    if (exp.contains("+")) {
      String[] bits = exp.split(" \\+ ");

      int a = Integer.parseInt(bits[0]);
      int b = Integer.parseInt(bits[1]);
      sum = a + b;
      return sum;

    } else if (exp.contains("-")) {
      String[] bits = exp.split(" - ");

      int a = Integer.parseInt(bits[0]);
      int b = Integer.parseInt(bits[1]);

      sum = a - b;
      return sum;

    }
    return sum;
  }
}
