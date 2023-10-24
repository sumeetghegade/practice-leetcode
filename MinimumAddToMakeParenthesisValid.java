package practice;

import java.util.Stack;


/*
INTUITION:
For every '(' we need a ')'.

So we keep pushing each char in the string into a stack and at any point
if stack top == '(' and the next char is ')' we pop from stack.

so at the end we will have all invalid parenthesis in the stack and we can return the stack size as answer as we will need to add the same amount of
parenthesis to make it valid


Method2:
But as we just need the number of required parenthesis we can replace the stack with a counter.
We keep a count of '(' brackets,
if we see an open bracket we decrement the count.
But it might happen that we dont have any opening brackets but we come across a closing bracket.
To take this into account we increment our res as we will need to add an opening bracket to compensate.

return sum of open brackets and the res at the end.

 */

public class MinimumAddToMakeParenthesisValid {

    // stack solution
    public int minAddToMakeValid1(String s) {
        Stack<Character> stack = new Stack<>();
        if(s == null || s.length() == 0)
            return 0;

        int index = 0;
        while(index < s.length()) {
            if(!stack.isEmpty() && stack.peek() == '(' && s.charAt(index) == ')')
                stack.pop();
            else
                stack.push(s.charAt(index));
            index++;
        }

        return stack.size();
    }


    // counter solution
    public int minAddToMakeValid2(String s) {
        int num_open = 0;
        int res = 0;


        for(char c: s.toCharArray()) {
            if(c == '(')
                num_open++;
            else if(num_open == 0)
                res++;
            else
                num_open--;
        }

        return num_open + res;
    }
}
