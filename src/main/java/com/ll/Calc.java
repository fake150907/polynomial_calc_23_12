package com.ll;

public class Calc {
  public static int run(String exp) {
    // TDD의 효과
    // 문제가 되는 이 녀석을 직접 여기다가 대입해서 눈으로든 머리로든 손으로든 돌려보면 어디서 잘못된게 보인다.
    boolean needToMultiply = exp.contains(" * ");
    boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
    boolean needToCompound = needToPlus && needToMultiply;

    if (needToCompound) {
      String[] bits = exp.split(" \\+ ");
      return Integer.parseInt(bits[0]) + run(bits[1]);
    }

    if (needToPlus) {
      exp = exp.replaceAll("\\- ", "\\+ \\-");

      String[] bits = exp.split(" \\+ ");

      int sum = 0;

      for (int i = 0; i < bits.length; i++) {
        sum += Integer.parseInt(bits[i]);
      }

      return sum;
    } else if (needToMultiply) {
      String[] bits = exp.split(" \\* ");

      int rs = 1;

      for (int i = 0; i < bits.length; i++) {
        rs *= Integer.parseInt(bits[i]);
      }
      return rs;
    }

    throw new RuntimeException("처리할 수 있는 계산식이 아닙니다");
  }
}