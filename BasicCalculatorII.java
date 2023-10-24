package practice;

import java.util.Stack;

/*
INTUITION:
We could just normally traverse through the string and start performing the operations.
But we need to perform * and / operations first.
But we need to keep track of previous numbers

Stack can be used to do this.
We keep adding the numbers into the stack till we encounter a * or / sign
Once we encounter that we perform that operation with top of stack and current number and then push
the ans into the stack.
at the end sum of stack will be the answer of the expression
 */

public class BasicCalculatorII {
    public int calculate(String s) {

        int result = 0;
        Stack<Integer> stack = new Stack<>();
        char op =  '+';
        int curNum = 0;

        for(int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);

            if(Character.isDigit(curChar)) {
                curNum = (curNum * 10) + Character.getNumericValue(curChar);
            }

            if(!Character.isDigit(curChar) && curChar != ' ' || i == s.length() - 1) {
                if(op == '+')
                    stack.push(curNum);
                else if(op == '-')
                    stack.push(-curNum);
                else if(op == '*')
                    stack.push(stack.pop() * curNum);
                else if(op == '/')
                    stack.push(stack.pop() / curNum);
                op = curChar;
                curNum = 0;
            }
        }
        while(!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}
