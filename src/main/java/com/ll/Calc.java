package com.ll;

public class Calc {
  public static int run(String exp) {

    exp = exp.replaceAll("\\- ", "\\+ \\-");

    String[] bits = exp.split(" \\+ ");
    int sum = 0;

    for(int i = 0; i < bits.length; i++){
      int a = Integer.parseInt(bits[i]);
      sum += a;
    }

    return sum;

  }
}