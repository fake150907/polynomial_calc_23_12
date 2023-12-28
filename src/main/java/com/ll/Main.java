package com.ll;

public class Main {
  public static void main(String[] args) {

    String exp = "-(8 + 2) * -(7 + 3) + 5";

    int startPos = 0;
    int endPos = 7;

    String head = exp.substring(0, startPos);
    String body = "(" + exp.substring(startPos + 1, endPos + 1) + " * -1)";
    String tail = exp.substring(endPos);

    System.out.println(head + body + tail);
  }
}