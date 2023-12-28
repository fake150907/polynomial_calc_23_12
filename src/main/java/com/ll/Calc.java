package com.ll;

public class Calc {
  public static int run(String exp) {// 매개변수 exp로 인자를 받는 run 함수를 선언한다.
    exp = exp.trim();// exp의 좌우공백을 제거한다
    exp = stripOuterBracket(exp);// exp를 인자로 줘서 stripOuterBracket함수 실행한 값을 exp에 담겠다.

    // 연산기호가 없으면 바로 리턴
    if (!exp.contains(" ")) return Integer.parseInt(exp);// 만약 공백을 포함하고 있지 않다면 return값으로 exp를 바로 정수화해서 넘겨준다.

    boolean needToMultiply = exp.contains(" * ");//  exp가 " * "를 포함하고 있다면 값에 true담고 포함하지 않으면 false
    boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");// //  exp가 " + " 또는 " - " 둘 중 하나라도 포함하고 있다면 값에 true담고 둘 다 포함하지 않으면 false 둘 다 포함 하면 true를 담겠다.

    boolean needToCompound = needToMultiply && needToPlus;// needToMultiply 와 needToPlus가 둘 다 true면 true를 담고 둘 중 하나라도 false면 false를 담는다.
    boolean needToSplit = exp.contains("(") || exp.contains(")");// "(" 또는 ")" 둘 중 하나라도 exp에 포함이 된다면 true를 담고 둘 다 포함하지 않으면 false를 담는다.

    if (needToSplit) { // needToSplit가 true일때 이 if문 선언

      int splitPointIndex = findSplitPointIndex(exp);// splitPointIndex공간에 인자가 exp인 함수 indSplitPointIndex(exp)를 실행하고 return한 값을 담겠다.

      String firstExp = exp.substring(0, splitPointIndex);// firstExp라는 공간에 exp를 substring(0, splitPointIndex)한 결과 값을 담겠다.
      String secondExp = exp.substring(splitPointIndex + 1);// econdExp라는 공간에 exp를 substring(splitPointIndex + 1)한 결과 값을 담겠다.

      char operator = exp.charAt(splitPointIndex);// econdExp라는 공간에 exp를 charAt(splitPointIndex)한 결과 값을 담겠다.

      exp = Calc.run(firstExp) + " " + operator + " " + Calc.run(secondExp);// exp에 Calc안에 있는 인자값으로 firstExp인 가지는 run함수를 실행한 값 + " " + 위의 operator의 값 + " " + Calc안에 있는 인자값으로 secondExp인 가지는 run함수를 실행한 값 이것들을 담겠다.

      return Calc.run(exp);// Calc안에 있는 인자값으로 exp를 가지는 run함수를 실행한 값을 return하겠다.

    } else if (needToCompound) {// needToCompound가 true일 때 안의 코드 실행
      String[] bits = exp.split(" \\+ ");// 문자열 배열 bits라는 공간에 exp를 " \\+ "를 기준으로 나눠서 나누어진 값들을 순서대로 담겠다.

      return Integer.parseInt(bits[0]) + Calc.run(bits[1]); // (bits[0]방의 원소를 정수로 바꾼 값 + Calc안에 있는 함수 run에 인자로 bits[1]방의 원소를 줘서 실행 시킨 값)을 return한다.
    }
    if (needToPlus) {// needToPlus가 true일 때 안의 코드 실행
      exp = exp.replaceAll("\\- ", "\\+ \\-");// exp를 replaceAll이라는 함수를 이용해 "\\- "를 "\\+ \\-"로 바꾼 값을 exp에 담는다.

      String[] bits = exp.split(" \\+ "); // 문자열 배열 bits라는 공간에 exp를 " \\+ "를 기준으로 나눠서 나누어진 값들을 순서대로 담겠다.

      int sum = 0;// sum이라는 공간에 0을 담았다.

      for (int i = 0; i < bits.length; i++) {// i 초기값 0부터 (bits의 길이-1)까지 1씩 증가시키는 for문이다.
        sum += Integer.parseInt(bits[i]);// 증가된 i의 값을 bits의 인덱스 번호로 지정해서 sum이라는 공간에 중첩해서 더한다음 그 값을 계속 담는다.
      }

      return sum;// sun을 return한다.
    } else if (needToMultiply) {// needToMultiply가 true일 때 안의 코드 실행
      String[] bits = exp.split(" \\* ");// 문자열 배열 bits라는 공간에 exp를 " \\* "를 기준으로 나눠서 나누어진 값들을 순서대로 담겠다.

      int rs = 1;// rs이라는 공간에 1을 담았다.

      for (int i = 0; i < bits.length; i++) {// i 초기값 0부터 (bits의 길이-1)까지 1씩 증가시키는 for문이다.
        rs *= Integer.parseInt(bits[i]);// 증가된 i의 값을 bits의 인덱스 번호로 지정해서 rs라는 공간에 중첩해서 곱한다음 그 값을 계속 담는다.
      }
      return rs;// rs를 return한다.
    }

    throw new RuntimeException("처리할 수 있는 계산식이 아닙니다");// 위의 모든 if문들이 false일 때 처리할 수 없는 계산식이라고 알려준다.
  }

  private static int findSplitPointIndexBy(String exp, char findChar) {// 매개변수로 exp와 findChar를 가지고 정수를 return하는 함수findSplitPointIndexBy 선언.
    int bracketCount = 0; // bracketCount라는 공간에 0을 담겠다. 나중에 괄호의 짝을 세려고 만든 변수

    for (int i = 0; i < exp.length(); i++) {// i 초기값 0부터 (exp의 길이-1)까지 1씩 증가시키는 for문이다.
      char c = exp.charAt(i);// exp를 charAt()함수로 인덱스 번호로 i를 넘겨주면서 c라는 공간에 하나씩 담을 것이다.

      if (c == '(') {// c의 값이 '('이면?
        bracketCount++;// bracketCount의 값을 1증가시킨다.
      } else if (c == ')') {// c의 값이 ')'이면?
        bracketCount--;// bracketCount의 값을 1감소시킨다.
      } else if (c == findChar) {// c의 값이 매개변수를 통해 들어온 인자의 값과 같다면
        if (bracketCount == 0) return i; // 그러면서 bracketCount가 0일때 return i를 해준다.
      }
    }
    return -1;// 위의 if문들이 전부false가 되었을 때 return -1 해준다.
  }

  private static int findSplitPointIndex(String exp) {//매개변수로 exp를 가지고 정수를 return하는 함수 findSplitPointIndex 선언.
    int index = findSplitPointIndexBy(exp, '+');//

    if (index >= 0) return index;

    return findSplitPointIndexBy(exp, '*');
  }

  private static String stripOuterBracket(String exp) {// 매개변수로 exp를 가지고 정수를 return하는 함수 stripOuterBracket 선언.
    int outerBracketCount = 0;// outerBracketCount라는 공간에 0을 담겠다.

    while (exp.charAt(outerBracketCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketCount) == ')') {
      outerBracketCount++;
    }

    if (outerBracketCount == 0) return exp;


    return exp.substring(outerBracketCount, exp.length() - outerBracketCount);
  }
}