package practice;

import java.util.ArrayDeque;
import java.util.Deque;

/*
INTUITION:
We can make one pass to identify which parentheses are invalid.
This can be done using a stack and we keep popping valid parenthesis. we store indexes of parenthesis.
So at the end of first pass we will have all invalid parentheses indexes in the stack.
And we do not want these in our final string

Also as we need one more pass to construct the result,
we can use a deque instead of stack so that we can access elements at the back of the deque.

O(n)
 */

public class MinimumRemoveToMakeValidParentheses {

    public static String minRemoveToMakeValid(String s) {
        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(')
                deque.push(i);
            else if(s.charAt(i) == ')') {
                if(!deque.isEmpty() && s.charAt(deque.peek()) == '(')
                    deque.pop();
                else
                    deque.push(i);
            }
        }

        for(int i = 0; i < s.length(); i++) {
            if(deque.size() > 0 && i == deque.peekLast()){
                deque.pollLast();
                continue;
            }
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("))(("));
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de"));
    }
}
