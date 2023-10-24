package practice;

import java.util.Stack;

/*
INTUITION:
We can use a stack to keep track of adjacent character.
As there can be multiple adjacent characters we can also maintain a count in the stack. this can be done using an array of length 2.
arr[0] --> character arr[1] --> count
when we see same char as stack top we increase its count else push it in.
if the count of stack top >= k we pop it

finally concatenate the characters in stack and return

 */

public class RemoveAllAdjacentDuplicatesInStringII {

    public String removeDuplicates(String s, int k) {

        Stack<int[]> stack = new Stack<>();

        for(char c: s.toCharArray()) {
            if(!stack.isEmpty() && stack.peek()[0] == c)
                stack.peek()[1]++;
            else
                stack.push(new int[]{c, 1});
            if(!stack.isEmpty() && stack.peek()[1] >= k)
                stack.pop();

        }

        StringBuilder sb = new StringBuilder();
        for(int[] arr: stack) {
            int count = arr[1];
            while(count > 0){
                sb.append((char)arr[0]);
                count--;
            }
        }

        return sb.toString();

    }
}
