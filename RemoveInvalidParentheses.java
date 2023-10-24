package practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/*
Intuition:
We first need to find out the minimum number of removals that will be required to make the string valid.
We can use the usual stack logic to get this number.
Now that we have this number we can check for all combinations of strings with minRemoval brackets removed.
get the valid ones and return.
We can use a set to introduce memoization which will help in reducing the runtime.


TC:
The worst-case time complexity is exponential, O(b^d), where b is the branching factor
(the average number of child nodes per node) and d is the depth of the search tree.
In the program, the branching factor is at most 2 (either remove or keep a parenthesis at each position),
and the depth can be up to the length of the input string. Therefore, the worst-case time complexity of
the program is O(2^n), where n is the length of the input string.
 */

public class RemoveInvalidParentheses {

    public static List<String> removeInvalidParentheses(String s) {

        ArrayList<String> result = new ArrayList<>();
        int minRemovals = getMinRemovals(s);
        HashSet<String> set = new HashSet<>();
        getResults(s, minRemovals, set, result);
        return result;
    }

    public static void getResults(String s, int minRemovals, HashSet<String> set, ArrayList<String> result) {
        if(set.contains(s))
            return;
        set.add(s);
        if(minRemovals == 0) {
            if(getMinRemovals(s) == 0)
                result.add(s);
            return;
        }
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i)!='(' && s.charAt(i)!=')') continue;
            String left = s.substring(0,i);
            String right = s.substring(i+1);
            if(!set.contains(left+right))
                getResults(left + right, minRemovals-1, set, result);
        }
    }

    public static int getMinRemovals(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(')
                stack.push('(');
            else if(s.charAt(i) == ')'){
                if(stack.isEmpty() || stack.peek() == ')')
                    stack.push(')');
                else if(stack.peek() == '(')
                    stack.pop();
            }
        }
        return stack.size();
    }

    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("(())())"));
    }
}
